package legacy;

class Email {
    private final String address;
    private final String title;
    private final String body;

    Email(String address, String title, String body) {

        this.address = address;
        this.title = title;
        this.body = body;
    }

    public String getAddress() {
        return address;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
