package assigments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PasswordValidatorTestJunit5 {
    private PasswordValidator passwordValidator;

    @BeforeEach
    void setUp() {
        passwordValidator = new PasswordValidator();
    }

    @Test
    void shouldThrowExceptionWhenEmptyStringIsPassedAsParameter() {
        //given
        String incorrectString = "";
        //when
        assertThrows(IllegalArgumentException.class,
                () -> passwordValidator.validate(incorrectString));
        //then exception should be thrown
    }

    @Test
    void shouldThrowExceptionWhenNullIsPassedAsParameter() {
        //when
        assertThrows(IllegalArgumentException.class,
                () -> passwordValidator.validate(null));
        //then exception should be thrown
    }

    @ParameterizedTest
    @ValueSource(strings = {"kot", "pies", "tyranozaur", "$%#42re", "Totmr1,4,56%%tj"})
    void shouldReturnNotValidWhenIncorrectStringIsPassedAsParameter(String incorrectString) {
        //given
        String result;
        //when
        result = passwordValidator.validate(incorrectString);
        //then
        assertThat(result).isEqualTo("Not valid");
    }

    @ParameterizedTest
    @CsvSource({"rot432kr,432", "witam3454q13,3454", "wolt346,346",
            "%$@%443%$,443", "JamnikMroku666,666", "t<><>423<>,423",
            "Tyranoza783r,783", "5413516316,5413516316"})
    void shouldReturnExpectedValueWhenCorrectStringIsPassedAsParameter(String correctString, String expectedResult) {
        //given
        String result;
        //when
        result = passwordValidator.validate(correctString);
        //then
        assertThat(result).isEqualTo(expectedResult);

    }
}