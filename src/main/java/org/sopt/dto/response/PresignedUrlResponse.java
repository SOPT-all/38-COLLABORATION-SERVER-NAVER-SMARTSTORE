package org.sopt.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Presigned URL 발급 응답")
public record PresignedUrlResponse (

  @Schema(description = "S3 업로드용 Presigned URL. PUT 메서드로 binary 전송")
  String presignedUrl,

  @Schema(description = "업로드 완료 후 실제 이미지 접근 URL. 상품 등록 시 서버에 전달할 값")
  String s3Url,

  @Schema(description = "파일의 ContentType")
  String contentType,

  @Schema(description = "Presigned URL 유효 시간 (초 단위, 300초 = 5분)")
  int expiresIn
) {}
