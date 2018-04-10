package primitivelist;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class SimpleListTest {
    private SimpleList list;
    private User userOne;
    private User userTwo;

    @BeforeMethod
    public void setUp() {
        userOne = mock(User.class);
        userTwo = mock(User.class);
        list = new SimpleList();
    }

    @Test
    public void shouldInstantiateListThatIsEmpty() {
        //given
        SimpleList list;
        //when
        list = new SimpleList();
        //then
        assertThat(list.getUsers()).isEmpty();
    }

    @Test
    public void shouldReturnNumberOfUsersEqualToExpected() {
        //when
        list.addUser(userOne);
        //then
        assertThat(list.getUsers()).isNotEmpty()
                .containsExactly(userOne)
                .doesNotHaveDuplicates();
    }

    @Test
    public void shouldReturnTwoUsersIfTwoWereAdded() {
        //when
        list.addUser(userOne);
        list.addUser(userTwo);
        //then
        assertThat(list.getUsers()).isNotEmpty()
                .doesNotHaveDuplicates()
                .containsExactly(userOne, userTwo);
    }
}