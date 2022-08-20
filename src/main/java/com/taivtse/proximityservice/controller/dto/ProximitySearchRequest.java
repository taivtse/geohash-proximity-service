package com.taivtse.proximityservice.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author taivt
 * @since 2022/08/20 15:38:14
 */
@Getter
@Setter
public class ProximitySearchRequest {
  @NotNull
  private Double latitude;

  @NotNull
  private Double longitude;

  @NotNull
  private Float radius;
}
