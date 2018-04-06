package assigments;

import java.util.ArrayList;
import java.util.List;

class SimpleReservationService {
    private final List<Integer> reservations = new ArrayList<>();

    List<Integer> listBookedHours() {
        return reservations;
    }

    void bookHour(Integer hour) {
        if (hour == null || hour < 0 || hour > 23) {
            throw new IllegalArgumentException();
        }

    }
}
