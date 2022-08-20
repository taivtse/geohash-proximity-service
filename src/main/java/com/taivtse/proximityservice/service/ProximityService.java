package com.taivtse.proximityservice.service;

import ch.hsr.geohash.GeoHash;
import ch.hsr.geohash.WGS84Point;
import ch.hsr.geohash.queries.GeoHashCircleQuery;
import ch.hsr.geohash.util.VincentyGeodesy;
import com.taivtse.proximityservice.controller.dto.GeoSpatialIndexDto;
import com.taivtse.proximityservice.controller.dto.ProximitySearchRequest;
import com.taivtse.proximityservice.controller.dto.ProximitySearchResponse;
import com.taivtse.proximityservice.mapper.GeoSpatialMapper;
import com.taivtse.proximityservice.repository.GeoSpatialRepository;
import com.taivtse.proximityservice.repository.entity.GeoSpatialIndex;
import com.taivtse.proximityservice.util.GeoHashUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author taivt
 * @since 2022/08/20 15:53:01
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProximityService {

  private final GeoSpatialRepository geoSpatialRepository;
  private final GeoSpatialMapper geoSpatialMapper = GeoSpatialMapper.INSTANCE;

  public ProximitySearchResponse findProximityLocations(ProximitySearchRequest request) {
    // Find center geo hash
    int geoHashPrefixLength = GeoHashUtil.getPrefixLength(request.getRadius());
    GeoHash centerGeoHash = GeoHash.withCharacterPrecision(request.getLatitude(), request.getLongitude(), geoHashPrefixLength);

    // Find adjacent geo hashes
    List<String> adjacentGeoHashes = GeoHashUtil.findAdjacentGeoHashes(centerGeoHash);
    log.info("Geo hash prefix = {}, adjacent geo hashes = {}", centerGeoHash.toBase32(), adjacentGeoHashes);

    // Query locations by geo hashes
    List<String> findingGeoHashes = new ArrayList<>(adjacentGeoHashes);
    findingGeoHashes.add(centerGeoHash.toBase32());
    List<GeoSpatialIndex> geoSpatialIndices = geoSpatialRepository.findAllByGeoHashes(geoHashPrefixLength, findingGeoHashes);

    // Filter out-of-scope locations
    WGS84Point centerPoint = new WGS84Point(request.getLatitude(), request.getLongitude());
    List<GeoSpatialIndexDto> geoSpatialIndexDtos = geoSpatialIndices.stream()
        .map(geoSpatialIndex -> {
          WGS84Point neighbourPoint = new WGS84Point(geoSpatialIndex.getLatitude(), geoSpatialIndex.getLongitude());

          // Calculate the distance
          double distanceInMeters = VincentyGeodesy.distanceInMeters(centerPoint, neighbourPoint);
          if (distanceInMeters > request.getRadius()) {
            log.info("Geo spatial index is out of the radius, geoSpatialIndex = {}", geoSpatialIndex);
            return null;
          }

          GeoSpatialIndexDto geoSpatialIndexDto = geoSpatialMapper.map(geoSpatialIndex);
          geoSpatialIndexDto.setDistanceInMeters(distanceInMeters);
          return geoSpatialIndexDto;
        })
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    return ProximitySearchResponse.builder()
        .locations(geoSpatialIndexDtos)
        .build();
  }

}
