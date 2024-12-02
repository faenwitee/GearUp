package interface_adapter.studymodebegin;

import interface_adapter.ViewManagerModel;
import interface_adapter.studymode.StudyModeViewModel;
import interface_adapter.studymodequestion.StudyModeQuestionState;
import interface_adapter.studymodequestion.StudyModeQuestionViewModel;
import use_case.studymodebegin.StudyModeBeginOutputBoundary;
import use_case.studymodebegin.StudyModeBeginOutputData;

/**
 * The Presenter for the Study Mode Begin Use Case.
 */
public class StudyModeBeginPresenter implements StudyModeBeginOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private StudyModeBeginViewModel studyModeBeginViewModel;
    private StudyModeQuestionViewModel studyModeQuestionViewModel;
    private StudyModeViewModel studyModeViewModel;

    public StudyModeBeginPresenter(ViewManagerModel viewManagerModel,
                                   StudyModeBeginViewModel studyModeBeginViewModel,
                                   StudyModeQuestionViewModel studyModeQuestionViewModel,
                                   StudyModeViewModel studyModeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.studyModeBeginViewModel = studyModeBeginViewModel;
        this.studyModeQuestionViewModel = studyModeQuestionViewModel;
        this.studyModeViewModel = studyModeViewModel;
    }

    @Override
    public void prepareSuccessView(StudyModeBeginOutputData outputData) {
        final StudyModeQuestionState studyModeQuestionState = studyModeQuestionViewModel.getState();
        studyModeQuestionState.setModule(outputData.getModule());
        this.studyModeQuestionViewModel.setState(studyModeQuestionState);
        this.studyModeQuestionViewModel.firePropertyChanged();
        this.viewManagerModel.setState(studyModeQuestionViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // Assume no failure view
    }

    @Override
    public void switchToStudyModeQuestionView() {
        viewManagerModel.setState(studyModeQuestionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToStudyModeView() {
        viewManagerModel.setState(studyModeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

