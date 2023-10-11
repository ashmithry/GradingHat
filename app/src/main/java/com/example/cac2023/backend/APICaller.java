package com.example.cac2023.backend;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import java.util.ArrayList;
import java.util.function.Consumer;

public class APICaller
{
    private static final String API_KEY = "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";
    private ArrayList<ChatMessage> messageList;
    private OpenAiService service;
    private int max_tokens;
    public APICaller(int max_tokens, String system_prompt)
    {
        this.service = new OpenAiService(API_KEY);
        this.max_tokens = max_tokens;
        this.messageList = new ArrayList<>();

        messageList.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), system_prompt));
    }
    public synchronized void requestAPI(String prompt_data, Consumer<String> reciever)
    {
        Thread t = new Thread(() ->
        {
            ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(messageList)
                .maxTokens(max_tokens)
                .build();

            ChatMessage response = service.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage();
            messageList.add(response);

            reciever.accept(response.getContent());
        });

        ChatMessage prompt = new ChatMessage(ChatMessageRole.USER.value(), prompt_data);
        messageList.add(prompt);

        t.start();
    }

    public ArrayList<ChatMessage> getMessageList()
    {
        return messageList;
    }
}
