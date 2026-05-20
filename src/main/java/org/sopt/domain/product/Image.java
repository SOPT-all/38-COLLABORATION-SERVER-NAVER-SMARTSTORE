package org.sopt.domain.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.domain.common.BaseTimeEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Image extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 500)
  private String imageUrl;

  @Column(nullable = false)
  private String contentType;

  @Column(nullable = false)
  private int imageOrder;

  @Column(name = "is_representative", nullable = false)
  private boolean representative = false;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Builder
  private Image(String imageUrl, String contentType, int imageOrder, boolean representative) {
    this.imageUrl = imageUrl;
    this.contentType = contentType;
    this.imageOrder = imageOrder;
    this.representative = representative;
  }

  void assignProduct(Product product) {
    this.product = product;
  }
}
