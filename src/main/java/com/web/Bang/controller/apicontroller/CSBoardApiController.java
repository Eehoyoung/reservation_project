package com.web.Bang.controller.apicontroller;

import com.web.Bang.auth.PrincipalDetail;
import com.web.Bang.dto.ResponseDto;
import com.web.Bang.model.CustomServiceBoard;
import com.web.Bang.model.CustomServiceReply;
import com.web.Bang.model.type.CSBoardType;
import com.web.Bang.model.type.RoleType;
import com.web.Bang.service.CSBoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CSBoardApiController {
    private final CSBoardServiceImpl csBoardService;

    @PostMapping("/cs-write")
    public ResponseDto<Integer> writeBoard(@AuthenticationPrincipal PrincipalDetail principal, @RequestBody CustomServiceBoard serviceBoard) {
        serviceBoard.setUser(principal.getUser());
        if (principal.getUser().getRole() == RoleType.ADMIN) {
            serviceBoard.setBoardType(CSBoardType.NOTICE);
        } else {
            serviceBoard.setBoardType(CSBoardType.NORMAL);
        }
        csBoardService.writeBoard(serviceBoard);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/cs-update")
    public ResponseDto<String> updateBoard(@AuthenticationPrincipal PrincipalDetail principal, @RequestBody CustomServiceBoard board) {
        CustomServiceBoard tempboard = csBoardService.findCSboardByid(board.getId());

        if (tempboard.getUser().getId() != principal.getUser().getId()) {
            return new ResponseDto<>(HttpStatus.FORBIDDEN.value(), "NO");
        }

        if (Boolean.TRUE.equals(csBoardService.updateCsBoard(board))) {
            return new ResponseDto<>(HttpStatus.OK.value(), "OK");
        } else {
            return new ResponseDto<>(HttpStatus.FORBIDDEN.value(), "NO");
        }
    }

    @DeleteMapping("/cs-delete")
    public ResponseDto<String> deleteBaord(@RequestParam(value = "boardId") int id, @AuthenticationPrincipal PrincipalDetail principal) {
        CustomServiceBoard tempboard = csBoardService.findCSboardByid(id);
        if (tempboard.getUser().getId() != principal.getUser().getId()) {
            return new ResponseDto<>(HttpStatus.FORBIDDEN.value(), "NO");
        }
        csBoardService.removeBoard(id);
        return new ResponseDto<>(HttpStatus.OK.value(), "OK");
    }

    @PostMapping("/cs-write/reply")
    public ResponseDto<CustomServiceReply> writeReply(@AuthenticationPrincipal PrincipalDetail principal,
                                                      @RequestBody CustomServiceReply customServiceReply,
                                                      @RequestParam(value = "boardId") int id) {
        if (principal.getUser().getRole() != RoleType.ADMIN) {
            return new ResponseDto<>(HttpStatus.FORBIDDEN.value(), null);
        }

        csBoardService.saveReply(customServiceReply, id);
        return new ResponseDto<>(HttpStatus.OK.value(), customServiceReply);
    }

    @DeleteMapping("/cs-delete/reply")
    public ResponseDto<Integer> deleteReply(@AuthenticationPrincipal PrincipalDetail principal, @RequestParam(value = "replyId") int id) {
        if (principal.getUser().getRole() != RoleType.ADMIN) {
            return new ResponseDto<>(HttpStatus.FORBIDDEN.value(), null);
        }
        csBoardService.deleteReply(id);
        return new ResponseDto<>(HttpStatus.OK.value(), id);
    }


}
