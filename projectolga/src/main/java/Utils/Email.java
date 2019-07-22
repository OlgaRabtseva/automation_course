package Utils;

public class Email
{
    private int id;
    private String topic;
    private String body;

    private Email() {}

    public int getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + this.id +
                ", topic='" + this.topic + '\'' +
                ", body='" + this.body + '\'' +
                '}';
    }

    public static Builder anEmail() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String topic;
        private String body;

        public Builder() {}

        public Builder withId(final int id) {
            this.id = id;
            return this;
        }

        public Builder withTopic(final String topic) {
            this.topic = topic;
            return this;
        }

        public Builder withBody(final String body) {
            this.body = body;
            return this;
        }

        public Email build() {
            Email email = new Email();
            email.id = this.id;
            email.topic = this.topic;
            email.body = this.body;
            return email;
        }
    }
}
