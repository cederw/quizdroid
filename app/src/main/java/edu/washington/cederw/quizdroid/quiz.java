package edu.washington.cederw.quizdroid;

/*
This is poorly named but i already have a question object running around in this project
this thing contains an individual question to quiz people on.
 */
public class quiz {
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int correct;

    public quiz(String question, String answer1, String answer2, String answer3, String answer4, int correct) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correct = correct;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public int getCorrect() {
        return correct;
    }



}
