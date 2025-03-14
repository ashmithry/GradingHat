package com.example.cac2023.ui.teachers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cac2023.databinding.FragmentTeachersBinding;
import com.example.cac2023.ui.papers.PaperAdapter;

public class TeachersFragment extends Fragment {

    private FragmentTeachersBinding binding;
    private RecyclerView.LayoutManager layoutMgr;
    private RecyclerView rv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTeachersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        rv = binding.teachersListRecycler;

        layoutMgr = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutMgr);
        rv.setAdapter(new TeachersAdapter());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}