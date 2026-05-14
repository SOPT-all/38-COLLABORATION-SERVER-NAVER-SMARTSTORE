package org.sopt.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

  // =================== COMMON ===================
  // 400 BAD REQUEST
  INVALID_NUMBER_FORMAT(HttpStatus.BAD_REQUEST, "COM_001", "숫자만 입력해주세요."),
  INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "COM_002", "잘못된 이메일 형식입니다."),
  INVALID_DATE_FORMAT(HttpStatus.BAD_REQUEST, "COM_005", "잘못된 날짜 형식입니다."),
  INVALID_NULL_DATA(HttpStatus.BAD_REQUEST, "COM_006", "빈 값은 허용되지 않습니다."),
  INVALID_MAPPING_PARAMETER(HttpStatus.BAD_REQUEST, "COM_007", "매핑할 수 없는 값입니다."),
  INVALID_SIZE(HttpStatus.BAD_REQUEST, "COM_008", "잘못된 크기입니다."),

  // 404 NOT FOUND
  RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "COM_003", "존재하지 않는 리소스입니다."),

  // 500 INTERNAL SERVER ERROR
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COM_004", "서버 내부 오류가 발생했습니다."),

  // =================== PRODUCT ===================
  // 404 NOT FOUND
  PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "PRO_001", "존재하지 않는 상품입니다."),
  CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "CAT_001", "존재하지 않는 카테고리입니다."),

  // 409 CONFLICT
  PRODUCT_BY_NAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "PRO_002", "해당 이름의 상품이 이미 존재합니다."),

  // =================== IMAGE ===================
  // 400 BAD REQUEST
  REPRESENTATIVE_IMAGE_REQUIRED(HttpStatus.BAD_REQUEST, "IMG_002", "대표 이미지가 필요합니다."),
  MULTIPLE_REPRESENTATIVE_IMAGES(HttpStatus.BAD_REQUEST, "IMG_003", "대표 이미지는 하나만 허용됩니다."),

  // 404 NOT FOUND
  IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "IMG_001", "존재하지 않는 이미지입니다."),

  // =================== NOTICE ===================
  // 404 NOT FOUND
  NOTICE_NOT_FOUND(HttpStatus.NOT_FOUND, "NOT_001", "존재하지 않는 공지사항입니다.");

  private final HttpStatus status;
  private final String code;
  private final String message;

  ErrorCode(HttpStatus status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }
}
