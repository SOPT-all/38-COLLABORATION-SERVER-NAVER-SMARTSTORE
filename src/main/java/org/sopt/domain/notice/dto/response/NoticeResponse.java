package org.sopt.domain.notice.dto.response;

import org.sopt.domain.notice.Notice;

import java.time.LocalDateTime;

public record NoticeResponse (
    Long id,
    String NoticeType,
    String title,
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
