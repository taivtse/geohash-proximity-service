package com.taivtse.proximityservice.controller.dto;

import lombok.*;

import java.util.List;

/**
 * @author taivt
 * @since 2022/08/20 15:37:29
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProximitySearchResponse {
  private List<GeoSpatialIndexDto> locations;
}
