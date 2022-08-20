package com.taivtse.proximityservice.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author taivt
 * @since 2022/08/20 15:56:20
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "geo_spatial_index")
public class GeoSpatialIndex {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String restaurantName;

  private String geoHash;

  private Double latitude;

  private Double longitude;
}
