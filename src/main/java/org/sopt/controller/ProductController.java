package org.sopt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.dto.request.ProductCreateRequest;
import org.sopt.dto.response.ProductCreateResponse;
import org.sopt.global.response.ApiResponseBody;
import org.sopt.global.response.SuccessCode;
import org.sopt.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Tag(name = "Product", description = "상품 API")
public class ProductController {

  private final ProductService productService;

  @Operation(summary = "상품 등록", description = "상품명, 카테고리, 판매가, 이미지 목록을 입력받아 상품을 등록합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "상품 등록 성공"),
      @ApiResponse(responseCode = "400", description = "잘못된 요청 값"),
      @ApiResponse(responseCode = "404", description = "존재하지 않는 카테고리"),
  })
  @PostMapping
  public ResponseEntity<ApiResponseBody<ProductCreateResponse, Void>> createProduct(
      @Valid @RequestBody ProductCreateRequest request
  ) {
    ProductCreateResponse response = productService.createProduct(request);

    return ResponseEntity
        .status(SuccessCode.PRODUCT_CREATED.getStatus())
        .body(ApiResponseBody.created(SuccessCode.PRODUCT_CREATED, response));
  }

}
