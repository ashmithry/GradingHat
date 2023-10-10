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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class APICaller
{
    private static final String URL = "https://api.openai.com/v1/chat/completions";
    private boolean responseReady;
    private String response;
    private Context applicationContext;
    public APICaller(Context applicationContext)
    {
        this.applicationContext = applicationContext;
    }
    public void queryResponse(JSONArray messages)
    {
        responseReady = false;
        RequestQueue queue = Volley.newRequestQueue(applicationContext);
        JSONObject data = new JSONObject();

        try {
            data.put("model", "gpt-3.5-turbo");
            data.put("messages", messages);
        } catch (JSONException e)
        {
            Log.e("APICaller (Backend)", "Exception when writing to JSON: " + e.getMessage());
            return;
        }

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URL, data,
                (responseData) ->
                {
                    try {
                        response = responseData.
                                getJSONArray("choices").
                                getJSONObject(0).
                                getJSONObject("message").
                                getString("content");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    responseReady = true;
                }, (error) ->
                {
                    Log.e("APICaller (Backend)", "Unable to get data from API: \n" + error.getMessage());
                });
    }

    public String readResponse()
    {
        if(!responseReady) return null;

        responseReady = false;
        return response;
    }

    public boolean isResponseReady()
    {
        return responseReady;
    }

    private static class APIRequest extends JsonObjectRequest
    {
        public APIRequest(int method, String url, @Nullable JSONObject jsonRequest, Response.Listener<JSONObject> listener, @Nullable Response.ErrorListener errorListener) {
            super(method, url, jsonRequest, listener, errorListener);
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> params = new HashMap<>();
            params.put("Content-type", "application/json");
            params.put("Authorization", "Bearer Enter your token here");

            return params;
        }
    }
}
