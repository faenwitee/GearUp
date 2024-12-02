package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.logout.LogoutController;
import interface_adapter.studymode.StudyModeState;
import interface_adapter.studymodebegin.StudyModeBeginController;
import interface_adapter.studymodebegin.StudyModeBeginState;
import interface_adapter.studymodebegin.StudyModeBeginViewModel;

/**
 * The View after user selected a topic to study on - the 'Begin' Page.
 */
public class StudyModeBeginView extends JPanel {
    private final String viewName = "study mode begin";

    private LogoutController logoutController;
    private final StudyModeBeginViewModel studyModeBeginViewModel;
    private StudyModeBeginController studyModeBeginController;

    private final JButton backToStudyMode;
    private final JPanel buttonWrapper;
    private final JButton begin;
    private final JLabel username;
    private String moduleName;

    public StudyModeBeginView(StudyModeBeginViewModel studyModeBeginViewModel) {

        this.studyModeBeginViewModel = studyModeBeginViewModel;
        this.setBackground(Color.decode("#11212D"));

        final JLabel title = new JLabel("Study Mode\n");
        title.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        title.setForeground(Color.decode("#4A5C6A"));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        moduleName = studyModeBeginViewModel.getState().getModule();

        final JLabel beginText = new JLabel("<html>Welcome to study "
                + moduleName + ". The questions you get wrong will be "
                + " redisplayed until you answer all of them correctly.</html>");
        beginText.setAlignmentX(Component.CENTER_ALIGNMENT);
        beginText.setFont(new Font("Arial", Font.PLAIN, 20));

        studyModeBeginViewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                moduleName = studyModeBeginViewModel.getState().getModule();
                beginText.setText("<html><div style='text-align: center; font-family: \"Times New Roman\"; margin: 10px auto;'>"
                        + "<p style='color: #C1E8FF;'>Welcome to study <span style='color: #5483B3; "
                        + "font-style: italic;'>" + moduleName + "</span>.</p>"
                        + "<p style='color: #C1E8FF;'>The questions you get wrong</span> "
                        + "will be redisplayed until you answer all of them correctly.</p></div></html>");
            }
        });

        System.out.println("hi" + moduleName);

        final JPanel buttons = new JPanel();
        buttons.setBackground(Color.decode("#11212D"));
        final String fancyFont = "Lucida Handwriting";
        final int fontSize = 20;

        begin = new JButton("Begin");
        begin.setFont(new Font(fancyFont, Font.ITALIC, fontSize));
        begin.setForeground(Color.decode("#9BA8AB"));
        begin.setBackground(Color.decode("#253745"));
        buttons.add(begin);

        backToStudyMode = new JButton("Back To Study Mode");
        backToStudyMode.setAlignmentX(Component.CENTER_ALIGNMENT);

        final int wrapperMargin = 20;
        buttonWrapper = new JPanel();
        buttonWrapper.setLayout(new BoxLayout(buttonWrapper, BoxLayout.Y_AXIS));
        buttonWrapper.setOpaque(false);
        buttonWrapper.setBorder(BorderFactory.createEmptyBorder(wrapperMargin, 0, wrapperMargin, 0));
        buttonWrapper.add(backToStudyMode);

        backToStudyMode.setBackground(Color.decode("#9BA8AB"));
        backToStudyMode.setForeground(Color.decode("#253745"));
        backToStudyMode.setFocusPainted(false);
        backToStudyMode.setOpaque(true);
        backToStudyMode.setBorderPainted(false);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Take user to 'question view' page based on their selection
        begin.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(begin)) {
                            final StudyModeBeginState studyModeBeginState = studyModeBeginViewModel.getState();
                            studyModeBeginState.setModule(moduleName);
                            studyModeBeginViewModel.setState(studyModeBeginState);
                            studyModeBeginViewModel.firePropertyChanged();
                            System.out.println(studyModeBeginState.getModule());
                            studyModeBeginController.execute(studyModeBeginState.getModule());
                        }
                        studyModeBeginController.switchToStudyModeQuestionView();
                    }
                }
        );

        // Take user back to <TestModeView>
        backToStudyMode.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        studyModeBeginController.switchToStudyModeView();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(beginText);
        this.add(buttons);

        this.add(buttonWrapper);

        this.add(usernameInfo);
        this.add(username);
    }

    public String getViewName() {
        return viewName;
    }

    public void setStudyModeBeginController(StudyModeBeginController studyModeBeginController) {
        this.studyModeBeginController = studyModeBeginController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

}
