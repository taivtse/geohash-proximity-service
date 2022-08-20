package com.taivtse.proximityservice.repository;

import com.taivtse.proximityservice.repository.entity.GeoSpatialIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author taivt
 * @since 2022/08/20 15:55:09
 */
@Repository
public interface GeoSpatialRepository extends JpaRepository<GeoSpatialIndex, Integer> {

  @Query("FROM GeoSpatialIndex WHERE SUBSTRING(geoHash, 1, :geoHashPrefixLength) IN :geoHashPrefixes")
  List<GeoSpatialIndex> findAllByGeoHashes(int geoHashPrefixLength, List<String> geoHashPrefixes);
}
