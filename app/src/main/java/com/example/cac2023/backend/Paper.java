package com.example.cac2023.backend;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class Paper
{
    public String document, rubric;
    public String rubricBreakdown, feedback, score;
    public LocalDateTime dateGraded;
    public int teacherIndex;
    public static JSONArray paperList;
    private static JSONObject toJSON(Paper paper)
    {
        JSONObject object = new JSONObject();
        try {
            object.put("document", paper.document);
            object.put("rubric", paper.rubric);
            object.put("score", paper.score);
            object.put("rubricBreakdown", paper.rubricBreakdown);
            object.put("dateGraded", paper.dateGraded.toString());
            object.put("teacherIndex", paper.teacherIndex);
        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return object;
    }
    private static Paper fromJSON(JSONObject object)
    {
        Paper paper = new Paper();
        try {
            paper.document = object.getString("document");
            paper.rubric = object.getString("rubric");
            paper.score = object.getString("score");
            paper.rubricBreakdown = object.getString("rubricBreakdown");
            paper.dateGraded = LocalDateTime.parse(object.getString("dateGraded"));
            paper.teacherIndex = object.getInt("teacherIndex");
        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return paper;
    }
    public static synchronized void createPaper(String document, String rubric, int teacherIndex)
    {
        new Thread(() -> {
            Paper paper = new Paper();
            paper.document = document;
            paper.rubric = rubric;
            paper.teacherIndex = teacherIndex;

            paper.Grade();

            paperList.put(Paper.toJSON(paper));
        }).start();
    }

    public static void readPaperList()
    {
        JSONObject data = IO.jsonData;

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
    }

    public static void savePaperList()
    {
        try {
            IO.jsonData.put("paperList", paperList);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void Grade()
    {
        AtomicBoolean graded = new AtomicBoolean(false);
        Grader.GenerateFeedback(
                document, rubric, Teacher.findTeacher(teacherIndex).leniency,
                (rubricBreakdown) -> this.rubricBreakdown = rubricBreakdown,
                (feedback) -> this.feedback = feedback,
                (score) -> {
                    this.score = score;
                    graded.set(true);
                }
        );

        while(!graded.get());
        dateGraded = LocalDateTime.now();

        Log.e("GRADER", score);
        Log.d("GRADER", feedback);
        Log.v("GRADER", rubricBreakdown);
    }

    private Paper()
    {
        document = "No Data\n";
        rubric = "N/A";
        rubricBreakdown = "N/A";
        feedback = "N/A";
        score = "0/100";
        dateGraded = LocalDateTime.now();
        teacherIndex = -1;
    }

    @NonNull @Override
    public String toString()
    {
        return "Title: " + getTitle() + "\tGrade: " + score + "\tDate Graded: " + dateGraded.toString();
    }

    public String getLetterGrade()
    {
        int numerator = Integer.parseInt(score.substring(0, score.indexOf('/')));
        int denominator = Integer.parseInt(score.substring(score.indexOf('/') + 1));

        double percent = ((double)numerator / denominator) * 100;

        if(percent < 60)
        {
            return "F";
        }

        if(percent < 63)
        {
            return "D-";
        }

        if(percent < 67)
        {
            return "D";
        }

        if(percent < 70)
        {
            return "D+";
        }

        if(percent < 73)
        {
            return "C-";
        }

        if(percent < 77)
        {
            return "C";
        }

        if(percent < 80)
        {
            return "C+";
        }

        if(percent < 83)
        {
            return "B-";
        }

        if(percent < 87)
        {
            return "B";
        }

        if(percent < 90)
        {
            return "B+";
        }

        if(percent < 93)
        {
            return "A-";
        }

        if(percent < 98)
        {
            return "A";
        }

        return "A+";
    }

    public String getTeacherName()
    {
        try {
            Teacher t = new Teacher(Teacher.teacherList.getJSONObject(teacherIndex));
            return t.name;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTitle()
    {
        if(document.indexOf('\n') == -1) return "N/A";
        return document.substring(0, document.indexOf('\n'));
    }
}
