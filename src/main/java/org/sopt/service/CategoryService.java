package org.sopt.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sopt.dto.response.CategorySearchResponse;
import org.sopt.global.exception.BusinessException;
import org.sopt.global.exception.ErrorCode;
import org.sopt.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public List<CategorySearchResponse> searchCategories(String keyword) {
    if (!StringUtils.hasText(keyword)) {
      throw new BusinessException(ErrorCode.INVALID_NULL_DATA);
    }

    return categoryRepository.findAll().stream()
        .filter(category -> category.getName().getDescription().contains(keyword))
        .map(CategorySearchResponse::from)
        .toList();
  }

}
