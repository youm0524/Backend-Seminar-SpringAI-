package com.example.demo.Advisor;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class AdvisorService {
    private final ChatClient.Builder chatClient;

    public String useAdvisor(String message){
//
        ChatClient client = chatClient
                .defaultAdvisors(
                        new SimpleLoggerAdvisor(1),
                        new LoggingAdvisor(),
                        new ReReadingAdvisor())
                .build();

        Prompt prompt = new Prompt(
                List.of(
                        new SystemMessage("You are a kind AI assistant.\n" +
                                "Do not infer; just provide the results. Also, always mask any BlockedKeyword."),
                        new UserMessage( message )
                ));
        // 🚀 한 번만 호출 → 그리고 바로 BlockedKeywordAdvisor 적용
        String response = client.prompt(prompt)
                .advisors(new BlockedKeywordAdvisor()) // 바로 후처리 마스킹
                .call()
                .content();
        return response;
    }
}
