package com.web.Bang.service;

import com.web.Bang.model.CustomServiceBoard;
import com.web.Bang.model.CustomServiceReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CSBoardService {
    void writeBoard(CustomServiceBoard customServiceBoard);

    Page<CustomServiceBoard> findByTitle(String title, Pageable pageable);

    List<CustomServiceBoard> loadNoticeBoards();

    CustomServiceBoard findCSboardByid(int id);

    Boolean updateCsBoard(CustomServiceBoard board);

    void removeBoard(int id);

    void saveReply(CustomServiceReply customServiceReply, int boardId);

    void deleteReply(int replyid);
}
