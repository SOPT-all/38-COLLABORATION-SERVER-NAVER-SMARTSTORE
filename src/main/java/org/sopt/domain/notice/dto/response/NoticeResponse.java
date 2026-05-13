package org.sopt.domain.notice.dto.response;

import org.sopt.domain.notice.Notice;

import java.time.LocalDateTime;

public record NoticeResponse (
    Long id,
    String NoticeType,
    String title,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
){
    public static NoticeResponse from(Notice notice){
        return new NoticeResponse(
                notice.getId(),
                notice.getNoticeType(),
                notice.getTitle(),
                notice.getCreatedAt(),
                notice.getUpdatedAt()
        );
    }
}
