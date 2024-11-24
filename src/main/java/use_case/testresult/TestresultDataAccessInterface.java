package use_case.testresult;

import java.util.ArrayList;

/**
 * DAO for the Test result Use Case.
 */
public interface TestresultDataAccessInterface {

    /**
     * Returns the number of the correct questions user got for test mode.
     * @return the number of the correct questions
     */
    int getCorrectQuestions();

    /**
     * Get the time used for completing the test.
     * @return the time taken b=to complete the test
     */
    int getTime();

    /**
     * Get a list of incorrect questions from a test.
     * @return a list of incorrect questions
     */
    ArrayList<String> getIncorrectQuestions();
}