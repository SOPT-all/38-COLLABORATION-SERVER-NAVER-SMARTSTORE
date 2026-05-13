package org.sopt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.sopt.service.NoticeService;
import org.sopt.global.response.ApiResponseBody;
import org.sopt.global.response.SuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Notice", description = "공지사항 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(
            summary = "공지사항 전체 조회",
            description = "전체 공지사항 목록을 조회합니다."
    )
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
