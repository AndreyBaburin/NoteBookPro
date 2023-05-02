import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    private String topic;
    private boolean isKnown;
    public String datetimeAdded = dataNow();

    public Task(){}
    public Task(String topic) {
        this.topic = topic;
        isKnown = false;
       datetimeAdded = dataNow() + Config.SEPARATOR1;
    }

    public String dataNow() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");
        return now.format(formatter);
    }

    public String getTopic() {
        return topic;
    }

    public Boolean getKnown() {
        return isKnown;
    }

    public String getDatetimeAdded() {
        return datetimeAdded;
    }


    @Override
    public String toString() {
        return  topic + '\'' +
                 isKnown +
                 datetimeAdded + '\'';
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setKnown(boolean known) {
        isKnown = known;
    }

    public void setDatetimeAdded(String datetimeAdded) {
        this.datetimeAdded = datetimeAdded;
    }
}
