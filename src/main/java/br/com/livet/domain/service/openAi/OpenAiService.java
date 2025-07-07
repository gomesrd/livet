package br.com.livet.domain.service.openAi;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class OpenAiService {

    private final ChatClient chatClient;

    public OpenAiService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public ChatResponse getPromptResponse() {
        return chatClient.prompt("Diga oi")
                .call()
                .chatResponse();
    }
}