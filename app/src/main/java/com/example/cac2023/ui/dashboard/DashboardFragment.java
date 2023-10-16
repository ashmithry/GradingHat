package com.example.cac2023.ui.dashboard;

import static com.example.cac2023.MainActivity.swapFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cac2023.MainActivity;
import com.example.cac2023.databinding.FragmentDashboardBinding;
import com.example.cac2023.ui.new_paper.NewPaperFragment;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel homeViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        new Thread(() -> {
            while(MainActivity.fab == null);

            MainActivity.fab.setOnClickListener(view ->
            swapFragments(this, new NewPaperFragment(this)));}).start();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}