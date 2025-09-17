package com.example.demo.FunctionCall;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.execution.DefaultToolExecutionExceptionProcessor;
import org.springframework.ai.tool.execution.ToolExecutionExceptionProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AiConfig {

    @Bean
    public ChatClient chatClient(ChatModel openAIChatModel,
                                 CustomerTool customerTool) {
        return ChatClient.builder(openAIChatModel)
                .defaultSystem("You have access to the following tools: getCustomerById\n" +
                        "Only call a tool if the user explicitly asks for customer information by ID.\n" +
                        "If the question is unrelated, answer directly without calling any tool.\n" +
                        "Do not make up or guess tool calls.\n")
                .defaultTools(customerTool)
                .build();
    }

    @Bean
    ToolExecutionExceptionProcessor toolExecutionExceptionProcessor() {
        return new DefaultToolExecutionExceptionProcessor(false);
    }
}

