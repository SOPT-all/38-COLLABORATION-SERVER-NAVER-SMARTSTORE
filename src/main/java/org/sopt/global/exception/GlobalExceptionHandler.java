package org.sopt.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.sopt.global.response.ApiResponseBody;
import org.sopt.global.response.ErrorMeta;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiResponseBody<Void, ErrorMeta>> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpServletRequest request
  ) {
    System.err.println("HttpMessageNotReadableException: " + ex.getMessage());
    ErrorCode errorCode = ErrorCode.INVALID_MAPPING_PARAMETER;

    return ResponseEntity
        .status(errorCode.getStatus())
        .body(ApiResponseBody.onFailure(errorCode, createErrorMeta(request)));
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ApiResponseBody<Void, ErrorMeta>> handleBusinessException(
      BusinessException e,
      HttpServletRequest request
  ) {
    ErrorCode errorCode = e.getErrorCode();

    System.err.println("===== HANDLED BUSINESS EXCEPTION =====");
    System.err.printf("[errorCode=%s] %s%n", errorCode.getCode(), e.getMessage());
    System.err.println("======================================");

    return ResponseEntity.status(errorCode.getStatus())
        .body(ApiResponseBody.onFailure(errorCode, createErrorMeta(request)));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponseBody<Void, ErrorMeta>> handleException(
      Exception e,
      HttpServletRequest request
  ) {
    System.err.println("===== UNHANDLED EXCEPTION =====");
    System.err.println(e.getClass().getName() + ": " + e.getMessage());
    System.err.println("===============================");

    ErrorCode internalServerError = ErrorCode.INTERNAL_SERVER_ERROR;

    return ResponseEntity.status(internalServerError.getStatus())
        .body(ApiResponseBody.onFailure(internalServerError, e.getMessage(), createErrorMeta(request)));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponseBody<Void, ErrorMeta>> handleValidationException(
      MethodArgumentNotValidException e,
      HttpServletRequest request
  ) {
    System.err.println("===== VALIDATION EXCEPTION =====");
    e.getBindingResult().getFieldErrors().forEach(error -> {
      System.err.printf("[field=%s] %s%n", error.getField(), error.getDefaultMessage());
    });
    System.err.println("===============================");

    ErrorCode errorCode = resolveValidationErrorCode(e);

    return ResponseEntity.status(errorCode.getStatus())
        .body(ApiResponseBody.onFailure(errorCode, createErrorMeta(request)));
  }

  private ErrorMeta createErrorMeta(HttpServletRequest request) {
    return new ErrorMeta(
        request.getRequestURI(),
        System.currentTimeMillis()
    );
  }

  private ErrorCode resolveValidationErrorCode(MethodArgumentNotValidException e) {
    boolean hasSizeError = e.getBindingResult().getFieldErrors().stream()
        .anyMatch(error -> "Size".equals(error.getCode()));

    if (hasSizeError) {
      return ErrorCode.INVALID_SIZE;
    }

    return ErrorCode.INVALID_NULL_DATA;
  }
}
