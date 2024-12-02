package interface_adapter.studymode;

import use_case.studymode.StudyModeInputBoundary;
import use_case.studymode.StudyModeInputData;

/**
 * Controller for the Study Mode Use Case.
 */
public class StudyModeController {
    private final StudyModeInputBoundary studyModeInputBoundary;

    public StudyModeController(StudyModeInputBoundary studyModeInputBoundary) {
        this.studyModeInputBoundary = studyModeInputBoundary;
    }

    /**
     * Executes the Study Mode Use Case.
     * @param module the module that the user selected
     */
    public void execute(String module) {
        final StudyModeInputData studyModeInputData = new StudyModeInputData(module);
        studyModeInputBoundary.execute(studyModeInputData);
    }

    /**
     * Executes the "switch to StudyModeBegin" Use Case.
     */
    public void switchToStudyModeBeginView() {
        studyModeInputBoundary.switchToStudyModeBeginView();
    }

    /**
     * Executes the "switch to LoggedIn" Use Case.
     */
    public void switchToModeSelectionView() {
        studyModeInputBoundary.switchToModeSelectionView();
    }
}
