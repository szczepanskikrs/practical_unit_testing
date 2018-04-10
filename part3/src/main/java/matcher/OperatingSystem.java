package matcher;

public class OperatingSystem {
    private int nbOfBits;
    private String name;
    private String version;
    private int releaseYear;

    OperatingSystem(int nbOfBits, String name, String version, int releaseYear) {
        this.nbOfBits = nbOfBits;
        this.name = name;
        this.version = version;
        this.releaseYear = releaseYear;
    }

    public int getNbOfBits() {
        return nbOfBits;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
}
