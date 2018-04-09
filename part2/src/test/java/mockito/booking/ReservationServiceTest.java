package mockito.booking;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static mockito.booking.Equipment.WHITEBOARD;
import static mockito.booking.RoomID.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class ReservationServiceTest {
    private SoftAssertions softlyAssert = new SoftAssertions();
    private ReservationService reservationService;
    private Room roomOne;
    private Room roomTwo;
    private BookingDate bookingDate;

    @BeforeMethod
    public void setUp() {
        bookingDate = mock(BookingDate.class);
        roomOne = mock(Room.class);
        roomTwo = mock(Room.class);
        reservationService = new ReservationService();
    }

    @Test
    public void shouldReturnListOfAllRooms() {
        //given
        List<Room> result;
        int expectedNumberOfRooms = 2;
        //when
        reservationService.addRoom(ROOM_1, roomOne);
        reservationService.addRoom(ROOM_2, roomTwo);
        result = reservationService.listAllRooms();
        //then
        verify(roomOne, only()).assignId(ROOM_1);
        verify(roomTwo, only()).assignId(ROOM_2);
        assertThat(result.size()).isEqualTo(expectedNumberOfRooms);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Can't change room once it was assigned to ID")
    public void shouldThrowIllegalArgumentExceptionWhenTriedToReassignRoomIDToAnotherRoom() {
        //when
        reservationService.addRoom(ROOM_1, roomOne);
        reservationService.addRoom(ROOM_1, roomTwo);
        //then exception should be thrown
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenNullValueIsPassed() {
        //when
        reservationService.addRoom(ROOM_1, null);
        //then exception should be thrown
    }

    @Test
    public void shouldListOnlyFreeRooms() {
        //given
        List<Room> resultList;
        int expectedListSize = 1;
        reservationService.addRoom(ROOM_1, roomOne);
        when(roomOne.isFree(bookingDate)).thenReturn(true);
        reservationService.addRoom(ROOM_2, roomTwo);
        when(roomTwo.isFree(bookingDate)).thenReturn(false);
        //when
        resultList = reservationService.listFreeRooms(bookingDate);
        //then
        softlyAssert.assertThat(resultList.size()).isEqualTo(expectedListSize);
        softlyAssert.assertThat(resultList.contains(roomOne)).isTrue();
        verify(roomOne).isFree(bookingDate);
        verify(roomTwo).isFree(bookingDate);
        softlyAssert.assertAll();
    }

    @Test
    public void shouldBookRoomCorrectly() {
        //given
        reservationService.addRoom(ROOM_1, roomOne);
        //when
        reservationService.book(ROOM_1, bookingDate);
        //then
        verify(roomOne).reserveForDate(bookingDate);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Can't book room that is not assigned or date that does not exist")
    public void shouldThrowExceptionWhenTriedToBookRoomThatIsNotAssigned() {
        //when
        reservationService.book(ROOM_1, bookingDate);
        //then exception should be thrown
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Can't check room that is not assigned or date that does not exist")
    public void shouldThrowExceptionWhenTriedToCheckRoomThatIsNotAssigned() {
        //when
        reservationService.checkIfFree(ROOM_1, bookingDate);
        //then exception should be thrown
    }

    @Test(dataProvider = "groupSizeProvider")
    public void shouldBookCorrectRoomForGroupOfCorrectSize(int correctGroupSize) {
        //given
        reservationService.addRoom(ROOM_1, roomOne);
        //when
        when(roomOne.size()).thenReturn(23);
        when(roomOne.isFree(bookingDate)).thenReturn(true);
        reservationService.book(bookingDate, correctGroupSize);
        //then
        verify(roomOne).reserveForDate(bookingDate);
        verify(roomOne).size();
    }

    @Test
    public void shouldBookCorrectRoomForGroupOfCorrectSizeAndCorrectEquipment() {
        //given
        int roomSize = 100;
        int groupSize = 79;
        reservationService.addRoom(ROOM_LARGE, roomOne);
        when(roomOne.isFree(bookingDate)).thenReturn(true);
        when(roomOne.size()).thenReturn(roomSize);
        when(roomOne.containsEquipment(WHITEBOARD)).thenReturn(true);
        //when
        reservationService.book(bookingDate, groupSize, WHITEBOARD);
        //then
        verify(roomOne).isFree(bookingDate);
        verify(roomOne).containsEquipment(WHITEBOARD);
        verify(roomOne).size();

    }

    @DataProvider
    public static Object[][] groupSizeProvider() {
        return new Object[][]{
                {1},
                {4},
                {5},
                {6},
                {3},
                {9},
                {22},
                {23},
                {12},
        };
    }
}
