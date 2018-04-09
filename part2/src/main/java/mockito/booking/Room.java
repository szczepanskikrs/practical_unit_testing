package mockito.booking;

import java.util.List;

interface Room {
    void assignId(RoomID id);

    boolean isFree(BookingDate bookingDate);

    void reserveForDate(BookingDate bookingDate);

    int size();

    List<Equipment> listEquipment();

    void addEquipment(Equipment equipment);

    boolean containsEquipment(Equipment equipment);
}
