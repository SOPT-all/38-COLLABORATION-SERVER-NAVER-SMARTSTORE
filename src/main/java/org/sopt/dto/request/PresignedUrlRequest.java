package org.sopt.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PresignedUrlRequest (
  @NotBlank String fileName,
  @NotBlank String ContentType
) {}
