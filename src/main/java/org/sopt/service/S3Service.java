package org.sopt.service;

import java.time.Duration;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.sopt.dto.request.PresignedUrlRequest;
import org.sopt.dto.response.PresignedUrlResponse;
import org.sopt.global.exception.BusinessException;
import org.sopt.global.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;

@Service
@RequiredArgsConstructor
public class S3Service {

  private final S3Presigner s3Presigner;

  @Value("${spring.cloud.aws.s3.bucket}")
  private String bucket;

  @Value("${spring.cloud.aws.region.static}")
  private String region;

  private static final int EXPIRES_IN = 300;
  private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
  private static final Set<String> ALLOWED_TYPES = Set.of(
      "image/png", "image/jpeg", "image/webp"
  );

  public PresignedUrlResponse generatePresignedUrl(PresignedUrlRequest request) {

    // 지원하지 않는 MIME 타입 검증
    if (!ALLOWED_TYPES.contains(request.contentType())) {
      throw new BusinessException(ErrorCode.UNSUPPORTED_MIME_TYPE);
    }

    // 파일 크기 검증 (클라이언트 제공 값 기반, 5MB 초과 시 거부)
    if (request.fileSize() > MAX_FILE_SIZE) {
      throw new BusinessException(ErrorCode.FILE_SIZE_EXCEEDED);
    }

    // S3 저장 경로: products/{uuid}-{원본파일명}
    String key = "products/" + UUID.randomUUID() + "-" + request.fileName();

    PutObjectRequest putObjectRequest = PutObjectRequest.builder()
        .bucket(bucket)
        .key(key)
        .contentType(request.contentType())
        .build();

    // Presigned URL 생성 (유효시간 300초)
    PresignedPutObjectRequest presigned = s3Presigner.presignPutObject(r -> r
        .signatureDuration(Duration.ofSeconds(EXPIRES_IN))
        .putObjectRequest(putObjectRequest));

    // 업로드 완료 후 실제 접근 URL
    String s3Url = "https://" + bucket + ".s3." + region + ".amazonaws.com/" + key;

    return new PresignedUrlResponse(
        presigned.url().toString(),
        s3Url,
        request.contentType(),
        EXPIRES_IN
    );
  }

  public void validateIssuedS3Url(String imageUrl, String contentType) {
    String expectedPrefix = "https://" + bucket + ".s3." + region + ".amazonaws.com/products/";

    if (!imageUrl.startsWith(expectedPrefix)) {
      throw new BusinessException(ErrorCode.INVALID_IMAGE_URL);
    }

    if (!ALLOWED_TYPES.contains(contentType)) {
      throw new BusinessException(ErrorCode.UNSUPPORTED_MIME_TYPE);
    }
  }
}