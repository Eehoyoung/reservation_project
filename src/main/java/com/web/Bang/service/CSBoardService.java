package com.web.Bang.service;

import com.web.Bang.model.CustomServiceBoard;
import com.web.Bang.model.CustomServiceReply;
import com.web.Bang.repository.CSBoardRepository;
import com.web.Bang.repository.CSReplyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CSBoardService {
    private final CSBoardRepository csBoardRepository;
    private final CSReplyRepository csReplyRepository;

    public CSBoardService(CSBoardRepository csBoardRepository, CSReplyRepository csReplyRepository) {
        this.csBoardRepository = csBoardRepository;
        this.csReplyRepository = csReplyRepository;
    }

    @Transactional
    public void writeBoard(CustomServiceBoard customServiceBoard) {
        csBoardRepository.save(customServiceBoard);
    }

    @Transactional(readOnly = true)
    public Page<CustomServiceBoard> findByTitle(String title, Pageable pageable) {
        return csBoardRepository.findByTitleContaining(title, pageable);
    }

    @Transactional(readOnly = true)
    public List<CustomServiceBoard> loadNoticeBoards() {
        return csBoardRepository.loadNoticeBoard();
    }

    @Transactional(readOnly = true)
    public CustomServiceBoard findCSboardByid(int id) {
        return csBoardRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 게시글를 찾을 수 없습니다."));
    }

    @Transactional
    @Modifying
    public Boolean updateCsBoard(CustomServiceBoard board) {
        CustomServiceBoard tempBoard = csBoardRepository.findById(board.getId()).orElseThrow(() -> new RuntimeException("해당 게시글를 찾을 수 없습니다."));
        tempBoard.setTitle(board.getTitle());
        tempBoard.setContent(board.getContent());
        tempBoard.setSecret(board.getSecret());
        return true;
    }

    @Transactional
    public boolean removeBoard(int id) {
        csBoardRepository.deleteById(id);
        return true;
    }

    @Transactional
    public CustomServiceReply saveReply(CustomServiceReply customServiceReply, int boardId) {
        CustomServiceBoard targetBoard = csBoardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("해당 게시글를 찾을 수 없습니다."));
        customServiceReply.setCustomServiceBoard(targetBoard);
        csReplyRepository.save(customServiceReply);
        return customServiceReply;
    }

    @Transactional
    public void deleteReply(int replyid) {
        csReplyRepository.deleteById(replyid);
    }
}
