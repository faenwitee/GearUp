package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

/**
 * Sign Up View.
 */
public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "sign up";

    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final Color backgroundC = Color.decode("#99acaf");

    private SignupController signupController;

    public SignupView(SignupViewModel signupViewModel) {
        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(this);

        // Set the panel's background color
        this.setBackground(backgroundC);

        // Initialize components
        final JPanel titlePanel = createTitlePanel();
        final JPanel inputFieldsPanel = createInputFieldsPanel();
        final JPanel buttonsPanel = createButtonsPanel();

        // Add components to the layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(titlePanel);
        this.add(inputFieldsPanel);
        this.add(buttonsPanel);

        // Add listeners
        addUsernameListener();
        addPasswordListener();
        addRepeatPasswordListener();
    }

    private JPanel createTitlePanel() {
        final JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setBackground(backgroundC);
        final int tbMargin = 20;
        titlePanel.setBorder(BorderFactory.createEmptyBorder(tbMargin, 0, tbMargin, 0));

        final JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        final int fontTitle = 32;
        title.setFont(new Font("Times New Roman", Font.BOLD, fontTitle));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final ImageIcon originalIcon = new ImageIcon("images\\Signup_icon.jpg");
        final Image scaledImage = originalIcon.getImage().getScaledInstance(90, -1, Image.SCALE_SMOOTH);
        final ImageIcon resizedIcon = new ImageIcon(scaledImage);

        final JLabel imageLabel = new JLabel(resizedIcon);

        titlePanel.add(title);
        final int horizontalS = 10;
        titlePanel.add(Box.createHorizontalStrut(horizontalS));
        titlePanel.add(imageLabel);

        return titlePanel;
    }

    private JPanel createInputFieldsPanel() {
        final JPanel inputFieldsPanel = new JPanel();
        inputFieldsPanel.setLayout(new BoxLayout(inputFieldsPanel, BoxLayout.Y_AXIS));

        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
        final LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
        inputFieldsPanel.add(usernameInfo);
        inputFieldsPanel.add(passwordInfo);
        inputFieldsPanel.add(repeatPasswordInfo);

        return inputFieldsPanel;
    }

    private JPanel createButtonsPanel() {
        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(backgroundC);

        final JLabel accountInfo = new JLabel(SignupViewModel.EXISTING_ACCOUNT_LABEL);
        buttonsPanel.add(accountInfo);

        final JButton toLogin = new JButton(SignupViewModel.TO_LOGIN_BUTTON_LABEL);
        buttonsPanel.add(toLogin);

        final JButton signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttonsPanel.add(signUp);

        toLogin.addActionListener(event -> signupController.switchToLoginView());

        signUp.addActionListener(event -> {
            final SignupState currentState = signupViewModel.getState();
            signupController.execute(
                    currentState.getUsername(),
                    currentState.getPassword(),
                    currentState.getRepeatPassword()
            );
        });

        return buttonsPanel;
    }

    private void addUsernameListener() {
        usernameInputField.getDocument().addDocumentListener(new AbstractDocumentListenerAdapter() {
            @Override
            public void onUpdate() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                signupViewModel.setState(currentState);
            }
        });
    }

    private void addPasswordListener() {
        passwordInputField.getDocument().addDocumentListener(new AbstractDocumentListenerAdapter() {
            @Override
            public void onUpdate() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                signupViewModel.setState(currentState);
            }
        });
    }

    private void addRepeatPasswordListener() {
        repeatPasswordInputField.getDocument().addDocumentListener(new AbstractDocumentListenerAdapter() {
            @Override
            public void onUpdate() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setRepeatPassword(new String(repeatPasswordInputField.getPassword()));
                signupViewModel.setState(currentState);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setSignupController(SignupController controller) {
        this.signupController = controller;
    }
}
