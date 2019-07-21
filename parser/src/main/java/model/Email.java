package model;

public class Email
{
    private int id;
    private String topic;
    private String body;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + this.id +
                ", topic='" + this.topic + '\'' +
                ", body='" + this.body + '\'' +
                '}';
    }
}
