package org.sopt.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SuccessCode {

    OK(HttpStatus.OK, "요청이 성공했습니다."),
    CREATED(HttpStatus.CREATED, "리소스가 생성되었습니다."),

    // =================== PRODUCT ===================
    PRODUCT_FOUND(HttpStatus.OK, "상품 조회에 성공했습니다."),
    PRODUCT_CREATED(HttpStatus.CREATED, "상품 등록에 성공했습니다.");

    private final HttpStatus status;
    private final String message;

    SuccessCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
