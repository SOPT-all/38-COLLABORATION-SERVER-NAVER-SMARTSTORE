package org.sopt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.dto.request.PresignedUrlRequest;
import org.sopt.dto.response.PresignedUrlResponse;
import org.sopt.global.response.ApiResponseBody;
import org.sopt.global.response.SuccessCode;
import org.sopt.service.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController {

  private final S3Service s3Service;

  @PostMapping("/presigned-url")
  public ResponseEntity<ApiResponseBody<PresignedUrlResponse, Void>> getPresignedUrl(
      @RequestBody @Valid PresignedUrlRequest request
  ) {
    return ResponseEntity.ok(
        ApiResponseBody.ok(SuccessCode.PRESIGNED_URL_SUCCESS, s3Service.generatePresignedUrl(request))
    );
  }
}