package com.example.cac2023;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;

import com.example.cac2023.backend.APICaller;
import com.example.cac2023.backend.Grader;
import com.example.cac2023.backend.Paper;
import com.example.cac2023.backend.Teacher;
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
    public String essay = "Identifying Unknown Metal Ions with Their Emissions After Being Heated\n" +
            "\n" +
            "\n" +
            " \n" +
            "\n" +
            "\n" +
            "Taha Rawjani\n" +
            "9/30/2023\n" +
            "AET Chemistry, Block 5\n" +
            "Mr. Allshouse\n" +
            "On my honor, I will not accept or provide any unauthorized aid on any test, quiz, or assignment. \n" +
            "TABLE OF CONTENTS\n" +
            "\n" +
            "TABLE OF CONTENTS\n" +
            "\n" +
            "INTRODUCTION\n" +
            "Purpose\n" +
            "Hypothesis\n" +
            "Background\n" +
            "\n" +
            "METHODS\n" +
            "Materials\n" +
            "Procedures\n" +
            "\n" +
            "RESULTS\n" +
            "Data\n" +
            "Calculations and Graphs\n" +
            "\n" +
            "DISCUSSION\n" +
            "Conclusion\n" +
            "Errors\n" +
            "Post-Lab Questions \n" +
            "Extensions\n" +
            "REFERENCES\n" +
            "APPENDIX\n" +
            " \n" +
            "INTRODUCTION\n" +
            "Purpose\n" +
            "The purpose of the lab will be to observe the color of light emitted by heating different metal ions, estimating their wavelengths and frequencies, and then using that data to identify unknown metal samples.\n" +
            "Hypothesis\n" +
            "The various metal ions tested in this lab will be accurately identified based upon their emission spectrum.\n" +
            "Background\n" +
            "A Bunsen burner is a gas burner that is used in laboratories, it produces a gas flame of variable size and heat and is usually used for heating and combustion (Wikipedia, 2023; Heney, 2022). Most Bunsen burners today use a gas supply of natural gas (methane) or a liquified petroleum gas. The most common ways to ignite the flame in a Bunsen burner are either by using a spark lighter or a match (Wikipedia, 2023). Observe figure 1 for a labeled diagram of a Bunsen burner. \n" +
            " \n" +
            "Figure 1. Labeled Diagram of a Bunsen Burner (Heney, 2022)\n" +
            "\tBunsen burners can be adjusted in multiple ways, allowing the experimenter to control the size and type of flame. The collar at the bottom of the Bunsen burner can be set to a large opening or a small opening. If the opening is large, more air can diffuse with the gas before combustion, allowing the flame to burn hotter and in a blueish color. However, if the opening at the bottom is set to be small or is closed, the flame that is emitted is going to be much brighter, cooler and have a yellowish color (Heney, 2022). Most laboratory experiments preferably should be done on the blue flame because the bright-yellow flame leaves a layer of carbon on whatever it heats, and it is regarded as a “dirty” flame (Wikipedia, 2023). Another way that Bunsen burners can be adjusted is through adjusting the amount of gas goes in by opening the needle valve. The more gas that is inside the burner, the higher the flame will be (Wikipedia, 2023). \n" +
            "\tSince Bunsen burners produce a flame, they should be treated very carefully and cautiously. Here are some safety precautions and advice for using Bunsen burners in any experiment (USC, 2023):\n" +
            "-\tKnow the location of the nearest fire extinguisher and know how to use a fire extinguisher.\n" +
            "-\tWear appropriate eye protection (goggles), a flame-resistant lab coat, and heat-insulating gloves when interacting with Bunsen burners.\n" +
            "-\tKeep personal belongings and clothing away: Don’t wear jewelry, long-sleeves or have long-hair\n" +
            "-\tRemove all combustible and flammable materials away from the flame before using the Bunsen burner.\n" +
            "-\tMake sure the gas tubing is secure and tight\n" +
            "-\tKeep the flame yellow or visible when not in use, and never reach over the flame to get something\n" +
            "Matter is defined as anything that has mass and volume. The smallest unit of matter are atoms, and one atom contains different types of subatomic particles. These subatomic particles consist of positively charged protons, neutrally charged neutrons, and negatively charged electrons. The atom is structurally composed of a positively charged nucleus in the center and a negatively charged electron cloud orbiting it. The nucleus is made up of protons and neutrons, and it holds most of the mass of the atom (Madison Independent Learning Academy, 2023). Refer to figure 2 for a labeled diagram of the parts of an atom.\n" +
            "An element is a sample of mater that is made entirely of atoms with the same number of protons. This means that some atoms in an element can have different numbers of neutrons. These atoms are known as isotopes of the element. On top of that, atoms in an element can have different charges. This could happen if an element lost or gained electrons from another element. When this exchange of electrons occurs between two atoms, they become ions, or charged atoms. Ions with a positive charge are called cations, and ions with a negative charge are called anions.\n" +
            "\t\n" +
            " \n" +
            "Figure 2. Labeled Diagram of the Parts of an Atom (Madison Independent Learning Academy, 2023)\n" +
            "The elements are organized in the periodic table, as shown in figure 3. Every row in the periodic table is called a period, and every column is called a group. The periodic table is organized in a way that it puts all atoms with the same amount of valence electrons in the same group. Valence electrons is the number of electrons that are in the highest energy ring of the atom. As a result, many of the elements from the same group react similarly, but they differ in reactivity. The number of valence electrons also results in atoms exchanging electrons with each other, because atoms want to keep their valence shell full. They can either donate an electron, which can help them lost their valence shell, or steal an electron. When these exchanges happen, the two atoms get opposite charges, one becomes positive, and one becomes negative. This results in the two atoms bonding together and forming an ionic bond and compound.\n" +
            "Light is emitted by the releasing of energy by an electron. This can happen when the electron loses electric potential energy by going closer to the center of an atom, which is positively charged due to protons. The energy that is lost gets converted into a photon or light particle that travels at a frequency based on how much energy was gained or lost. The formula for frequency is f = c/y, where c is the speed of light (constant), and y is the wavelength of the light. This means that the frequency of a photon is inversely proportional to its wavelength, meaning that the more electric potential an electron loses, the smaller the wavelength and the higher the frequency of the emitted photon (Allshouse, 2023). \n" +
            "This behavior of electrons allows certain elements to only emit certain wavelengths and colors of light. Since wavelengths of light can be related to visible colors, as shown in Table 1, it is possible to measure the hue an element emits to identify it (Allshouse, 2023). The hue that an element emits can be measured with a spectroscope, which allows one to observe the spectrum of light something emits, or just by using a table that converts observable light to wavelengths. Elements can be identified with their emissions because every element emits a specific portion of the visible spectrum, based on the specific interactions between their electrons’ arrangements. In the visible spectrum, waves with higher frequencies are usually found in the violet-blue area, and waves with lower frequencies are usually found in the red-yellow area. A labeled diagram of the visible spectrum can be found in Figure 4 (Britannica, 2023; Allshouse, 2023). \n" +
            "Table 1. Wavelength of Light vs Flame Color\n" +
            "Flame Color\tWavelength of Light (nm)\n" +
            "Pink-Violet (Lavender/Lilac)\t400.\n" +
            "Silver*\t  425*\n" +
            "Green Blue (Emerald)\t500.\n" +
            "Yellow White\t530.\n" +
            "Yellow Green\t550.\n" +
            "Golden Yellow\t580.\n" +
            "Bright Orange\t600.\n" +
            "Orange Red\t620.\n" +
            "Orange\t625\n" +
            "Red\t700.\n" +
            "Dark Red\t715\n" +
            "Note: Silver is made up of multiple wavelengths, but this lab will only use one for simplicity.\n" +
            " \n" +
            "Figure 3. Periodic Table of Elements\n" +
            " \n" +
            "Figure 4. The Visible Spectrum\n" +
            "\n" +
            "METHODS\n" +
            "Materials\n" +
            "The following materials are required for this experiment: (Make sure to include amounts) \n" +
            "•\tOne Bunsen burner\n" +
            "•\tA supply of gas for the Bunsen burner\n" +
            "•\tA spark lighter\n" +
            "•\tA pair of pliers\n" +
            "•\tSamples of the following 9 metal ions:\n" +
            "o\tBarium Nitrate\n" +
            "o\tCalcium Chloride\n" +
            "o\tCobalt Chloride\n" +
            "o\tCopper Nitrate\n" +
            "o\tIron(III) Nitrate\n" +
            "o\tLithium Chloride\n" +
            "o\tPotassium Nitrate\n" +
            "o\tSodium Carbonate\n" +
            "o\tStrontium Nitrate\n" +
            "•\tFour unknown metal ions that are going to be identified\n" +
            "The set-up of the experiment is shown in Figure 5. \n" +
            " \n" +
            "Figure 5. Set-up Bunsen burner connected to the gas pipe\n" +
            "Procedures\n" +
            "1.\t Screw the Bunsen burner from the bottom fully, then unscrew a small bit so that the flame can travel through.\n" +
            "2.\tPut the barrel 2/3 of the way in the Bunsen burner so that it is possible to adjust it later\n" +
            "3.\tAttach one end of the gas pipe to the gas socket and the other end to the gas hole in the burner.\n" +
            "4.\tTurn the gas socket’s handle by a quarter of the total distance it can go.\n" +
            "5.\t Use a striker positioned above the Bunsen burner to light a flame.\n" +
            "6.\tAdjust the pin at the bottom of the Bunsen burner to get a blueish flame with another light blue flame inside it.\n" +
            "7.\tPut one ion sample on top of the flame, and record the color it emits while burning\n" +
            "8.\tCompare the observed color with the color-wavelength table and record the best wavelength.\n" +
            "9.\tRepeat steps 7-8 for all samples.\n" +
            "RESULTS\n" +
            "Data\n" +
            "Table 2. Data collected during procedures\n" +
            "Compound Name\tMetal Ion\tColor\tWavelength (nm)\tFrequency (hz)\n" +
            "Barium Nitrate\t2 +\tYellow/Green\t550.\t5.45 x 1014\n" +
            "Calcium Chloride\t2 +\tOrange/Red\t620.\t4.84 x 1014\n" +
            "Cobalt Chloride\t2 +\tYellow/White\t530.\t5.66 x 1014\n" +
            "Copper Nitrate\t2 +\tGreen/Blue\t500.\t6.00 x 1014\n" +
            "Iron(III) Nitrate\t3 +\tGolden Yellow\t580.\t5.17 x 1014\n" +
            "Lithium Chloride\t1 +\tRed\t700.\t4.28 x 1014\n" +
            "Potassium Nitrate\t1 +\tPink/Violet\t400.\t7.49 x 1014\n" +
            "Sodium Carbonate\t1 +\tBright Orange\t625.\t5.00 x 1014\n" +
            "Strontium Nitrate\t2 +\tDark Red\t715.\t4.19 x 1014\n" +
            "\n" +
            "\n" +
            "Table 3. Estimations of the Wavelengths of the Unknown Ions (Unknowns D)\n" +
            "Compound Name\tColor\tWavelength (nm)\tPredicted Ion\n" +
            "Unknown 1\tOrange\t625\tSodium Carbonate\n" +
            "Unknown 2\tGreen/Blue\t500.\tCopper Nitrate\n" +
            "Unknown 3\tRed\t700.\tLithium Chloride\n" +
            "Unknown 4\tDark Red\t715\tStrontium Nitrate\n" +
            "\n" +
            "Calculations and Graphs\n" +
            "Frequency Calculation:\n" +
            "1.\tGet the wavelength in nanometers of the compound whose frequency is to be calculated\n" +
            "a.\tWavelength for Barium Nitrate is 550. nm\n" +
            "2.\tConvert the scientifically accepted value for the speed of light into nanometers by multiplying it by 109\n" +
            "a.\t3.00 x 108 * 109 = 3.00 x 1017\n" +
            "3.\tPlug in the speed of light and the wavelength in the frequency formula\n" +
            "a.\tF = c / y, where F is frequency (hz), c is the speed of light in nm/s, and y is the wavelength\n" +
            "b.\tF = 3.00 x 1017 / 550.\n" +
            "c.\tF = 5.45 x 1014\n" +
            "4.\tThe answer should have 3 significant figures because the wavelength was estimated with 3 significant figures.\n" +
            "a.\tFrequency = 5.45 x 1014 hz\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "DISCUSSION\n" +
            "Conclusion\n" +
            "The purpose of the experiment was accomplished, the colors of light, wavelengths and frequencies of the metal ion samples are labeled in Table 2. The hypothesis was also supported as the 4 unknown metals in Table 3 were successfully indeitified.\n" +
            "Errors\n" +
            "One possible source of error in this experiment is not recognizing the actual color that the metal ions emit or mixing two different colors with each other.  This is random error, and it is due to the nature of human measurement being unreliable. These errors skew the data, and it can lead to some ions being misidentified. One way to prevent these types of errors would be to collaborate with others to compare the colors that are measured with each ion.\n" +
            "Another possible source of error in this lab would be due to floating point error, which is a error in computer precision that can effect the data and slightly skew it. However, this error is mostly minimal and unnoticeable in the actual significant figures of the calculations.\n" +
            "Post-Lab Questions \n" +
            "Identify the four unknown metals based on the color of light they emitted:\n" +
            "Unknown 1 – Sodium Carbonate\n" +
            "Unknown 2 – Copper Nitrate\n" +
            "Unknown 3 – Lithium Chloride\n" +
            "Unknown 4 – Strontium Nitrate\n" +
            "When a glass rod is heated, a yellow-green flame is observed around the point where it is heated. What does the yellow flame indicate might be present in the glass?\n" +
            "The yellow-green flame indicates that some amount of Barium Nitrate might be present in the glass rod. This is because the data that was collected for Barium Nitrate in this lab was associated with a yellow-green flame.\n" +
            "Within a paragraph or two explain how metallic salts are used in fireworks.\n" +
            "Since most metal salts give off light after being heated to high temperatures, they are a good agent to emit light midair for fireworks during their combustion. This is because when these salts absorb energy, they use the energy to excite an electron to a higher quantum energy level. When the electron falls back down, it releases a photon of a specific wavelength, depending on the energy initially absorbed (Shipman, 2012). The usage of salts in fireworks allow for a wider variety of colors in the explosion, for example, strontium salts emit red lights while copper salts emit blue light.\n" +
            "Extensions\n" +
            "Two bottles containing white powders have lost their labels. How could you determine which bottle contained strontium nitrate and which contained potassium sulfate?\n" +
            "According to the data collected in Table 2, Strontium Nitrate emits a dark red color when heated, and Potassium Nitrate emits a pink/violet color when heated. One way to determine which bottle contained which powder would be to observe the color that each of the powders emit when they are heated in a flame. The bottle containing Strontium Nitrate would emit a dark-red color, and the bottle containing Potassium Sulfate would emit violet.\n" +
            "One application of this concept to real life is determining the composition of metals in stars and temprature.x\n";
    public String rubric = "\n" +
            "\n" +
            "Exceptional (90-100%)\n" +
            "Emerging (<90%)\n" +
            "\n" +
            "Well written, clear, and complete purpose that aligns with the experiment.\n" +
            "Purpose is unclear or does not align well with the experiment.\n" +
            "Hypothesis or prediction is stated clearly and reasoning is provided to explain.\n" +
            "Hypothesis unclear or is not accompanied by adequate reasoning.\n" +
            "Student describes all relevant background information to the experiment and shares all formulas/calculations used.\n" +
            "Student describes some of the relevant background information or is missing a formula/calculation.\n" +
            "\n" +
            "Student lists all equipment used in the experiment and includes quantities, sizes, or masses where relevant.\n" +
            "Student lists all equipment used in the experiment but is missing quantities, sizes or masses; or is missing up to two pieces of equipment used.\n" +
            "Gives a detailed description of the experimental setup; and lists detailed steps of all laboratory procedures including quantities, masses, etc.\n" +
            "Missing details about the experimental setup; or missing one or two steps from the procedure; or is missing quantities, masses, etc. \n" +
            "\n" +
            "Includes all of the data that were gathered and have that data organized in a chart &/or graph that includes a title, labels, units, and trendline where appropriate.\n" +
            "Table or graph are missing one or two of: title, labels, units, or trendline; or some data are missing/ not calculated.\n" +
            "All calculated values have a sample calculation shown that includes: general formula, formula with numbers, and final answer with units. \n" +
            "Subtract 1 pt if sample calculations are missing one of: general formula, formula with numbers, or final answer with units.\n" +
            "\n" +
            "States whether the purpose was accomplished or not; and accepts or rejects the hypothesis with evidence from the results- including data/numbers\n" +
            "States whether the purpose was accomplished or not; and accepts or rejects the hypothesis, evidence missing\n" +
            "Thoroughly discusses errors that align with the data.\n" +
            "Adequate discussion of errors, or errors do not align with data.\n" +
            "\n" +
            "\n" +
            "Notes are clearly written and organized logically. Written in pen. New lab started on a fresh page with blank page separation. Mistakes are struck through, not obliterated or erased. Sections noted and labelled.\n" +
            "Notes are mostly legibly written and organized somewhat. New lab started on a fresh page but without blank page separation. Some mistakes obliterated or erased. Sections inadequately noted and labelled.\n" +
            "\n";

    private String leniency = "Strictly";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
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

        Paper.readPaperList(getApplicationContext());
        //Paper.createPaper(essay, rubric, new Teacher("Mr. Allshouse", "Strict"));
        Paper.writePaperList(getApplicationContext());
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