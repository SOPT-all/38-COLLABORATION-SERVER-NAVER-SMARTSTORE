package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.dto.response.NoticeResponse;
import org.sopt.repository.NoticeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeService {

  private final NoticeRepository noticeRepository;

  @Transactional(readOnly = true)
  public List<NoticeResponse> getAllNotices() {
    return noticeRepository.findAll().stream()
        .map(NoticeResponse::from)
        .toList();
  }
}
