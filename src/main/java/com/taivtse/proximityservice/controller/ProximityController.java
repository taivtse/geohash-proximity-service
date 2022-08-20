package com.taivtse.proximityservice.controller;

import ch.hsr.geohash.GeoHash;
import com.taivtse.proximityservice.controller.dto.ProximitySearchRequest;
import com.taivtse.proximityservice.controller.dto.ProximitySearchResponse;
import com.taivtse.proximityservice.service.ProximityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author taivt
 * @since 2022/08/20 15:26:33
 */
@RestController
@RequiredArgsConstructor
public class ProximityController {

  private final ProximityService proximityService;

  @GetMapping("/businesses/locations")
  public ResponseEntity<ProximitySearchResponse> findProximityLocations(ProximitySearchRequest request) {
    return ResponseEntity.ok(proximityService.findProximityLocations(request));
  }

}
