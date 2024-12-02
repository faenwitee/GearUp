package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.testresult.TestresultController;
import interface_adapter.testresult.TestresultViewModel;

/**
 * The view for test result use case.
 */
public class TestresultView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "test result";

    private final TestresultViewModel testresultViewModel;

    private TestresultController testresultController;

    private final JButton finish = new JButton("Finish");

    private final Color backgroundC = Color.decode("#99acaf");

    public TestresultView(TestresultViewModel viewModel) {
        this.testresultViewModel = viewModel;
        testresultViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(TestresultViewModel.TITLE_LABEL);
        final int fontTitle = 32;
        title.setFont(new Font("Times New Roman", Font.BOLD, fontTitle));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel correctQuestionsLabel = new JLabel("Correct questions you got for this test is "
                + viewModel.getCorrectQuestions());
        final JLabel timeLabel = new JLabel("The time taken for this test is " + viewModel.getTime());

        final JPanel incorrectQuestionsPanel = new JPanel();
        incorrectQuestionsPanel.setLayout(new BoxLayout(incorrectQuestionsPanel, BoxLayout.Y_AXIS));
        incorrectQuestionsPanel.setBackground(backgroundC);
        final JLabel incorrectQuestionsLabel;
        if (viewModel.getIncorrectQuestions().isEmpty()) {
            incorrectQuestionsLabel = new JLabel("Congratulations! You answered all 40 "
                    + "questions correctly for this test.");
        }
        else {
            incorrectQuestionsLabel = new JLabel("Incorrect questions you got from this test is(are): ");
        }
        incorrectQuestionsPanel.add(incorrectQuestionsLabel);

        for (String question: viewModel.getIncorrectQuestions()) {
            incorrectQuestionsPanel.add(new JLabel("- " + question));
        }

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(correctQuestionsLabel);
        this.add(timeLabel);
        this.add(finish);
        finish.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        testresultController.switchToLoggedInView();
                    }
                }
        );

    }

    public String getViewName() {
        return viewName;
    }

    public void setTestResultController(TestresultController controller) {
        this.testresultController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // no implementation needed for this view.
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // no implementation needed for this view.
    }
}
