package org.sopt.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "상품 이미지 등록 요청")
public record ProductImageCreateRequest(

    @Schema(description = "이미지 URL", example = "https://example.com/image.jpg")
    @NotBlank
    @Size(max = 500)
    String imageUrl,

    @Schema(description = "이미지 콘텐츠 타입", example = "image/jpeg")
    @NotBlank
    String contentType,

    @Schema(description = "이미지 순서", example = "1")
    @NotNull
    @Min(1)
    Integer imageOrder,

    @Schema(description = "대표 이미지 여부", example = "true")
    @NotNull
    Boolean isRepresentative
) {

}
