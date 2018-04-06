package assigments;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SimpleReservationServiceTest {
    private SimpleReservationService service;

    @BeforeMethod
    public void setUp() {
        service = new SimpleReservationService();
    }

    @Test(dataProvider = "incorrectValuesProvider",
            expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenIncorrectValueIsPassedForBooking(Integer incorrectValue) {
        //when
        service.bookHour(incorrectValue);
        //then exception should be thrown
    }

    @Test
    public void shouldReturnEmptyListWhenNothingWasBooked() {
        //given
        List<Integer> result;
        //when
        result = service.listBookedHours();
        //then
        assertThat(result.isEmpty()).isTrue();
    }

    @DataProvider
    private Object[][] incorrectValuesProvider() {
        return new Object[][]{{-32},
                {34},
                {43},
                {24},
                {999},
                {-4092},
                {45216},
                {null}
        };
    }

    @Test()
    public void shouldReturnCorrectListWhenBookingWasSuccessful() {
    }
}