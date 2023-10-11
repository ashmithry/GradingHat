package com.example.cac2023.backend;


import java.util.function.Consumer;

public class Grader
{
    public static synchronized void GenerateFeedback(String essay, String rubric, String leniency, Consumer<String> breakdown_receiver, Consumer<String> feedback_receiver, Consumer<String> grade_receiver)
    {
        String breakdown_prompt = "This is my piece of writing:" + essay + "\nThis is my rubric:" + rubric + "\nYou will grade the writing as a " + leniency + " grader. Your response will be in 3 sections:" +
                                   "\nFirst, Please provide a short breakdown of what points I earned or missed on the rubric.This section should begin with 'Breakdown:'";
        String feedback_prompt =   "\nNow provide some concise feedback on things I could improve on in the writing. This section should start with 'Feedback:'";
        String grade_prompt =      "\nProvide the grade in a format with the total points on the denominator and the earned points on the numerator. Example: 44/50. This section should consist of 'Overall Grade:' and the fractional grade. Don't add any other words in this section.";

        String query = breakdown_prompt + feedback_prompt + grade_prompt;
        int tokens = APICaller.countTokens(query);

        APICaller caller = new APICaller("You are going to grade a piece of writing and provide feedback based on the rubric and a teacher's leniency.");

        caller.requestAPI(query, 12000 - tokens, (result) -> {

            int breakdownIndex = result.indexOf("Breakdown:") + 10;
            int feedbackIndex = result.indexOf("Feedback:") + 9;
            int gradeIndex = result.indexOf("Overall Grade:") + 14;
            String breakdown = result.substring(breakdownIndex, feedbackIndex - 9);
            String grade = result.substring(gradeIndex);
            String feedback = result.substring(feedbackIndex, gradeIndex - 14);

            breakdown_receiver.accept(breakdown);
            grade_receiver.accept(grade);
            feedback_receiver.accept(feedback);
        });
    }

}
