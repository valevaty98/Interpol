package by.training.interpol.util;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ParamsValidatorTest {

    @BeforeMethod
    public void setUp() {
    }

    @Test
    public void testIsValidEmail() {
        String testEmail = "test.email@email.com";
        boolean isValid = ParamsValidator.isValidEmail(testEmail);
        Assert.assertTrue(isValid);
    }

    @Test (description = "Email is invalid")
    public void testIsInvalidEmail() {
        String testEmail = "test.email@email..com";
        boolean isValid = ParamsValidator.isValidEmail(testEmail);
        Assert.assertFalse(isValid);
    }

    @Test
    public void testIsValidPassword() {
        String testPassword = "easy_pass22";
        boolean isValid = ParamsValidator.isValidPassword(testPassword);
        Assert.assertTrue(isValid);
    }

    @Test (description = "Password is invalid")
    public void testIsInvalidPassword() {
        String testPassword = "easy.pass22";
        boolean isValid = ParamsValidator.isValidPassword(testPassword);
        Assert.assertFalse(isValid);
    }

    @Test
    public void testIsValidLogin() {
        String testLogin = "user-login.2";
        boolean isValid = ParamsValidator.isValidLogin(testLogin);
        Assert.assertTrue(isValid);
    }

    @Test (description = "Login is invalid")
    public void testIsInvalidLogin() {
        String testLogin = "user.im-";
        boolean isValid = ParamsValidator.isValidLogin(testLogin);
        Assert.assertFalse(isValid);
    }

    @Test (description = "Subject string length = 46")
    public void testIsValidSubject() {
        String testSubject = "Lorem ipsum dolor consectetur adipiscing elit.";
        boolean isValid = ParamsValidator.isValidSubject(testSubject);
        Assert.assertTrue(isValid);
    }

    @Test (description = "Subject string length = 90")
    public void testIsInvalidSubject() {
        String testSubject = "Mauris leo nisi, lacinia eu ante quis, iaculis bibendum neque. " +
                "Praesent a vestibulum augue";
        boolean isValid = ParamsValidator.isValidSubject(testSubject);
        Assert.assertFalse(isValid);
    }

    @Test (description = "Message string length = 407")
    public void testIsValidMessage() {
        String testMessage = "In eros enim, vehicula a diam nec, iaculis pretium purus. Maecenas imperdiet ut" +
                " metus ut posuere. Ut nec maximus tortor. Phasellus non erat et risus tristique semper eget " +
                "rhoncus risus. Proin id euismod purus. Aenean facilisis tincidunt risus nec lacinia. Mauris " +
                "lobortis pretium pharetra. Nam sed nunc nec purus convallis finibus  Aenean consequat sodales " +
                " consectetur. Aliquam tincidunt porttitor lectus, ";
        boolean isValid = ParamsValidator.isValidMessage(testMessage);
        Assert.assertTrue(isValid);
    }

    @Test (description = "Message string length = 652")
    public void testIsInvalidMessage() {
        String testMessage = "In eros enim, vehicula a diam nec, iaculis pretium purus. Maecenas imperdiet ut" +
                " metus ut posuere. Ut nec maximus tortor. Phasellus non erat et risus tristique semper eget " +
                "rhoncus risus. Proin id euismod purus. Aenean facilisis tincidunt risus nec lacinia. Mauris " +
                "lobortis pretium pharetra. Nam sed nunc nec purus convallis finibus  Aenean eleifend mauris ut" +
                " nunc lobortis interdum in vel enim. Aenean auctor ipsum vitae libero faucibus, et efficitur dui" +
                " dapibus. Aenean consequat sodales consectetur. Aliquam tincidunt porttitor lectus, tempor bibendum " +
                "ligula sollicitudin ac. Pellentesque tristique odio nec sapien hendrerit finibus. Donec et ante ut ";
        boolean isValid = ParamsValidator.isValidMessage(testMessage);
        Assert.assertFalse(isValid);
    }
}