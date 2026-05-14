package org.sopt.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.product.Category;
import org.sopt.domain.product.Image;
import org.sopt.domain.product.Product;
import org.sopt.dto.request.ProductCreateRequest;
import org.sopt.dto.request.ProductImageCreateRequest;
import org.sopt.dto.response.ProductCreateResponse;
import org.sopt.global.exception.BusinessException;
import org.sopt.global.exception.ErrorCode;
import org.sopt.repository.CategoryRepository;
import org.sopt.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  @Transactional
  public ProductCreateResponse createProduct(ProductCreateRequest request) {
    Category category = categoryRepository.findById(request.categoryId())
        .orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND));

    int price = parsePrice(request.price());

    validateRepresentativeImage(request.images());

    Product product = Product.builder()
        .name(request.name())
        .category(category)
        .price(price)
        .build();

    request.images().forEach(imageRequest -> {
      Image image = Image.builder()
          .imageUrl(imageRequest.imageUrl())
          .contentType(imageRequest.contentType())
          .imageOrder(imageRequest.imageOrder())
          .representative(imageRequest.isRepresentative())
          .build();

      product.addImage(image);
    });

    Product savedProduct = productRepository.save(product);
    return ProductCreateResponse.from(savedProduct);
  }

  private int parsePrice(Object price) {
    try {
      return Integer.parseInt(price.toString());
    } catch (NumberFormatException e) {
      throw new BusinessException(ErrorCode.INVALID_NUMBER_FORMAT);
    }
  }

  private void validateRepresentativeImage(List<ProductImageCreateRequest> images) {
    long representativeCount = images.stream()
        .filter(image -> image.isRepresentative())
        .count();

    if (representativeCount == 0) {
      throw new BusinessException(ErrorCode.REPRESENTATIVE_IMAGE_REQUIRED);
    } else if (representativeCount > 1) {
      throw new BusinessException(ErrorCode.MULTIPLE_REPRESENTATIVE_IMAGES);
    }
  }

}
