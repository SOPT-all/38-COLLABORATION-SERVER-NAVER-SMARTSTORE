package org.sopt.dto.response;

public record PresignedUrlResponse (
  String presignedUrl,
  String s3Url,
  String contentType,
  int expiresIn
) {}
