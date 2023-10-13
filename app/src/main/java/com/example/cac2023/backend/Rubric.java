package com.example.cac2023.backend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Rubric
{
    public String data;
    public static JSONArray rubricList;
    public static void saveRubricList()
    {
        try {
            IO.jsonData.put("rubricList", rubricList);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readRubricList()
    {
        JSONObject data = IO.jsonData;

        if(data.has("rubricList"))
        {
            try {
                rubricList = data.getJSONArray("rubricList");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } else
        {
            rubricList = new JSONArray();
        }
    }

    public static Rubric findRubric(int index)
    {
        if(!(index < rubricList.length() && index > 0)) return new Rubric();
        try {
            return new Rubric(rubricList.getString(index));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public Rubric(String data)
    {
        this.data = data;
    }

    public Rubric()
    {
        this.data = "N/A";
    }
}
