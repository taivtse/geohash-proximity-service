package com.taivtse.proximityservice.controller.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author taivt
 * @since 2022/08/20 15:39:01
 */
@Getter
@Setter
public class GeoSpatialIndexDto {
  private Integer id;
  private String restaurantName;
  private String geoHash;
  private Double latitude;
  private Double longitude;
  private Double distanceInMeters;
}
