package assigments;

import org.testng.annotations.DataProvider;

public class PasswordValidatorProvider {

    @DataProvider
    public Object[][] provideIncorrectStrings() {
        return new Object[][]{
                {null},
                {""}
        };
    }

    @DataProvider
    public static Object[][] noNumbersStringProvider() {
        return new Object[][]{
                {"pies"},
                {"Brzeczyszczykiewicz"},
                {"Dinozaur"},
                {"Piesozaur"},
                {"As &*$*%*#*@"},
                {" %*($(#"},
                {"Kot dw lesie je 42@"},
                {"ferese%45##@55$31$14%42@"},
        };
    }

    @DataProvider
    public static Object[][] validStringProvider() {
        return new Object[][]{
                {"Wolo 123", "123"},
                {"12 343", "343"},
                {"123", "123"},
                {"Samochód miał 450 koni mechanicznych i 6 biegów", "450"},
                {"$@#4332$#@766$#kjem43Jtmt532", "4332,766,532"}

        };
    }
}
