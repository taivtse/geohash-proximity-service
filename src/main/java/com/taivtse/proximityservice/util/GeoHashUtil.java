package com.taivtse.proximityservice.util;

import ch.hsr.geohash.GeoHash;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author taivt
 * @since 2022/08/20 22:34:53
 */
public class GeoHashUtil {
  private static final List<Pair<Float, Float>> geoLengthGrid = List.of(
      Pair.of(5_009_400F, 4_992_600F),
      Pair.of(1_252_300F, 624_100F),
      Pair.of(156_500F, 156_000F),
      Pair.of(39_100F, 19_500F),
      Pair.of(4_900F, 4_900F),
      Pair.of(1_200F, 609.4F),
      Pair.of(152.9F, 152.4F),
      Pair.of(38.2F, 19F),
      Pair.of(4.8F, 4.8F)
  );

  public static int getPrefixLength(float radius) {
    float diameter = radius * 2;
    int geoHashLength = 1;
    for (int i = 0; i < geoLengthGrid.size(); i++) {
      Pair<Float, Float> pair = geoLengthGrid.get(i);
      Float width = pair.getFirst();
      Float height = pair.getSecond();
      if (diameter <= width && diameter <= height) {
        geoHashLength = i + 1;
      }
    }
    return geoHashLength;
  }

  public static List<String> findAdjacentGeoHashes(GeoHash geoHash) {
    return Arrays.stream(geoHash.getAdjacent())
        .map(GeoHash::toBase32)
        .collect(Collectors.toList());
  }
}
