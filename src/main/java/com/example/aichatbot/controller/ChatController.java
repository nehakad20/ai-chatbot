package com.example.aichatbot.controller;

import com.example.aichatbot.model.ChatMessage;
import com.example.aichatbot.service.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) { this.chatService = chatService; }

    @PostMapping
    public ChatMessage sendMessage(@RequestBody Map<String, String> payload) {
        String message = payload.get("message");
        return chatService.processMessage(message);
    }

    @GetMapping
    public List<ChatMessage> getAllChats() {
        return chatService.getAllChats();
    }
}
