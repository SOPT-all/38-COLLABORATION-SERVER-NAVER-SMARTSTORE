package org.sopt.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PresignedUrlRequest (

  @NotBlank(message = "파일명은 필수입니다.")
  String fileName,

  @NotBlank(message = "content_type은 필수입니다.")
  String contentType
) {}
