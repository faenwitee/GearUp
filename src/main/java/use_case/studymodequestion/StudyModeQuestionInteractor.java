package use_case.studymodequestion;

/**
 * The TestModeQuestion Interactor.
 */
public class StudyModeQuestionInteractor implements StudyModeQuestionInputBoundary {
    private final StudyModeQuestionDataAccessInterface smqdaiObject;
    private final StudyModeQuestionOutputBoundary presenter;

    public StudyModeQuestionInteractor(StudyModeQuestionDataAccessInterface smqdaiObject,
                                   StudyModeQuestionOutputBoundary outputBoundary) {
        this.smqdaiObject = smqdaiObject;
        this.presenter = outputBoundary;
    }

    @Override
    public void execute(StudyModeQuestionInputData input) {
        final String option = input.getSelectedAnswer();

        //        necessary?
        //        if (!isValidMode(mode)) {
        //            presenter.prepareFailView("Invalid answer selected, Please choose one of the fourth option");
        //        }
        final StudyModeQuestionOutputData output = new StudyModeQuestionOutputData(option);

        presenter.prepareSuccessView(output);
    }

    @Override
    public void switchToNextQuestionView() {
        presenter.switchToNextQuestionView();
    }

    @Override
    public void switchToTestResultView() {
        presenter.switchToTestResultView();
    }

    /**
     * Switch back to the StudyMode View.
     */
    @Override
    public void switchToStudyModeView() {
        presenter.switchToStudyMode();
    }
}
