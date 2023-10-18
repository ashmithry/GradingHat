package com.example.cac2023.ui.new_paper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cac2023.databinding.FragmentNewPaperBinding;

public class NewPaperFragment extends Fragment {

    private FragmentNewPaperBinding binding;
    private Fragment parent;

    public NewPaperFragment(Fragment parent)
    {
        this.parent = parent;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NewPaperViewModel galleryViewModel =
                new ViewModelProvider(this).get(NewPaperViewModel.class);

        binding = FragmentNewPaperBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}