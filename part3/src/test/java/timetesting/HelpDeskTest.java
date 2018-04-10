package timetesting;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Calendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HelpDeskTest {
    private HelpDesk helpDesk;
    private Calendar calendar;
    private Issue issue;

    private static final int OVERTIME_HOUR = 18;
    private static final int REGULAR_HOUR = 12;

    private static final int SUNDAY = 1;
    private static final int MONDAY = 2;
    private static final int TUESDAY = 3;
    private static final int WEDNESDAY = 4;
    private static final int THURSDAY = 5;
    private static final int FRIDAY = 6;
    private static final int SATURDAY = 7;

    @BeforeMethod
    public void setUp() {
        issue = mock(Issue.class);
        helpDesk = new HelpDesk();
        calendar = mock(Calendar.class);
    }

    @Test(dataProvider = "weekendDaysProvider")
    public void shouldReturnFalseWhenWeekend(int correctWeekendDay) {
        //given
        boolean result;
        when(calendar.get(Calendar.DAY_OF_WEEK)).thenReturn(correctWeekendDay);
        //when
        result = helpDesk.willHandleIssue(issue, calendar);
        //then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenFridayAndAfter5pm() {
        //given
        boolean result;
        //when
        when(calendar.get(Calendar.DAY_OF_WEEK)).thenReturn(FRIDAY);
        when(calendar.get(Calendar.HOUR_OF_DAY)).thenReturn(OVERTIME_HOUR);
        result = helpDesk.willHandleIssue(issue, calendar);
        //then
        assertThat(result).isFalse();
    }

    @Test(dataProvider = "workingDaysProvider")
    public void shouldReturnTrueWhenWorkingDaysAndRegularHours(int correctWorkingDay) {
        //given
        boolean result;
        //when
        when(calendar.get(Calendar.DAY_OF_WEEK)).thenReturn(correctWorkingDay);
        when(calendar.get(Calendar.HOUR_OF_DAY)).thenReturn(REGULAR_HOUR);
        result = helpDesk.willHandleIssue(issue, calendar);
        //then
        assertThat(result).isTrue();
    }

    @DataProvider
    public static Object[][] weekendDaysProvider() {
        return new Object[][]{
                {SATURDAY},
                {SUNDAY}
        };
    }

    @DataProvider
    public static Object[][] workingDaysProvider() {
        return new Object[][]{
                {MONDAY},
                {TUESDAY},
                {WEDNESDAY},
                {THURSDAY},
                {FRIDAY},
        };
    }
}