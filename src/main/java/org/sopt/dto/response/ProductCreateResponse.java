package org.sopt.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import org.sopt.domain.product.Product;

@Builder
public record ProductCreateResponse(
    Long id,
    String name,
    Long categoryId,
    int price,
    List<ProductImageResponse> images,
    LocalDateTime createdAt
){
  public static ProductCreateResponse from(Product product) {
    return ProductCreateResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .categoryId(product.getCategory().getId())
        .price(product.getPrice())
        .images(product.getImages().stream()
            .map(ProductImageResponse::from)
            .toList())
        .createdAt(product.getCreatedAt())
        .build();
  }
}
