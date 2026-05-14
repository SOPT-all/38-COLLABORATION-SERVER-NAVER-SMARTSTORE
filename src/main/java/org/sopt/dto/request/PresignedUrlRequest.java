package org.sopt.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Presigned URL 발급 요청")
public record PresignedUrlRequest (

  @Schema(description = "업로드할 파일 원본 이름 (확장자 포함)", example = "product-image.png")
  @NotBlank(message = "파일명은 필수입니다.")
  String fileName,

  @Schema(description = "파일의 contentType (image/png, image/jpeg, image/webp만 허용)", example = "image/png")
  @NotBlank(message = "contentType은 필수입니다.")
  String contentType
) {}
