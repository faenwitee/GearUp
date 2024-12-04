package use_case.signup;

import java.io.IOException;

import entity.User;
import entity.UserFactory;
import use_case.email_validation.VerifaliaEmailValidator;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        boolean validRequest = true;
        // Step 1: Validate the email
        try {
            if (!VerifaliaEmailValidator.validateEmail(signupInputData.getUsername())) {
                userPresenter.prepareFailView("Invalid email address. Please enter a valid email.");
                validRequest = false;
            }
        }
        catch (IOException ioException) {
            userPresenter.prepareFailView("An error occurred during email validation. Please try again.");
            validRequest = false;
        }

        // Step 2: Check if the user already exists (only if email is valid)
        if (validRequest && userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
            validRequest = false;
        }

        // Step 3: Validate passwords match (only if previous checks passed)
        if (validRequest && !signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
            validRequest = false;
        }

        // Step 4: Create a new user if all validations pass
        if (validRequest) {
            final User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
            userDataAccessObject.save(user);

            final SignupOutputData signupOutputData = new SignupOutputData(user.getName(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
