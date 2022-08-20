package com.taivtse.proximityservice.mapper;

import com.taivtse.proximityservice.controller.dto.GeoSpatialIndexDto;
import com.taivtse.proximityservice.repository.entity.GeoSpatialIndex;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author taivt
 * @since 2022/08/20 16:20:31
 */
@Mapper
public interface GeoSpatialMapper {
  GeoSpatialMapper INSTANCE = Mappers.getMapper(GeoSpatialMapper.class);

  @Mapping(target = "distanceInMeters", ignore = true)
  GeoSpatialIndexDto map(GeoSpatialIndex entity);
}
