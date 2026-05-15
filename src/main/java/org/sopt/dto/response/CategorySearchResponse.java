package org.sopt.dto.response;

import lombok.Builder;
import org.sopt.domain.product.Category;

@Builder
public record CategorySearchResponse(
    Long id,
    String name
) {
  public static CategorySearchResponse from(Category category) {
    return CategorySearchResponse.builder()
        .id(category.getId())
        .name(category.getName().getDescription())
        .build();
  }
}
