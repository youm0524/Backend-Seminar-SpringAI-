package com.example.demo.Advisor;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdvisorController {
    private final AdvisorService advisorService;

    @GetMapping("/advisor")
    public String advisor(@RequestParam("message") String message) { return advisorService.useAdvisor(message);}

}
