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
import interface_adapter.testmode.TestModeController;
import interface_adapter.testmode.TestModeState;
import interface_adapter.testmode.TestModeViewModel;

/**
 * The View after user selected test mode.
 */
public class TestModeView extends JPanel {
    private final String viewName = "test mode";

    private LogoutController logoutController;
    private final TestModeViewModel testModeViewModel;
    private TestModeController testModeController;

    private JButton begin;
    private JButton backToModeSelection;
    private JPanel buttonWrapper;

    public TestModeView(TestModeViewModel testModeViewModel) {
        this.testModeViewModel = testModeViewModel;
        this.setBackground(Color.decode("#11212D"));

        final JLabel title = createTitleLabel();
        final JLabel beginText = createBeginTextLabel();
        final JPanel buttons = createButtonsPanel();
        buttonWrapper = createButtonWrapper();

        setupActionListeners();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addComponents(title, beginText, buttons);
    }

    private JLabel createTitleLabel() {
        final int titleFontSize = 25;
        return createLabel("Test Mode\n", new Font("Times New Roman", Font.ITALIC, titleFontSize), "#4A5C6A");
    }

    private JLabel createBeginTextLabel() {
        final int beginTextFontSize = 20;
        final String text = "<html><div style='text-align: center; font-family: \"Times New Roman\"; "
                + "margin: 10px auto;'> <p style='color: #C1E8FF;'>You have 40 Questions to answer. "
                + "To pass the test, you need to get 36 questions correct. For your reference, the time you used will"
                + " be displayed, but the test is NOT timed.</p></div></html>";
        return createHtmlLabel(text, beginTextFontSize);
    }

    private JLabel createLabel(String text, Font font, String color) {
        final JLabel label = new JLabel(text);
        if (font != null) {
            label.setFont(font);
        }
        if (color != null) {
            label.setForeground(Color.decode(color));
        }
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JLabel createHtmlLabel(String htmlText, int fontSize) {
        final JLabel label = new JLabel("<html><div style='text-align: center; font-family: \"Times New Roman\"; "
                + "margin: 10px auto; font-size: " + fontSize + "'>"
                + htmlText + "</div></html>");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, fontSize));
        return label;
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

        backToModeSelection = new JButton("Back To Mode Selection");
        backToModeSelection.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(backToModeSelection, "#253745", "#9BA8AB");

        return buttons;
    }

    private JPanel createButtonWrapper() {
        final int wrapperMargin = 20;
        buttonWrapper = new JPanel();
        buttonWrapper.setLayout(new BoxLayout(buttonWrapper, BoxLayout.Y_AXIS));
        buttonWrapper.setOpaque(false);
        buttonWrapper.setBorder(BorderFactory.createEmptyBorder(wrapperMargin, 0, wrapperMargin, 0));
        buttonWrapper.add(backToModeSelection);
        return buttonWrapper;
    }

    private void styleButton(JButton button, String foreColor, String backColor) {
        button.setBackground(Color.decode(backColor));
        button.setForeground(Color.decode(foreColor));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorderPainted(false);
    }

    private void setupActionListeners() {
        begin.addActionListener(evt -> {
            if (evt.getSource().equals(begin)) {
                final TestModeState testModeState = testModeViewModel.getState();
                testModeViewModel.setState(testModeState);
                testModeViewModel.firePropertyChanged();
                testModeController.execute();
            }
            testModeController.switchToTestModeQuestionView();
        });

        backToModeSelection.addActionListener(evt -> testModeController.switchToModeSelectionView());
    }

    private void addComponents(JLabel title, JLabel beginText, JPanel buttons) {
        this.add(title);
        this.add(beginText);
        this.add(buttons);
        this.add(backToModeSelection);
        this.add(buttonWrapper);
    }

    public String getViewName() {
        return viewName;
    }

    public void setTestModeController(TestModeController testModeController) {
        this.testModeController = testModeController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

}
