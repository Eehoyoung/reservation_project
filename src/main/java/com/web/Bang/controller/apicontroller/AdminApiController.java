package com.web.Bang.controller.apicontroller;

import com.web.Bang.dto.ResponseDto;
import com.web.Bang.dto.adminDto.AdmintableDto;
import com.web.Bang.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AdminApiController {

    private final UserServiceImpl userService;

    @GetMapping("/admin/adminTable")
    public ResponseDto<Map<String, List<AdmintableDto>>> loadTableData(@RequestParam Map<String, String> data) {
        Map<String, List<AdmintableDto>> maps = new LinkedMultiValueMap<>();
        switch (data.get("index")) {
            case "0":
                maps.put("user", userService.loadMonthTableCount("user"));
                maps.put("house", userService.loadMonthTableCount("house"));
                maps.put("reservation", userService.loadMonthTableCount("reservation"));
                maps.put("review", userService.loadMonthTableCount("review"));
                return new ResponseDto<>(HttpStatus.OK.value(), maps);
            case "1":
                maps.put("address", userService.loadAddressHouseCount());
                return new ResponseDto<>(HttpStatus.OK.value(), maps);
            default:
                maps.put("best", userService.loadHouseDtolist(data.get("month"), data.get("limit")));
                return new ResponseDto<>(HttpStatus.OK.value(), maps);
        }
    }

    @DeleteMapping("/admin/user/delete/{id}")
    public ResponseDto<Integer> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }
}
