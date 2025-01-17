package com.crash_point.server.round;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoundController {
    private final RoundService roundService;

    @GetMapping("test")
    public void test(){
        roundService.createRound();
    }
}
