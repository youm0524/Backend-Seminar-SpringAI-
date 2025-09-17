package com.example.demo.Advisor;

import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;

import java.util.List;

public class PromptModel {

    private final Prompt prompt;

    public PromptModel(String message) {
        Prompt prompt = new Prompt(
                List.of(
                        new SystemMessage("너는 친절한 AI비서야.  \n" +
                                "추론하지말고 결과를 이야기해줘.\n"),
                        new UserMessage( message )
                ));

        this.prompt = prompt;
    }

    public Prompt getPrompt() {
        return prompt;
    }
}
