package com.web.Bang.service;

import com.web.Bang.dto.queryDslDto.CustomServiceBoardDto;
import com.web.Bang.model.CustomServiceBoard;
import com.web.Bang.model.CustomServiceReply;
import com.web.Bang.repository.CSBoardRepository;
import com.web.Bang.repository.CSReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CSBoardServiceImpl implements CSBoardService {

    private final CSBoardRepository csBoardRepository;
    private final CSReplyRepository csReplyRepository;

    @Override
    @Transactional
    public void writeBoard(CustomServiceBoard customServiceBoard) {
        csBoardRepository.save(customServiceBoard);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomServiceBoardDto> findByTitle(String title, Pageable pageable) {
        return csBoardRepository.findByTitleContaining(title, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomServiceBoardDto> loadNoticeBoards() {
        return csBoardRepository.loadNoticeBoard();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomServiceBoard findCSboardByid(int id) {
        return csBoardRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 게시글를 찾을 수 없습니다."));
    }

    @Override
    @Transactional
    @Modifying
    public Boolean updateCsBoard(CustomServiceBoard board) {
        CustomServiceBoard tempBoard = csBoardRepository.findById(board.getId()).orElseThrow(() -> new RuntimeException("해당 게시글를 찾을 수 없습니다."));
        tempBoard.setTitle(board.getTitle());
        tempBoard.setContent(board.getContent());
        tempBoard.setSecret(board.getSecret());
        return true;
    }

    @Override
    @Transactional
    public void removeBoard(int id) {
        csBoardRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveReply(CustomServiceReply customServiceReply, int boardId) {
        CustomServiceBoard targetBoard = csBoardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("해당 게시글를 찾을 수 없습니다."));
        customServiceReply.setCustomServiceBoard(targetBoard);
        csReplyRepository.save(customServiceReply);
    }

    @Override
    @Transactional
    public void deleteReply(int replyid) {
        csReplyRepository.deleteById(replyid);
    }
}
