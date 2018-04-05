package basic;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StringExerciseTest {
    private StringExercise stringExercise;

    @BeforeMethod
    public void setUp() {
        stringExercise = new StringExercise();
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Parameter can't be null")
    public void shouldThrowIllegalArgumentExceptionWhenParameterIsNull() {
        //when
        stringExercise.reverse(null);
        //then exception should be thrown
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Parameter can't be empty")
    public void shouldThrowIllegalArgumentExceptionWhenParameterIsEmpty() {
        //when
        stringExercise.reverse("");
        //then exception should be thrown
    }

    @Test(dataProvider = "correctStringProvider",
            dataProviderClass = StringExerciseDataProvider.class)
    public void shouldReverseStringCorrectly(String toReverse, String correctReverseResult) {
        //given
        String assertionString;
        //when
        assertionString = stringExercise.reverse(toReverse);
        //then
        assertThat(assertionString).isEqualTo(correctReverseResult);
    }
}