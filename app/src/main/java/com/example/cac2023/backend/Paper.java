package com.example.cac2023.backend;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class Paper
{
    private static final String filename = "data.json";
    public String document, rubric;
    public String rubricBreakdown, feedback, score;
    public LocalDateTime dateGraded;
    public Teacher teacher;
    public static JSONArray paperList;
    public static Paper lastPaper;
    public static JSONObject toJSON(Paper paper)
    {
        JSONObject object = new JSONObject();
        try {
            object.put("document", paper.document);
            object.put("rubric", paper.rubric);
            object.put("rubricBreakdown", paper.rubricBreakdown);
            object.put("dateGraded", paper.dateGraded.toString());
            object.put("teacher", paper.teacher.toJSON());
        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return object;
    }
    public static Paper fromJSON(JSONObject object)
    {
        Paper paper = new Paper();
        try {
            paper.document = object.getString("document");
            paper.rubric = object.getString("rubric");
            paper.rubricBreakdown = object.getString("rubricBreakdown");
            paper.dateGraded = LocalDateTime.parse(object.getString("dateGraded"));
            paper.teacher = new Teacher(object.getJSONObject("teacher"));
        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return paper;
    }
    public static void createPaper(String document, String rubric, Teacher teacher)
    {
        Paper paper = new Paper();
        paper.document = document;
        paper.rubric = rubric;
        paper.teacher = teacher;

        paper.Grade();

        lastPaper = paper;
        paperList.put(Paper.toJSON(paper));
    }
    public static void readPaperList(Context context)
    {
        JSONObject data = new JSONObject();
        try {
            FileInputStream fIS = context.openFileInput(filename);
            InputStreamReader isReader = new InputStreamReader(fIS);
            BufferedReader reader = new BufferedReader(isReader);

            StringBuilder jsonBuilder = new StringBuilder();
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                jsonBuilder.append(currentLine);
            }

            if (jsonBuilder.length() < 1) {
                jsonBuilder.append("{}");
            }

            data = new JSONObject(
                    new JSONTokener(jsonBuilder.toString())
            );

            reader.close();
            isReader.close();
            fIS.close();
        } catch (FileNotFoundException e) {
            try {
                FileOutputStream fOS = context.openFileOutput(filename, Context.MODE_PRIVATE);
                OutputStreamWriter osWriter = new OutputStreamWriter(fOS);
                BufferedWriter writer = new BufferedWriter(osWriter);

                writer.write("{}");

                writer.close();
                osWriter.close();
                fOS.close();
            } catch (Exception f) {
                f.printStackTrace();
                System.exit(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        if(data.has("paperList"))
        {
            try {
                paperList = data.getJSONArray("paperList");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } else
        {
            paperList = new JSONArray();
        }

        if(paperList.length() > 0)
        {
            try {
                lastPaper = Paper.fromJSON(paperList.getJSONObject(paperList.length() - 1));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } else {
            lastPaper = new Paper();
        }
    }
    public static void writePaperList(Context context)
    {
        try {
            JSONObject data = new JSONObject();
            data.put("paperList", paperList);

            FileOutputStream fOS = context.openFileOutput(filename, Context.MODE_PRIVATE);
            OutputStreamWriter osWriter = new OutputStreamWriter(fOS);
            BufferedWriter writer = new BufferedWriter(osWriter);

            writer.write(data.toString());

            writer.close();
            osWriter.close();
            fOS.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    public void Grade()
    {
        AtomicBoolean graded = new AtomicBoolean(false);
        Grader.GenerateFeedback(
                document, rubric, teacher.leniency,
                (rubricBreakdown) -> this.rubricBreakdown = rubricBreakdown,
                (feedback) -> this.feedback = feedback,
                (score) -> {
                    this.score = score;
                    graded.set(true);
                }
        );

        while(!graded.get());
        dateGraded = LocalDateTime.now();
    }

    public Paper()
    {
        document = "No Data";
        rubric = "N/A";
        rubricBreakdown = "N/A";
        feedback = "N/A";
        score = "0/100";
        dateGraded = LocalDateTime.now();
        teacher = new Teacher();

        teacher.name = "No Teacher";
        teacher.leniency = "Normal";
    }
}
