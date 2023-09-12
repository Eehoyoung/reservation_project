package com.web.Bang.service;

import com.web.Bang.dto.queryDslDto.CustomServiceBoardDto;
import com.web.Bang.model.CustomServiceBoard;
import com.web.Bang.model.CustomServiceReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CSBoardService {
    void writeBoard(CustomServiceBoard customServiceBoard);

    Page<CustomServiceBoardDto> findByTitle(String title, Pageable pageable);

    List<CustomServiceBoardDto> loadNoticeBoards();

    CustomServiceBoard findCSboardByid(int id);

    Boolean updateCsBoard(CustomServiceBoard board);

    void removeBoard(int id);

    void saveReply(CustomServiceReply customServiceReply, int boardId);

    void deleteReply(int replyid);
}
