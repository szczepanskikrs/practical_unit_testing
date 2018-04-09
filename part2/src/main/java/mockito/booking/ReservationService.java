package mockito.booking;

import java.util.*;
import java.util.stream.Collectors;

class ReservationService {
    private Map<RoomID, Room> reservations;

    ReservationService() {
        this.reservations = new EnumMap<>(RoomID.class);
    }

    List<Room> listAllRooms() {
        return new ArrayList<>(reservations.values());
    }

    void addRoom(RoomID roomID, Room room) {
        Objects.requireNonNull(room);
        if (reservations.containsKey(roomID)) {
            throw new IllegalArgumentException("Can't change room once it was assigned to ID");
        }
        room.assignId(roomID);
        reservations.put(roomID, room);
    }

    List<Room> listFreeRooms(BookingDate date) {
        return reservations.values().stream()
                .filter(p -> p.isFree(date))
                .collect(Collectors.toList());
    }

    void book(RoomID roomId, BookingDate bookingDate) {
        if (reservations.get(roomId) == null || bookingDate == null) {
            throw new IllegalArgumentException("Can't book room that is not assigned or date that does not exist");
        }
        reservations.get(roomId).reserveForDate(bookingDate);
    }

    void book(BookingDate bookingDate, int groupSize) {
        if (groupSize <= 0) {
            throw new IllegalArgumentException("Group size can't be zero or negative!");
        }
        Room availableRooms = reservations.values().stream()
                .filter(p -> p.size() >= groupSize)
                .filter(p -> p.isFree(bookingDate))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No room with correct parameters found!"));

        availableRooms.reserveForDate(bookingDate);
    }

    void book(BookingDate bookingDate, int groupSize, Equipment equipment) {
        if (groupSize <= 0) {
            throw new IllegalArgumentException("Group size can't be zero or negative");
        }
        Room room = reservations.values().stream()
                .filter(p -> p.size() >= groupSize)
                .filter(p -> p.containsEquipment(equipment))
                .filter(p -> p.isFree(bookingDate))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No room with correct parameters found!"));
        room.reserveForDate(bookingDate);
    }

    boolean checkIfFree(RoomID roomId, BookingDate bookingDate) {
        if (reservations.get(roomId) == null || bookingDate == null) {
            throw new IllegalArgumentException("Can't check room that is not assigned or date that does not exist");
        }
        return reservations.get(roomId).isFree(bookingDate);
    }
}
