package org.sopt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Image", description = "이미지 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController {

  private final S3Service s3Service;

  @Operation(
      summary = "Presigned URL 발급",
      description = """
          S3에 이미지를 직접 업로드하기 위한 Presigned URL을 발급합니다.
          
          **업로드 플로우**
          1. 이 API로 presignedUrl과 s3Url을 발급받습니다.
          2. presignedUrl로 PUT 요청 + binary + ContentType 헤더를 담아 S3에 직접 업로드합니다.
          3. 업로드 완료 후 s3Url을 상품 등록 API에 전달합니다.
          
          **지원 MIME 타입:** image/png, image/jpeg, image/webp
          
          **Presigned URL 유효시간:** 300초 (5분)          
          """
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Presigned URL 발급 성공"),
      @ApiResponse(responseCode = "400",
          description = "파일명 또는 Content-Type 누락 (COM_006) / 지원하지 않는 MIME 타입 (IMG_004)")
  })
  @PostMapping("/presigned-url")
  public ResponseEntity<ApiResponseBody<PresignedUrlResponse, Void>> getPresignedUrl(
      @RequestBody @Valid PresignedUrlRequest request
  ) {
    return ResponseEntity.ok(
        ApiResponseBody.ok(SuccessCode.PRESIGNED_URL_SUCCESS, s3Service.generatePresignedUrl(request))
    );
  }
}