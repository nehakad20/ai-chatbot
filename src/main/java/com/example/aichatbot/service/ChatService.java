package com.example.aichatbot.service;

import com.example.aichatbot.model.ChatMessage;
import com.example.aichatbot.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final OpenAIService openAIService;

    public ChatService(ChatRepository chatRepository, OpenAIService openAIService) {
        this.chatRepository = chatRepository;
        this.openAIService = openAIService;
    }

    public ChatMessage processMessage(String userMessage) {
        String botResponse = openAIService.getChatResponse(userMessage);
        ChatMessage chat = new ChatMessage(userMessage, botResponse);
        return chatRepository.save(chat);
    }

    public List<ChatMessage> getAllChats() {
        return chatRepository.findAll();
    }
}
