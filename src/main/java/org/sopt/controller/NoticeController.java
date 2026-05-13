package org.sopt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.sopt.dto.response.NoticeResponse;
import org.sopt.service.NoticeService;
import org.sopt.global.response.ApiResponseBody;
import org.sopt.global.response.SuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "공지사항 전체 조회 성공"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "공지사항을 찾을 수 없음"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "서버 내부 오류"
            )
    })
    @GetMapping
    public ResponseEntity<ApiResponseBody<List<NoticeResponse>, Void>> getAllNotices(){
        return ResponseEntity.status(SuccessCode.OK.getStatus())
                .body(
                        ApiResponseBody.ok(
                                SuccessCode.OK, noticeService.getAllNotices()
                        )
                );
    }
}
