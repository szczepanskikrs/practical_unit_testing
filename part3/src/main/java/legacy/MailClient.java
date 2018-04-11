package legacy;

class MailClient {
    private EmailServer emailServer;

    void sendEmail(String address, String title, String body) {
        Email email = generateEmailMessage(address, title, body);
        if (emailServer == null) {
            emailServer = p -> {
                new EmailServer() {
                    @Override
                    public void sendEmail(Email email) {
                        // some logic here
                    }
                };
            };
        }
        emailServer.sendEmail(email);
    }

    Email generateEmailMessage(String address, String title, String body) {
        return new Email(address, title, body);
    }

    void setEmailServer(EmailServer emailServer) {
        this.emailServer = emailServer;
    }
}