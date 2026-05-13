package org.sopt.domain.notice.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.notice.service.NoticeService;
import org.sopt.global.response.ApiResponseBody;
import org.sopt.global.response.SuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public ResponseEntity<ApiResponseBody<?, Void>> getAllNotices(){
        return ResponseEntity.status(SuccessCode.OK.getStatus())
                .body(
                        ApiResponseBody.ok(
                                SuccessCode.OK, noticeService.getAllNotices()
                        )
                );
    }
}
