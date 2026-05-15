package org.sopt.domain.notice.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NoticeType {
  GENERAL("일반"),
  SYSTEM("시스템"),
  SAFE_TRADE("안전거래"),
  SELLING_TIP("판매 TIP"),
  D_COMMERCE("D-커머스"),
  HAZARD_INFO("위해정보"),
  MANUAL("매뉴얼");

  private final String description;
}

