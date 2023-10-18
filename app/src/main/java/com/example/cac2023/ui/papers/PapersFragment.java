package com.example.cac2023.ui.papers;

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

import com.example.cac2023.databinding.FragmentPapersBinding;

public class PapersFragment extends Fragment {

    private FragmentPapersBinding binding;
    private RecyclerView.LayoutManager layoutMgr;
    private RecyclerView rv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPapersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        rv = binding.paperListRecycler;

        layoutMgr = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutMgr);
        rv.setAdapter(new PaperAdapter());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}