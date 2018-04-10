package matcher;

import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

class OperatingSystemAssert extends GenericAssert<OperatingSystemAssert, OperatingSystem> {

    private OperatingSystemAssert(Class<OperatingSystemAssert> selfType, OperatingSystem actual) {
        super(selfType, actual);
    }


    static OperatingSystemAssert assertThat(OperatingSystem actual) {
        return new OperatingSystemAssert(OperatingSystemAssert.class, actual);
    }

    OperatingSystemAssert is64Bit() {
        isNotNull();
        String errorMessage = String.format("Expected operating system to be <%s>bit but was <%s>bit", 64, actual.getNbOfBits());
        Assertions.assertThat(actual.getNbOfBits()).overridingErrorMessage(errorMessage).isEqualTo(64);
        return this;
    }

    OperatingSystemAssert isNamed(String name) {
        isNotNull();
        String errorMessage = String.format("Expected operating system to be named <%s> but was named <%s>bit", name, actual.getName());
        Assertions.assertThat(actual.getName()).overridingErrorMessage(errorMessage).isEqualTo(name);
        return this;
    }

    OperatingSystemAssert isVersion(String version) {
        isNotNull();
        String errorMessage = String.format("Expected operating system to be in version <%s>bit but was in version <%s>bit", version, actual.getVersion());
        Assertions.assertThat(actual.getVersion()).overridingErrorMessage(errorMessage).isEqualTo(version);
        return this;
    }

    OperatingSystemAssert wasReleasedInYear(int year) {
        isNotNull();
        String errorMessage = String.format("Expected operating system to be released in <%s>bit but was released in <%s>bit", year, actual.getReleaseYear());
        Assertions.assertThat(actual.getReleaseYear()).overridingErrorMessage(errorMessage).isEqualTo(year);
        return this;
    }
}
