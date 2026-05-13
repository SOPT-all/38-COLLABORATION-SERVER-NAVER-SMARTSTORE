package org.sopt.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.sopt.domain.notice.Notice;

import java.time.LocalDateTime;

@Schema(description = "공지사항 응답 DTO")
public record NoticeResponse (
    @Schema(description = "공지사항 ID")
    Long id,

    @Schema(description = "공지사항 타입")
    String noticeType,

    @Schema(description = "공지사항 제목")
    String title,

    @Schema(description = "공지사항 생성 시간")
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
