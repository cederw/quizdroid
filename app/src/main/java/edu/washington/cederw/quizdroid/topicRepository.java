package edu.washington.cederw.quizdroid;

/**
 * declares stuff
 */
public interface topicRepository{
    String getTitle();
    String getDesc();
    String getQuestion(int i);

    String getAnswer1(int i);

    String getAnswer2( int i);

    String getAnswer3(int i);

    String getAnswer4(int i);

    int getCorrect(int i);

    int count();
    int draw();
}
