package use_case.studymodequestion;

/**
 * The output boundary for the Study Mode Question Use Case.
 */
public interface StudyModeQuestionOutputBoundary {
    /**
     * Prepares the success view for the Study Mode Question Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(StudyModeQuestionOutputData outputData);

    /**
     * Prepares the failure view for the Study Mode Question Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switch to the next Study Mode Question View.
     */
    void switchToNextQuestionView();

    /**
     * Switch to the Test Result View.
     */
    void switchToTestResultView();

    void switchToStudyMode();
}
