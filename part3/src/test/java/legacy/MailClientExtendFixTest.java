package legacy;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class MailClientExtendFixTest {
    private Email email;
    private EmailServer emailServer;
    private MailClient client;

    @BeforeMethod
    public void setUp() {
        client = spy(new MailClient());
        email = mock(Email.class);
        emailServer = mock(EmailServer.class);
        client.setEmailServer(emailServer);
    }

    @Test(dataProvider = "correctEmailDataProvider")
    public void sentEmailShouldBeCorrect(String correctAddress, String correctTitle, String correctBody) {
        //given
        doReturn(email).when(client).generateEmailMessage(correctAddress, correctTitle, correctBody);
        //when
        client.sendEmail(correctAddress, correctTitle, correctBody);
        verify(emailServer).sendEmail(email);
    }

    @DataProvider
    public static Object[][] correctEmailDataProvider() {
        return new Object[][]{
                {"Adress", "Title", "Body"},
                {"wolo@wolo.wolo", "Trolling", "Letters goes here"},
                {"test@test.en", "Testing", "WTF? WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?" +
                        "WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?" +
                        "WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?WTF?"},
                {"nice@o2.pl", "Diet", "Don't eat fat things!"},
                {"lorem@gmail.com", "Lorem Ipsum", "Lorem ipsum dolor sit amet, ne est omnium " +
                        "voluptaria suscipiantur, ei quo debet quaeque albucius, per ex amet " +
                        "tation suscipiantur. Mei ea assum dolor nominati, simul impedit gloriatur " +
                        "cum et. Ex habeo doming mel, ea nostrud omnesque postulant mei. Ex illud" +
                        " quaeque has, ut saperet sadipscing interpretaris pro.\n" +
                        "\n" +
                        "An usu enim erroribus, mel placerat consequat ne, in dictas molestie tractatos" +
                        " est. Mei ad vidisse dolores inciderint, repudiare temporibus vis ne. Dico sonet " +
                        "lucilius per ea, stet idque gubergren duo no, ex recteque splendide quo. Pro an" +
                        " augue facete phaedrum. Facete epicurei interesset eum ei, quo id dicit electram. " +
                        "Ius odio debet ei. Mea case honestatis eu, eam elit epicurei ei.\n" +
                        "\n" +
                        "No sed quidam aliquam appetere, mei vide convenire id. Homero libris bonorum id " +
                        "eam, ad virtute voluptatibus eos. Eos an prima mandamus, ea alienum apeirian vel." +
                        " Iisque eloquentiam mel id, an vim inani verear torquatos. Sale iuvaret civibus no est," +
                        " omnes propriae at cum, in eripuit dissentiet per. Iuvaret mandamus no cum.\n" +
                        "\n" +
                        "Eum ut feugait deleniti, ea purto noster suscipiantur nam. Recusabo appellantur " +
                        "definitiones pro te. Habeo quaerendum ex has, et est ubique abhorreant moderatius," +
                        " nam verterem convenire id. Ut esse posse tacimates vel.\n" +
                        "\n" +
                        "Tollit altera vivendum per in. Et his prompta adversarium, ei magna utinam quaestio " +
                        "vel, ad solum sententiae cum. Ex labitur equidem appetere vel. Eu vix semper inermis, " +
                        "ad commodo oportere per, id has quis munere."}

        };
    }
}