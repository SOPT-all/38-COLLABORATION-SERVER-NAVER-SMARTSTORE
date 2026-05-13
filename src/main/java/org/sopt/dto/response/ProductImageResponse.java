package org.sopt.dto.response;

import lombok.Builder;
import org.sopt.domain.product.Image;

@Builder
public record ProductImageResponse(
    Long id,
    String imageUrl,
    String contentType,
    int imageOrder,
    boolean isRepresentative
) {
  public static ProductImageResponse from(Image image) {
    return ProductImageResponse.builder()
        .id(image.getId())
        .imageUrl(image.getImageUrl())
        .contentType(image.getContentType())
        .imageOrder(image.getImageOrder())
        .isRepresentative(image.isRepresentative())
        .build();
  }
}
