package view;

import data_access.DatabaseRetriever;
import entity.Answer;
import entity.Question;
import interface_adapter.logout.LogoutController;
import interface_adapter.testmodequestion.TestModeQuestionController;
import interface_adapter.testmodequestion.TestModeQuestionViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The View of Test Mode Question.
 */
public class TestModeQuestionView extends JPanel implements ActionListener {
    private final String viewName = "test mode question";

    private LogoutController logoutController;
    private final TestModeQuestionViewModel testModeQuestionViewModel;
    private TestModeQuestionController testModeQuestionController;

    private final JLabel username;
    private final JLabel testmodequestion;
    private final JButton option1;
    private final JButton option2;
    private final JButton option3;
    private final JButton option4;
    private final JButton nextButton;

    private LinkedList<Question> questions;
    private Question currentQuestion;
    private ArrayList<Question> wrongquestions;
    private int correctquestion;

    public TestModeQuestionView(TestModeQuestionViewModel testModeQuestionViewModel) {
        this.testModeQuestionViewModel = testModeQuestionViewModel;
        this.questions = DatabaseRetriever.getQuestionsRandom();
        this.correctquestion = 0;

        final JLabel title = new JLabel("Test Mode Question");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        testmodequestion = new JLabel("Question needed to be added ");
        testmodequestion.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        //        buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        option1 = new JButton("Option 1");
        buttons.add(option1);
        buttons.add(Box.createVerticalStrut(10));

        option2 = new JButton("Option 2");
        buttons.add(option2);
        buttons.add(Box.createVerticalStrut(10));

        option3 = new JButton("Option 3");
        buttons.add(option3);
        buttons.add(Box.createVerticalStrut(10));

        option4 = new JButton("Option 4");
        buttons.add(option4);
        buttons.add(Box.createVerticalStrut(10));

        nextButton = new JButton("Next");
        // disable nextButton at first
        nextButton.setEnabled(false);
        nextButton.setBounds(300, 400, 80, 30);
        buttons.add(nextButton);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);

        loadNextQuestion();

        // Take user to 'begin view' page based on their selection
        option1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (currentQuestion.getAnswers().get(0).isCorrect()) {
                            option1.setBackground(Color.GREEN);
                            option1.revalidate();
                            option1.setOpaque(true);
                            option1.repaint();
                            correctquestion++;

                        }
                        else {
                            option1.setBackground(Color.RED);
                            option1.revalidate();
                            option1.setOpaque(true);
                            option1.repaint();
                            if (!wrongquestions.contains(currentQuestion)) {
                                wrongquestions.add(currentQuestion);
                            }
                        }
                        // Enable "Next" button after an option is selected
                        nextButton.setEnabled(true);
                    }
                }
        );

        option2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (currentQuestion.getAnswers().get(1).isCorrect()) {
                            option2.setBackground(Color.GREEN);
                            option2.revalidate();
                            option2.setOpaque(true);
                            option2.repaint();
                            correctquestion++;

                        }
                        else {
                            option2.setBackground(Color.RED);
                            option2.revalidate();
                            option2.setOpaque(true);
                            option2.repaint();
                            if (!wrongquestions.contains(currentQuestion)) {
                                wrongquestions.add(currentQuestion);
                            }
                        }
                        // Enable "Next" button after an option is selected
                        nextButton.setEnabled(true);
                    }
                }
        );

        option3.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (currentQuestion.getAnswers().get(2).isCorrect()) {
                            option3.setBackground(Color.GREEN);
                            option3.revalidate();
                            option3.setOpaque(true);
                            option3.repaint();
                            correctquestion++;

                        }
                        else {
                            option3.setBackground(Color.RED);
                            option3.revalidate();
                            option3.setOpaque(true);
                            option3.repaint();
                            if (!wrongquestions.contains(currentQuestion)) {
                                wrongquestions.add(currentQuestion);
                            }
                        }
                        // Enable "Next" button after an option is selected
                        nextButton.setEnabled(true);
                    }
                }
        );

        option4.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (currentQuestion.getAnswers().get(3).isCorrect()) {
                            option4.setBackground(Color.GREEN);
                            option4.revalidate();
                            option4.setOpaque(true);
                            option4.repaint();
                            correctquestion++;

                        }
                        else {
                            option4.setBackground(Color.RED);
                            option4.revalidate();
                            option4.setOpaque(true);
                            option4.repaint();
                            if (!wrongquestions.contains(currentQuestion)) {
                                wrongquestions.add(currentQuestion);
                            }
                        }
                        // Enable "Next" button after an option is selected
                        nextButton.setEnabled(true);
                    }
                }
        );

        nextButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        // Disable again until a new option is selected
                        nextButton.setEnabled(false);
                        loadNextQuestion();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(testmodequestion);
        this.add(buttons);

        this.add(usernameInfo);
        this.add(username);
    }

    private void loadNextQuestion() {
        if (!questions.isEmpty()) {
            currentQuestion = questions.poll();
            testmodequestion.setText(currentQuestion.getQuestionText());

            final List<Answer> answers = currentQuestion.getAnswers();
            final JButton[] options = {option1, option2, option3, option4};

            for (int i = 0; i < 4; i++) {
                if (i < answers.size()) {
                    options[i].setText(answers.get(i).getAnswerText());
                    options[i].setEnabled(true);
                    // Reset button color
                    options[i].setBackground(null);
                }
                else {
                    options[i].setText("N/A");
                    options[i].setEnabled(false);
                }
            }
        }
        else {
            // End session if no more questions
            // link to test result
            //            JOptionPane.showMessageDialog(
            //                    this,
            //                    "You have completed the study mode!",
            //                    "Done",
            //                    JOptionPane.INFORMATION_MESSAGE
            //            );
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setTestModeQuestionController(TestModeQuestionController testModeQuestionController) {
        this.testModeQuestionController = testModeQuestionController;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

}
