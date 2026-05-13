package org.sopt.domain.notice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.sopt.domain.notice.Notice;

import java.time.LocalDateTime;

@Schema(description = "공지사항 응답 DTO")
public record NoticeResponse (
    @Schema(description = "공지사항 ID", example = "1")
    Long id,

    @Schema(description = "공지사항 타입", example = "시스템")
    String noticeType,

    @Schema(description = "공지사항 제목", example = "정산 일일 리포트 발송 일시 중단 안내")
    String title,

    @Schema(description = "공지사항 생성 시간", example = "2026-04-02T14:30:00")
    LocalDateTime createdAt
){
    public static NoticeResponse from(Notice notice){
        return new NoticeResponse(
                notice.getId(),
                notice.getNoticeType(),
                notice.getTitle(),
                notice.getCreatedAt()
        );
    }
}
