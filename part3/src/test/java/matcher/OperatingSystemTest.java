package matcher;

import org.testng.annotations.Test;

import static matcher.OperatingSystemAssert.assertThat;

public class OperatingSystemTest {
    @Test
    public void systemCreatedWith64BitArchitectureShouldBe64Bit() {
        OperatingSystem operatingSystem = new OperatingSystem(64, "Windows", "10", 2015);
        assertThat(operatingSystem)
                .is64Bit()
                .isNamed("Windows")
                .isVersion("10")
                .wasReleasedInYear(2015);
    }
}