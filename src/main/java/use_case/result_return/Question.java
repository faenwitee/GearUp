package use_case.result_return;

/**
 * One question data.
 */
public class Question {
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int answer;

    public Question(String question, String option1, String option2, String option3, String option4, int answer) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
    }
}
