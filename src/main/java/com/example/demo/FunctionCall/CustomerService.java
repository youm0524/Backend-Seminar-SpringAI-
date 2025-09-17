package com.example.demo.FunctionCall;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final ChatClient chatClient;
    private final CustomerTool customerTools;

    public String askCustomerInfoByMessage(String message) {

        String systemPrompt = """
                You are an assistant with access to customer tools. Use these rules:
                - If the user asks about customer information by ID or name, call the corresponding tool exactly with needed parameters.
                - For any other question, do not call any tool. Instead, answer directly.
                - Do not guess or call tools unnecessarily.
                """;

        var callResponse = chatClient
                .prompt()
                .system(systemPrompt)
                .user(message)
                .call();

        String content = callResponse.content();
        boolean toolUsed = content.contains("\"tool_call\"") || content.contains("<tool-use>");

        if (toolUsed) {
            // Tool 호출 결과 처리
            return content;
        } else {
            // 일반 LLM 응답 처리
            return content;
        }

    }
}

