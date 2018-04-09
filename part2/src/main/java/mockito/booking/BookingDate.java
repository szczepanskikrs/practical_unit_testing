package mockito.booking;

import java.time.Month;


public interface BookingDate {
    Month getMonth();

    int getDay();

    int getStartHour();

    int getEndHour();
}
