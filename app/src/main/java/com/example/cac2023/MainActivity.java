package com.example.cac2023;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.example.cac2023.backend.APICaller;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

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

    public String essay ="Immortality. Existential threat. Humanity’s last invention. The upcoming development and rise of the artificial intelligence (AI) singularity presents an interesting situation to mankind. The singularity, or also known as artificial super intelligence, is what will happen to AI when it achieves a higher intelligence than humans. Unlike the AI of today, artificial super intelligence, or ASI, wouldn’t be dependent on humans for support, as it would easily be able to improve itself and ‘code’ itself to get faster and smarter. This would lead to an exponential increase in the intelligence of this AI, until we can’t even comprehend it. As AI continues to grow, how will it impact the economy in the new era of the singularity? According to a variety of sources, the development of ASI would lead to the end of capitalism, because AI would displace job opportunities, lead to government bankruptcy, and outperform humans.\n";
    public String rubric = "Introduction, Thesis, & Conclusion\n" +
                    "\n" +
                    "\t\n" +
                    "10\n" +
                    "\n" +
                    "Includes all parts of Proficient, plus…\n" +
                    "*Hook & transition sentences (within intro) are engaging\n" +
                    "*Presents compelling background that skillfully introduces topic\n" +
                    "* Thesis is insightful that takes a position that is specific, arguable, and complex\n" +
                    "* Conclusion skillfully  and offers a possible solution";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        API = new APICaller(1000, "You are going to grade an essay and provide feedback based on the rubric and a teacher's leniency.");
        API.requestAPI("This is my Essay:" + essay + "\nThis is my rubric:" + rubric , System.out::println);
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
}