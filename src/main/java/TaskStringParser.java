
public class TaskStringParser {
    public Task parse(String source) {
        String[] parts = source.split(Config.SEPARATOR1);

        String title = parts[0];
        boolean known = Boolean.parseBoolean(parts[1]);
        String added = parts[2];

        Task task = new Task(title);
        task.setKnown(known);
        task.setDatetimeAdded(added);

        return task;
    }

    public String toWritableString(Task input) {

        return input.getTopic() +
                Config.SEPARATOR1 +
                input.getKnown() +
                Config.SEPARATOR1 +
                input.getDatetimeAdded() +
                Config.SEPARATOR1;
    }
}