package com.example.cac2023.backend;

import android.content.Context;
import android.util.Log;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class APICaller
{
    private static final String API_KEY = "sk-srlRSbK46H3R0kMSKCA7xT3BlbkFJfMlmCMk0yz7l0STRi2dt";
    private List<ChatMessage> messageList;
    private OpenAiService service;
    private int max_tokens;
    public APICaller(int max_tokens, String system_prompt)
    {
        this.service = new OpenAiService(API_KEY);
        this.max_tokens = max_tokens;
        this.messageList = new ArrayList<>();

        messageList.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), system_prompt));
    }
    public synchronized String requestAPI(String prompt_data)
    {
        ChatMessage prompt = new ChatMessage(ChatMessageRole.USER.value(), prompt_data);

        messageList.add(prompt);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo-0613")
                .messages(messageList)
                .maxTokens(max_tokens)
                .build();

        ChatMessage response = service.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage();
        messageList.add(response);

        return response.getContent();
    }

    public List<ChatMessage> getMessageList()
    {
        return messageList;
    }
}
