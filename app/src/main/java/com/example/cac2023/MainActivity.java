package com.example.cac2023;

import android.os.Bundle;
import android.view.Menu;

import com.example.cac2023.backend.APICaller;
import com.example.cac2023.backend.IO;
import com.example.cac2023.backend.Paper;
import com.example.cac2023.backend.Rubric;
import com.example.cac2023.backend.Teacher;
import com.example.cac2023.ui.new_paper.NewPaperFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cac2023.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    public static APICaller API;
    public static FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dashboard, R.id.nav_papers, R.id.nav_teachers, R.id.nav_rubrics)
                .setOpenableLayout(drawer)
                .build();
        fab = binding.appBarMain.fab;

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        IO.getJSONFile(getApplicationContext());
        Paper.readPaperList();
        Rubric.readRubricList();
        Teacher.readTeacherList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Paper.savePaperList();
        Rubric.saveRubricList();
        Teacher.saveTeacherList();
        IO.writeJSONFile(getApplicationContext());
    }

    public static void swapFragments(Fragment currentFragment, Fragment newFragment) {
        FragmentTransaction fragmentTransaction = currentFragment.getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_content_main, newFragment);
        fragmentTransaction.addToBackStack("Home");
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commit();
    }
}