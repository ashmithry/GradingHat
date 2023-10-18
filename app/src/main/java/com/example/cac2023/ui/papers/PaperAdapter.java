package com.example.cac2023.ui.papers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cac2023.R;
import com.example.cac2023.backend.Paper;

import org.json.JSONException;

import java.util.ArrayList;

public class PaperAdapter extends RecyclerView.Adapter<PaperAdapter.PaperHolder> {

    public ArrayList<String> teachers, papers, grades;

    public static class PaperHolder extends RecyclerView.ViewHolder {
        public final TextView titleText, teacherText, gradeText;
        public final Button detailsButton;

        public PaperHolder(View view) {
            super(view);
            titleText = view.findViewById(R.id.paper_recycler_title_text);
            teacherText = view.findViewById(R.id.paper_recycler_teacher_text);
            gradeText = view.findViewById(R.id.paper_recycler_grade_text);
            detailsButton = view.findViewById(R.id.paper_recycler_details_button);
        }

    }

    public PaperAdapter()
    {
        grades = new ArrayList<>();
        papers = new ArrayList<>();
        teachers = new ArrayList<>();
        for(int i = 0; i < Paper.paperList.length(); i++)
        {
            Paper p = null;
            try {
                p = Paper.fromJSON(Paper.paperList.getJSONObject(i));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            grades.add(p.getLetterGrade());
            papers.add(p.getTitle());
            papers.add(p.getTeacherName());
        }

        if(grades.size() == 0)
        {
            grades.add("B+");
            teachers.add("Mr. Allshouse");
            papers.add("Lab Report 1");


            grades.add("A+");
            teachers.add("Mr. Allshouse");
            papers.add("Lab Report 2");
        }
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public PaperHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.paper_recycler_layout, viewGroup, false);

        return new PaperHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull PaperHolder viewHolder, final int position) {

        viewHolder.gradeText.setText(grades.get(position));
        viewHolder.teacherText.setText(teachers.get(position));
        viewHolder.titleText.setText(papers.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return grades.size();
    }
}
