package edu.washington.cederw.quizdroid;

import java.util.List;

/**
 * a topic basically is a list of questions
 */
public class topic implements topicRepository{
    private String title;
    private String desc;
    private List<quiz> qs;
    private int icon;

    public topic(String title, String desc, List qs, int icon) {
        this.title = title;
        this.desc = desc;
        this.qs = qs;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getQuestion(int i) {
        return qs.get(i).getQuestion();
    }

    public String getAnswer1(int i) {
        return qs.get(i).getAnswer1();
    }

    public String getAnswer2(int i) {
        return qs.get(i).getAnswer2();
    }

    public String getAnswer3(int i) {
        return qs.get(i).getAnswer3();
    }

    public String getAnswer4(int i) {
        return qs.get(i).getAnswer4();
    }

    public int getCorrect(int i) {
        return qs.get(i).getCorrect();
    }

    //used to determine if i should show finish or not
    public int count(){
        return qs.size();
    }

    //this would show different icons if i could draw
    public int draw(){
        return icon;
    }


}
