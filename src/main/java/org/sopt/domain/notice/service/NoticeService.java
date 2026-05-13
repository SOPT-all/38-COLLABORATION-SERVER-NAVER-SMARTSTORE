package org.sopt.domain.notice.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.notice.dto.response.NoticeResponse;
import org.sopt.domain.notice.repository.NoticeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    public List<NoticeResponse> getAllNotices(){
        return noticeRepository.findAll().stream()
                .map(NoticeResponse::from)
                .toList();
    }
}
