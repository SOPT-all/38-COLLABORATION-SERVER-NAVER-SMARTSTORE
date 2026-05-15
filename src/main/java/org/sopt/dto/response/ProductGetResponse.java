package org.sopt.dto.response;

import lombok.Builder;
import org.sopt.domain.product.Image;
import org.sopt.domain.product.Product;
import org.sopt.global.exception.BusinessException;
import org.sopt.global.exception.ErrorCode;

@Builder
public record ProductGetResponse(
    Long id,
    String name,
    int price,
    RepresentativeImageResponse representativeImage
) {
  public static ProductGetResponse from(Product product) {
    Image representativeImage = product.getImages().stream()
        .filter(Image::isRepresentative)
        .findFirst()
        .orElseThrow(() -> new BusinessException(ErrorCode.IMAGE_NOT_FOUND));

    return ProductGetResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .price(product.getPrice())
        .representativeImage(RepresentativeImageResponse.from(representativeImage))
        .build();
  }
}
