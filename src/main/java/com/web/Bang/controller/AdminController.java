package com.web.Bang.controller;

import com.web.Bang.model.User;
import com.web.Bang.service.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminServiceImpl adminService;

    @GetMapping("/admin/user-management")
    public String adminForm(@RequestParam Map<String, String> map, Model model) {

        String role = map.get("role") == null ? "" : map.get("role");
        String q = map.get("q") == null ? "" : map.get("q");
        System.out.println(role + "/" + q);
        List<User> user;
        if (role.isEmpty()) {
            try {
                user = adminService.searchUserOnly(q);
                model.addAttribute("users", user);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                user = adminService.searchRoleAndUser(role, q);
                model.addAttribute("users", user);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return "admin/admin_form";
    }

    @GetMapping("/admin/show-statistics")
    public String getAdminTable() {
        return "/user/admin_table";
    }

}

