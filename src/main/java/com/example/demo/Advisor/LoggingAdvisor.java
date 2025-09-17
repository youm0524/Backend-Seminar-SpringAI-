package com.example.demo.Advisor;


import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;

/**
 * LLM 호출 전/후 요청·응답을 콘솔에 로깅하는 Advisor
 */
public class LoggingAdvisor implements CallAdvisor {

    private int order = 0;

    public LoggingAdvisor() {}
    public LoggingAdvisor(int order) {
        this.order = order;
    }

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest,
                                         CallAdvisorChain callAdvisorChain) {
        // ✅ 요청 로깅
        System.out.println("📩 [LoggingAdvisor] BEFORE: " + chatClientRequest);

        // 실제 모델 호출
        ChatClientResponse response = callAdvisorChain.nextCall(chatClientRequest);

        // ✅ 응답 로깅
        System.out.println("🤖 [LoggingAdvisor] AFTER: " + response.chatResponse());

        return response;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getOrder() {
        return this.order;
    }
}
