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

import java.util.ArrayList;

public class PaperAdapter extends RecyclerView.Adapter<PaperAdapter.PaperHolder> {

    public ArrayList<Paper> paperList;

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

        Paper p = paperList.get(position);
        viewHolder.gradeText.setText(p.getLetterGrade());
        viewHolder.teacherText.setText(p.getTeacherName());
        viewHolder.titleText.setText(p.getTitle());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return paperList.size();
    }
}
