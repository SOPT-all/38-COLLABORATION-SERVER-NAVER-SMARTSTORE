package org.sopt.domain.product.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryName {

  FURNITURE_INTERIOR("가구/인테리어"),
  BOOK("도서"),
  DIGITAL_APPLIANCE("디지털/가전"),
  HEALTH("생활/건강"),
  SPORTS_LEISURE("스포츠/레저"),
  FOOD("식품"),
  LEISURE_CONVENIENCE("여가/생활편의"),
  BABY_PARENTING("출산/육아"),
  FASHION_CLOTHES("패션의류"),
  FASHION_ACCESSORIES("패션잡화"),
  COSMETIC_BEAUTY("화장품/미용");

  private final String description;

}