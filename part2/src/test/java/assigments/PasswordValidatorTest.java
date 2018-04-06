package assigments;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordValidatorTest {
    private PasswordValidator passwordValidator;

    @BeforeMethod
    public void setUp() {
        passwordValidator = new PasswordValidator();
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            dataProvider = "provideIncorrectStrings",
            dataProviderClass = PasswordValidatorProvider.class)
    public void shouldThrowExceptionWhenIncorrectValueIsPassedToValidation(String incorrectString) {
        //when
        passwordValidator.validate(incorrectString);
        //then exception should be thrown
    }

    @Test(dataProvider = "noNumbersStringProvider",
            dataProviderClass = PasswordValidatorProvider.class)
    public void shouldReturnNotValidWhenStringContainsNoNumericValues(String noNumbersString) {
        //given
        String result;
        //when
        result = passwordValidator.validate(noNumbersString);
        //then
        assertThat(result).isEqualTo("Not valid");
    }

    @Test(dataProvider = "validStringProvider",
            dataProviderClass = PasswordValidatorProvider.class)
    public void shouldReturnExpectedValueWhenValidated(String correctString, String expectedResult) {
        //given
        String result;
        //when
        result = passwordValidator.validate(correctString);
        //then
        assertThat(result).isEqualTo(expectedResult);
    }
}