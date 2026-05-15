package org.sopt.dto.response;

import lombok.Builder;
import org.sopt.domain.product.Image;

@Builder
public record RepresentativeImageResponse(
    Long id,
    String imageUrl
) {
  public static RepresentativeImageResponse from(Image image) {
    return RepresentativeImageResponse.builder()
        .id(image.getId())
        .imageUrl(image.getImageUrl())
        .build();
  }
}
