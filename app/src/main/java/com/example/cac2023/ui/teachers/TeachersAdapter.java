package com.example.cac2023.ui.teachers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cac2023.R;
import com.example.cac2023.backend.Paper;
import com.example.cac2023.backend.Teacher;

import org.apache.poi.ss.formula.functions.T;
import org.json.JSONException;

import java.util.ArrayList;

public class TeachersAdapter extends RecyclerView.Adapter<TeachersAdapter.TeacherHolder> {

    public ArrayList<Teacher> teachers;
    public static class TeacherHolder extends RecyclerView.ViewHolder {
        public final TextView teacherName, teacherLeniency;
        public final Button editButton;

        public TeacherHolder(View view) {
            super(view);
            teacherName = view.findViewById(R.id.teachers_recycler_teacher_text);
            teacherLeniency= view.findViewById(R.id.teachers_recycler_teacher_leniency);
            editButton = view.findViewById(R.id.teachers_recycler_edit_button);
        }

    }

    public TeachersAdapter()
    {
        teachers = new ArrayList<>();

        for(int i = 0; i < Teacher.teacherList.length(); i++)
        {
            Teacher t = null;
            try {
                t = new Teacher(Teacher.teacherList.getJSONObject(i));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            teachers.add(t);
        }

        if(teachers.size() == 0)
        {
            teachers.add(Teacher.createTeacher("Mr. Allshouse", "Strict"));
        }
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public TeacherHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.teacher_recycler_layout, viewGroup, false);

        return new TeacherHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull TeacherHolder viewHolder, final int position)
    {
        viewHolder.teacherName.setText(teachers.get(position).name);
        viewHolder.teacherLeniency.setText(teachers.get(position).leniency);
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return teachers.size();
    }
}
