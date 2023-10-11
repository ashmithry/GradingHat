package com.example.cac2023.backend;

import static com.theokanning.openai.service.OpenAiService.defaultClient;
import static com.theokanning.openai.service.OpenAiService.defaultObjectMapper;
import static com.theokanning.openai.service.OpenAiService.defaultRetrofit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.client.OpenAiApi;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;
import java.util.ArrayList;
import java.util.function.Consumer;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class APICaller
{
    private static final String API_KEY = "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";
    private ArrayList<ChatMessage> messageList;
    private OpenAiService service;
    public APICaller(String system_prompt)
    {
        ObjectMapper mapper = defaultObjectMapper();
        OkHttpClient client = defaultClient(API_KEY, Duration.ofSeconds(1000))
                .newBuilder()
                .build();
        Retrofit retrofit = defaultRetrofit(client, mapper);

        OpenAiApi api = retrofit.create(OpenAiApi.class);
        this.service = new OpenAiService(api);
        this.messageList = new ArrayList<>();

        messageList.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), system_prompt));
    }
    public synchronized void requestAPI(String prompt_data, int max_tokens, Consumer<String> reciever)
    {
        Thread t = new Thread(() ->
        {
            ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo-16k")
                .messages(messageList)
                .maxTokens(max_tokens)
                .temperature(0.0)
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

    public static int countTokens(String input)
    {
        int words = 1;
        for(int i = 0; i < input.length(); i++)
        {
            if(input.charAt(i) == ' ')
            {
                words++;
            }
        }

        return (int) (words * 1.3);
    }
}
