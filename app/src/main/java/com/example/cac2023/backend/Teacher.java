package com.example.cac2023.backend;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Teacher
{
    public String name;
    public String leniency;
    public static JSONArray teacherList;
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
    private Teacher()
    {
        name = "Unknown";
        leniency = "Normal";
    }
    public static Teacher createTeacher(String name, String leniency)
    {
        Teacher teacher = new Teacher();
        teacher.name = name;
        teacher.leniency = leniency;

        teacherList.put(teacher.toJSON());
        return teacher;
    }
    public static void readTeacherList()
    {
        JSONObject data = IO.jsonData;

        if(data.has("teacherList"))
        {
            try {
                teacherList = data.getJSONArray("teacherList");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } else
        {
            teacherList = new JSONArray();
        }
    }
    public static void saveTeacherList()
    {
        try {
            IO.jsonData.put("teacherList", teacherList);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static Teacher findTeacher(int index)
    {
        if(!(index < teacherList.length() && index > 0)) return null;
        try {
            return new Teacher(teacherList.getJSONObject(index));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
