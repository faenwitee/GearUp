package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

/**
 * Login View.
 */
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "log in";
    private final LoginViewModel loginViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    private JButton logIn;
    private JButton toSignUp;
    private LoginController loginController;
    private final Color backgroundC = Color.decode("#99acaf");

    public LoginView(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        this.setBackground(backgroundC);

        final JPanel titlePanel = createTitlePanel();
        final LabelTextPanel usernameInfo = createInputPanel("Username", usernameInputField);
        final LabelTextPanel passwordInfo = createInputPanel("Password", passwordInputField);

        final JPanel buttons = createButtonsPanel();

        // Add action listeners for buttons
        addActionListeners();

        // Add document listeners for input fields
        addDocumentListeners();

        // Layout setup
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(titlePanel);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(usernameErrorField);
        this.add(buttons);
    }

    private JPanel createTitlePanel() {
        final JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setBackground(backgroundC);

        final JLabel title = new JLabel("Login");
        final int titleSize = 32;
        title.setFont(new Font("Times New Roman", Font.BOLD, titleSize));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("images/Signup_icon.jpg")));
        final Image scaledImage = originalIcon.getImage().getScaledInstance(90, -1, Image.SCALE_SMOOTH);
        final ImageIcon resizedIcon = new ImageIcon(scaledImage);
        final JLabel imageLabel = new JLabel(resizedIcon);

        titlePanel.add(title);
        titlePanel.add(imageLabel);
        return titlePanel;
    }

    private LabelTextPanel createInputPanel(String label, JTextField textField) {
        return new LabelTextPanel(new JLabel(label), textField);
    }

    private JPanel createButtonsPanel() {
        final JPanel buttons = new JPanel();
        buttons.setBackground(backgroundC);

        toSignUp = new JButton("Go to Sign Up");
        logIn = new JButton("Log In");

        buttons.add(toSignUp);
        buttons.add(logIn);
        return buttons;
    }

    private void addActionListeners() {
        toSignUp.addActionListener(evt -> loginController.switchToSignUpView());
        logIn.addActionListener(evt -> {
            final LoginState currentState = loginViewModel.getState();
            loginController.execute(currentState.getUsername(), currentState.getPassword());
        });
    }

    private void addDocumentListeners() {
        addDocumentListener(usernameInputField, text -> {
            final LoginState currentState = loginViewModel.getState();
            currentState.setUsername(text);
            loginViewModel.setState(currentState);
        });

        addDocumentListener(passwordInputField, text -> {
            final LoginState currentState = loginViewModel.getState();
            currentState.setPassword(text);
            loginViewModel.setState(currentState);
        });
    }

    private void addDocumentListener(JTextField textField, InputUpdateListener listener) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                listener.onUpdate(textField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                listener.onUpdate(textField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                listener.onUpdate(textField.getText());
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        usernameErrorField.setText(state.getLoginError());
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
        passwordInputField.setText(state.getPassword());
    }

    public String getViewName() {
        return viewName;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    /**
     * Method to handle updates to the input text.
     */
    @FunctionalInterface
    private interface InputUpdateListener {
        void onUpdate(String text);
    }
}
