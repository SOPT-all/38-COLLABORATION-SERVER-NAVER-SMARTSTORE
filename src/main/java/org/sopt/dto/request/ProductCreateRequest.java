package org.sopt.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "상품 등록 요청")
public record ProductCreateRequest(

    @Schema(description = "상품명", example = "크리니크 시베리아리치크림 125ml 대용량", maxLength = 100)
    @NotBlank
    @Size(max = 100)
    String name,

    @Schema(description = "카테고리 ID", example = "1")
    @NotNull
    Long categoryId,

    @Schema(description = "판매가", example = "92000")
    @NotNull
    Integer price,

    @Schema(description = "상품 이미지 목록", maxLength = 3)
    @NotEmpty
    @Size(max = 3)
    List<@Valid ProductImageCreateRequest> images
) {
}
