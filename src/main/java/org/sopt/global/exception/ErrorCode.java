package org.sopt.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // =================== COMMON ===================
    // 400 BAD REQUEST
    INVALID_NUMBER_FORMAT(400, "COM_001", "숫자만 입력해주세요."),
    INVALID_EMAIL_FORMAT(400, "COM_002", "잘못된 이메일 형식입니다."),
    INVALID_DATE_FORMAT(400, "COM_005", "잘못된 날짜 형식입니다."),
    INVALID_NULL_DATA(400, "COM_006", "빈 값은 허용되지 않습니다."),
    INVALID_MAPPING_PARAMETER(400, "COM_007", "매핑할 수 없는 값입니다."),

    // 404 NOT FOUND
    RESOURCE_NOT_FOUND(404, "COM_003", "존재하지 않는 리소스입니다."),

    // 500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "COM_004", "서버 내부 오류가 발생했습니다."),

    // =================== PRODUCT ===================
    // 404 NOT FOUND
    PRODUCT_NOT_FOUND(404, "PRO_001", "존재하지 않는 상품입니다."),
    CATEGORY_NOT_FOUND(404, "CAT_001", "존재하지 않는 카테고리입니다."),
    IMAGE_NOT_FOUND(404, "IMG_001", "존재하지 않는 이미지입니다."),
    NOTICE_NOT_FOUND(404, "NOT_001", "존재하지 않는 공지사항입니다."),

    // 409 CONFLICT
    PRODUCT_BY_NAME_ALREADY_EXISTS(409, "PRO_002", "해당 이름의 상품이 이미 존재합니다.");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
