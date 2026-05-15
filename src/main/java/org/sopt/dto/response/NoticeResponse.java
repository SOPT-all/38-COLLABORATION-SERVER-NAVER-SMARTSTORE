package org.sopt.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.sopt.domain.notice.Notice;

import java.time.LocalDateTime;

@Builder
@Schema(description = "공지사항 응답 DTO")
public record NoticeResponse(
    @Schema(description = "공지사항 ID")
    Long id,

    @Schema(description = "공지사항 타입")
    String noticeType,

    @Schema(description = "공지사항 제목")
    String title,

    @Schema(description = "공지사항 생성 시간")
    LocalDateTime createdAt
) {
  public static NoticeResponse from(Notice notice) {
    return NoticeResponse.builder()
        .id(notice.getId())
        .noticeType(notice.getNoticeType())
        .title(notice.getTitle())
        .createdAt(notice.getCreatedAt())
        .build();
  }
}
