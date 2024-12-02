package interface_adapter.studymodequestion;

import interface_adapter.ViewManagerModel;
import use_case.studymodequestion.StudyModeQuestionOutputBoundary;
import use_case.studymodequestion.StudyModeQuestionOutputData;

/**
 * Presenter for study mode question use case.
 */
public class StudyModeQuestionPresenter implements StudyModeQuestionOutputBoundary {
    private final StudyModeQuestionViewModel studyModeQuestionViewModel;
    private final ViewManagerModel viewManagerModel;

    public StudyModeQuestionPresenter(ViewManagerModel viewManagerModel, StudyModeQuestionViewModel studyModeQuestionViewModel) {
        this.studyModeQuestionViewModel = studyModeQuestionViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(StudyModeQuestionOutputData output) {
        // TODO: haven't change yet
        studyModeQuestionViewModel.getState().setSelectedOption(output.getSelectedMode());

        viewManagerModel.setState(output.getSelectedMode());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // assume there is no failure case when selecting a mode.
    }

    @Override
    public void switchToNextQuestionView() {
        // TODO: how next
        viewManagerModel.setState("next question");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToTestResultView() {
        viewManagerModel.setState("test result");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToStudyMode() {
        viewManagerModel.setState("study mode");
        viewManagerModel.firePropertyChanged();
    }
}