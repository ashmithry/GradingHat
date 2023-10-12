package com.example.cac2023.backend;

import org.json.JSONException;
import org.json.JSONObject;

public class Teacher
{
    public String name;
    public String leniency;
    public JSONObject toJSON()
    {
        JSONObject data = new JSONObject();
        try {
            data.put("name", name);
            data.put("leniency", leniency);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return data;
    }
    public Teacher(JSONObject data)
    {
        try {
            name = data.getString("name");
            leniency = data.getString("leniency");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public Teacher()
    {
        name = "Unknown";
        leniency = "Normal";
    }

    public Teacher(String name, String leniency)
    {
        this.name = name;
        this.leniency = leniency;
    }
}
