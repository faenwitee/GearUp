package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.*;

import data_access.DatabaseRetriever;
import entity.Answer;
import entity.Question;
import interface_adapter.logout.LogoutController;
import interface_adapter.studymodequestion.StudyModeQuestionController;
import interface_adapter.studymodequestion.StudyModeQuestionViewModel;

/**
 * The View of Study Mode Question.
 */
public class StudyModeQuestionView extends JPanel implements ActionListener {
    private final String viewName = "study mode question";

    private LogoutController logoutController;
    private final StudyModeQuestionViewModel studyModeQuestionViewModel;
    private StudyModeQuestionController studyModeQuestionController;

    private final JLabel username;
    private final JLabel studymodequestion;
    private final JButton option1;
    private final JButton option2;
    private final JButton option3;
    private final JButton option4;
    private final JButton nextButton;

    private LinkedList<Question> questions = new LinkedList<>();
    private Question currentQuestion;
    private String moduleName;
    private final int randomNum;

    public StudyModeQuestionView(StudyModeQuestionViewModel studyModeQuestionViewModel) throws SQLException {
        this.studyModeQuestionViewModel = studyModeQuestionViewModel;
        final Random random = new Random();
        this.randomNum = random.nextInt(6) + 1;
        getModuleQuestion();
        //        this.questions = DatabaseRetriever.getQuestionsFromStart(0);
        //        this.moduleName = studyModeQuestionViewModel.getState().getModule();
        //        this.questions = new LinkedList<>();

//        studyModeQuestionViewModel.addPropertyChangeListener(evt -> {
//            if ("state".equals(evt.getPropertyName())) {
//                this.moduleName = studyModeQuestionViewModel.getState().getModule();
//
//                System.out.println(this.moduleName);
//                //                getModuleQuestion();
//                try {
//                    getModuleQuestion();
//                }
//                catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        });

//        if ("Module 1".equals(moduleName)) {
//            System.out.println("success");
//        }


        //        final JLabel title = new JLabel("Study Mode Question");
        //        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //        studymodequestion = new JLabel(("Question needed to be added"));
        studymodequestion = new JLabel("Question needed to be added ");
        studymodequestion.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        nextButton = new JButton("Next");
        // disable nextButton at first
        nextButton.setEnabled(false);
        nextButton.setBounds(300, 400, 80, 30);
        buttons.add(nextButton);

        final JLabel usernameInfo = new JLabel("Currently logged in111: ");
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

                        }
                        else {
                            option1.setBackground(Color.RED);
                            option1.revalidate();
                            if (!questions.contains(currentQuestion)) {
                                questions.add(currentQuestion);
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
                        }
                        else {
                            option2.setBackground(Color.RED);
                            option2.revalidate();
                            option2.setOpaque(true);
                            option2.repaint();
                            if (!questions.contains(currentQuestion)) {
                                questions.add(currentQuestion);
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
                        }

                        else {
                            option3.setBackground(Color.RED);
                            option3.revalidate();
                            option3.setOpaque(true);
                            option3.repaint();

                            if (!questions.contains(currentQuestion)) {
                                questions.add(currentQuestion);
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
                        }
                        else {
                            option4.setBackground(Color.RED);
                            option4.revalidate();
                            option4.setOpaque(true);
                            option4.repaint();
                            if (!questions.contains(currentQuestion)) {
                                questions.add(currentQuestion);
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

        //        this.add(title);
        this.add(studymodequestion);
        this.add(buttons);

        this.add(usernameInfo);
        this.add(username);
    }

//    private void getModuleQuestion() throws SQLException {
//        if ("Module 1".equals(moduleName)) {
////            return DatabaseRetriever.getQuestionsFromStart(0);
//            this.questions = DatabaseRetriever.getQuestionsFromStart(0);
//
//        }
//        else if ("Module 2".equals(moduleName)) {
////            return DatabaseRetriever.getQuestionsFromStart(20);
//            this.questions = DatabaseRetriever.getQuestionsFromStart(20);
//
//        }
//        else if ("Module 3".equals(moduleName)) {
////            return DatabaseRetriever.getQuestionsFromStart(40);
//            this.questions = DatabaseRetriever.getQuestionsFromStart(40);
//
//        }
//        else if ("Module 4".equals(moduleName)) {
////            return DatabaseRetriever.getQuestionsFromStart(60);
//            this.questions = DatabaseRetriever.getQuestionsFromStart(60);
//
//        }
//        else if ("Module 5".equals(moduleName)) {
////            return DatabaseRetriever.getQuestionsFromStart(80);
//            this.questions = DatabaseRetriever.getQuestionsFromStart(80);
//        }
//        else {
////            return DatabaseRetriever.getQuestionsInRange(100,123);
//            this.questions = DatabaseRetriever.getQuestionsInRange(100,123);
//
//        }
//
//    }

    private void getModuleQuestion() throws SQLException {
        if (this.randomNum == 1) {
//            return DatabaseRetriever.getQuestionsFromStart(0);
            this.questions = DatabaseRetriever.getQuestionsFromStart(0);

        }
        else if (this.randomNum == 2) {
//            return DatabaseRetriever.getQuestionsFromStart(20);
            this.questions = DatabaseRetriever.getQuestionsFromStart(20);

        }
        else if (this.randomNum == 3) {
//            return DatabaseRetriever.getQuestionsFromStart(40);
            this.questions = DatabaseRetriever.getQuestionsFromStart(40);

        }
        else if (this.randomNum == 4) {
//            return DatabaseRetriever.getQuestionsFromStart(60);
            this.questions = DatabaseRetriever.getQuestionsFromStart(60);

        }
        else if (this.randomNum == 5) {
//            return DatabaseRetriever.getQuestionsFromStart(80);
            this.questions = DatabaseRetriever.getQuestionsFromStart(80);
        }
        else {
//            return DatabaseRetriever.getQuestionsInRange(100,123);
            this.questions = DatabaseRetriever.getQuestionsInRange(100,123);

        }

    }

    private void loadNextQuestion() {
        if (this.questions == null || this.questions.isEmpty()) {
            System.out.print("no questions available yet.");
        }

        else if (!this.questions.isEmpty()) {
            currentQuestion = this.questions.poll();
            studymodequestion.setText(currentQuestion.getQuestionText());

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
            JOptionPane.showMessageDialog(
                    this,
                    "You have completed the study mode!",
                    "Done",
                    JOptionPane.INFORMATION_MESSAGE
            );
            //            studyModeQuestionController.switchToStudyModeView();

        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setStudyModeQuestionController(StudyModeQuestionController controller) {
        this.studyModeQuestionController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

}
