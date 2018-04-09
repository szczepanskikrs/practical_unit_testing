package mockito;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

import static mockito.Category.*;
import static org.mockito.Mockito.*;


public class RaceResultServiceTest {
    private RaceResultService raceResultService;
    private Client firstClient;
    private Client secondClient;
    private Message message;
    private RaceLogger logger;

    @BeforeMethod
    private void setUp() {
        firstClient = mock(Client.class);
        secondClient = mock(Client.class);
        message = mock(Message.class);
        logger = mock(RaceLogger.class);
        raceResultService = new RaceResultService(logger);
    }

    //zero subscribers
    @Test
    public void nonSubscribersShouldNotReceiveMessage() {
        //when
        raceResultService.send(message, F1);
        //then
        verify(firstClient, never()).receive(message);
        verify(secondClient, never()).receive(message);
    }

    //multiple subscribers
    @Test
    public void subscriberShouldReceiveMessage() {
        //given
        raceResultService.addSubscribers(firstClient, BOAT);
        raceResultService.addSubscribers(secondClient, BOAT);
        //when
        raceResultService.send(message, BOAT);
        //then
        verify(firstClient).receive(message);
        verify(secondClient).receive(message);
    }

    //same client multiple categories
    @Test
    public void shouldSendOnlyOneMessageToClientSubscribedMultipleTimes() {
        //give
        raceResultService.addSubscribers(firstClient, HORSE);
        raceResultService.addSubscribers(firstClient, CAR);
        raceResultService.addSubscribers(firstClient, BOAT);
        //when
        raceResultService.send(message, CAR);
        //then
        verify(firstClient, times(1)).receive(message);
    }

    @Test
    public void removedSubscriberShouldNotReceiveMessages() {
        //given
        raceResultService.addSubscribers(firstClient, F1);
        //when
        raceResultService.removeSubscriber(firstClient, F1);
        raceResultService.send(message, F1);
        //then
        verify(firstClient, never()).receive(message);
    }

    @Test
    public void loggerShouldLogMessagesThatWereSendForSubscribedClient() {
        //given
        raceResultService.addSubscribers(firstClient, F1);
        //when
        raceResultService.send(message, F1);
        //then
        verify(logger).log(firstClient, message);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Not subscribed, unable to unsubscribe.",
            dataProvider = "exceptionProvider")
    public void shouldThrowRuntimeExceptionWhenTryToUnsubscribeClientThatIsNotSubscribed(Category category) {
        //when
        raceResultService.removeSubscriber(firstClient, category);
        //then exception should be thrown
    }

    @DataProvider
    private Iterator<Object[]> exceptionProvider() {
        return new Iterator<Object[]>() {
            int counter = -1;

            @Override
            public boolean hasNext() {
                counter++;
                return counter <= Category.values().length;
            }

            @Override
            public Object[] next() {
                return new Object[]{Category.values()[counter - 1]};
            }
        };
    }
}