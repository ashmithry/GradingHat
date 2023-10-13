package com.example.cac2023.backend;

import android.content.Context;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IO
{
    private static final String filename = "data.json";
    public static JSONObject jsonData;
    public static JSONObject getJSONFile(Context context)
    {
        if(jsonData != null)
        {
            return jsonData;
        }

        JSONObject data = new JSONObject();
        try {
            FileInputStream fIS = context.openFileInput(filename);
            InputStreamReader isReader = new InputStreamReader(fIS);
            BufferedReader reader = new BufferedReader(isReader);

            StringBuilder jsonBuilder = new StringBuilder();
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                jsonBuilder.append(currentLine);
            }

            if (jsonBuilder.length() < 1) {
                jsonBuilder.append("{}");
            }

            data = new JSONObject(
                    new JSONTokener(jsonBuilder.toString())
            );

            reader.close();
            isReader.close();
            fIS.close();
        } catch (FileNotFoundException e) {
            try {
                FileOutputStream fOS = context.openFileOutput(filename, Context.MODE_PRIVATE);
                OutputStreamWriter osWriter = new OutputStreamWriter(fOS);
                BufferedWriter writer = new BufferedWriter(osWriter);

                writer.write("{}");

                writer.close();
                osWriter.close();
                fOS.close();
            } catch (Exception f) {
                f.printStackTrace();
                System.exit(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        jsonData = data;
        return data;
    }
    public static void writeJSONFile(Context context)
    {
        try {
            FileOutputStream fOS = context.openFileOutput(filename, Context.MODE_PRIVATE);
            OutputStreamWriter osWriter = new OutputStreamWriter(fOS);
            BufferedWriter writer = new BufferedWriter(osWriter);

            writer.write(jsonData.toString());

            writer.close();
            osWriter.close();
            fOS.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
