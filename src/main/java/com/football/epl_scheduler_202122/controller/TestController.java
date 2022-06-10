package com.football.epl_scheduler_202122.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class TestController {
    @GetMapping(value = "/")
    public String main() {
        return "index";
    }
}
