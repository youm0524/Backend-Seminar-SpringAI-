package com.example.demo.FunctionCall;

// ChatController - 외부 요청 API

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final CustomerService customerService;

    @PostMapping
    public String chat(@RequestBody Map<String, String> payload) {
        String message = payload.get("message");
        return customerService.askCustomerInfoByMessage(message);
    }
}

