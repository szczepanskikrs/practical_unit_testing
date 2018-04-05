package basic;

import org.testng.annotations.DataProvider;

public class StringExerciseDataProvider {
    @DataProvider
    public Object[][] correctStringProvider() {
        return new Object[][]{
                {"pies", "seip"},
                {"wololo", "ololow"},
                {"szczupak", "kapuzczs"},
                {"1011", "1101"}};
    }
}
