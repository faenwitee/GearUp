package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.logout.LogoutController;
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

    private JButton backToStudyMode;
    private JPanel buttonWrapper;
    private JButton begin;
    private String moduleName;

    public StudyModeBeginView(StudyModeBeginViewModel studyModeBeginViewModel) {
        this.studyModeBeginViewModel = studyModeBeginViewModel;
        this.setBackground(Color.decode("#11212D"));

        moduleName = studyModeBeginViewModel.getState().getModule();

        final JLabel title = createTitleLabel();
        final JLabel beginText = createBeginTextLabel();
        final JPanel buttons = createButtonsPanel();
        setupPropertyChangeListener(beginText);

        buttonWrapper = createButtonWrapper();

        setupLayout(title, beginText, buttons);
        setupActionListeners();

        this.add(title);
        this.add(beginText);
        this.add(buttons);
        this.add(buttonWrapper);
    }

    private JLabel createTitleLabel() {
        final JLabel title = new JLabel("Study Mode\n");
        final int titleSize = 25;
        title.setFont(new Font("Times New Roman", Font.ITALIC, titleSize));
        title.setForeground(Color.decode("#4A5C6A"));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        return title;
    }

    private JLabel createBeginTextLabel() {
        final JLabel beginText = new JLabel("<html>Welcome to study " + moduleName
                + ". The questions you get wrong will be redisplayed until you answer all of them correctly.</html>");
        beginText.setAlignmentX(Component.CENTER_ALIGNMENT);
        final int beginTextSize = 20;
        beginText.setFont(new Font("Arial", Font.PLAIN, beginTextSize));
        return beginText;
    }

    private void setupPropertyChangeListener(JLabel beginText) {
        studyModeBeginViewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                moduleName = studyModeBeginViewModel.getState().getModule();
                beginText.setText("<html><div style='text-align: center; font-family: "
                        + "\"Times New Roman\"; margin: 10px auto;'><p style='color: #C1E8FF;'>Welcome to study "
                        + "<span style='color: #5483B3;font-style: italic;'>" + moduleName + "</span>.</p>"
                        + "<p style='color: #C1E8FF;'>The questions you get wrong</span> "
                        + "will be redisplayed until you answer all of them correctly.</p></div></html>");
            }
        });
    }

    private JPanel createButtonsPanel() {
        final JPanel buttons = new JPanel();
        buttons.setBackground(Color.decode("#11212D"));
        final String fancyFont = "Lucida Handwriting";
        final int fontSize = 20;
        begin = new JButton("Begin");
        begin.setFont(new Font(fancyFont, Font.ITALIC, fontSize));
        begin.setForeground(Color.decode("#9BA8AB"));
        begin.setBackground(Color.decode("#253745"));
        buttons.add(begin);

        backToStudyMode = new JButton("Back To Mode Selection");
        backToStudyMode.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(backToStudyMode, "#253745", "#9BA8AB");

        return buttons;
    }

    private void styleButton(JButton button, String foreColor, String backColor) {
        button.setBackground(Color.decode(backColor));
        button.setForeground(Color.decode(foreColor));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorderPainted(false);
    }

    private JPanel createButtonWrapper() {
        final int wrapperMargin = 20;
        buttonWrapper = new JPanel();
        buttonWrapper.setLayout(new BoxLayout(buttonWrapper, BoxLayout.Y_AXIS));
        buttonWrapper.setOpaque(false);
        buttonWrapper.setBorder(BorderFactory.createEmptyBorder(wrapperMargin, 0, wrapperMargin, 0));
        buttonWrapper.add(backToStudyMode);
        return buttonWrapper;
    }

    private void setupLayout(JLabel title, JLabel beginText, JPanel buttons) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(beginText);
        this.add(buttons);
    }

    private void setupActionListeners() {
        begin.addActionListener(evt -> {
            if (evt.getSource().equals(begin)) {
                final StudyModeBeginState state = studyModeBeginViewModel.getState();
                state.setModule(moduleName);
                studyModeBeginViewModel.setState(state);
                studyModeBeginViewModel.firePropertyChanged();
                studyModeBeginController.execute(state.getModule());
            }
            studyModeBeginController.switchToStudyModeQuestionView();
        });

        backToStudyMode.addActionListener(evt -> studyModeBeginController.switchToStudyModeView());
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
