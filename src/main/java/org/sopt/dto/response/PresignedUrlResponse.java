package org.sopt.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Presigned URL 발급 응답")
public record PresignedUrlResponse (

  @Schema(description = "S3 업로드용 Presigned URL. PUT 메서드로 binary 전송",
      example = "https://bucket.s3.ap-northeast-2.amazonaws.com/products/uuid-image.png?X-Amz-Algorithm=...")
  String presignedUrl,

  @Schema(description = "업로드 완료 후 실제 이미지 접근 URL. 상품 등록 시 서버에 전달할 값",
      example = "https://bucket.s3.ap-northeast-2.amazonaws.com/products/uuid-image.png")
  String s3Url,

  @Schema(description = "파일의 ContentType", example = "image/png")
  String contentType,

  @Schema(description = "Presigned URL 유효 시간 (초 단위, 300초 = 5분)", example = "300")
  int expiresIn
) {}
