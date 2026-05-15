package org.sopt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.dto.response.CategorySearchResponse;
import org.sopt.global.response.ApiResponseBody;
import org.sopt.global.response.SuccessCode;
import org.sopt.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@Tag(name = "Category", description = "카테고리 API")
public class CategoryController {

  private final CategoryService categoryService;

  @Operation(summary = "카테고리 검색", description = "검색어를 입력받아 카테고리 목록을 조회합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "카테고리 검색 성공"),
      @ApiResponse(responseCode = "400", description = "검색어 누락"),
  })
  @GetMapping("/search")
  public ResponseEntity<ApiResponseBody<List<CategorySearchResponse>, Void>> searchCategories(
      @Parameter(description = "검색할 카테고리 명", example = "가구", required = true)
      @RequestParam(required = false) String keyword
  ) {
    List<CategorySearchResponse> response = categoryService.searchCategories(keyword);

    return ResponseEntity
        .status(SuccessCode.CATEGORY_SEARCH_SUCCESS.getStatus())
        .body(ApiResponseBody.ok(SuccessCode.CATEGORY_SEARCH_SUCCESS, response));
  }
}
