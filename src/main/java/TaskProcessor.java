import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TaskProcessor {
    TaskStringParser parser = new TaskStringParser();

    public List<Task> readAll() throws IOException {
        List<String> lines = Files.readAllLines(Config.FILE_PATH);
        return lines.stream()
                .map(l -> parser.parse(l))
                .collect(Collectors.toList());
    }


    public List<Task> readAllTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        List<String> lines = Files.readAllLines(Config.FILE_PATH);
        for (String line : lines) {
            if (!line.isEmpty()) {
                Task task = parser.parse(line);
                tasks.add(task);
            }
        }
        return tasks;
    }

    public boolean add(Task newTopic) throws IOException {
        String writableString = parser.toWritableString(newTopic) + System.lineSeparator();
        try {
            Files.writeString(Config.FILE_PATH, writableString, StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addWithoutRepeat(Task newTopic) throws IOException {
        if (readAllTasks().isEmpty()) {
            add(newTopic);
        } else {
            HashMap<String,Task> tasksMap = new HashMap<>();
            for (Task task : readAllTasks()) {
                tasksMap.put(task.getTopic(), task);
            }
            if (!tasksMap.containsKey(newTopic.getTopic())) {
                add(newTopic);
            } else {
                System.out.println("Тема " + newTopic.getTopic() + " уже существует" );
            }
        }
    }


    public void deleteTaskByName(String name) throws IOException {
        List<Task> tasks = readAllTasks();
        Task delete = null;
        new FileWriter(Config.FILE_PATH.toFile()).close();
        for (Task task : tasks) {
            if (task.getTopic().equals(name)) {
                delete=task; //ConcurrentModificationException
            }
        }
            tasks.remove(delete);

        for (Task task : tasks) {
            add(task);
        }
    }

    public void deleteTaskByNumberLine(int line) throws IOException {
        List<Task> tasks = readAllTasks();
        new FileWriter(Config.FILE_PATH.toFile()).close();
        tasks.remove(line);
        for (Task task : tasks) {
            add(task);
        }
    }

    public void deleteTask(Task t) throws IOException {
        List<Task> tasks = readAllTasks();
        Task delete = null;
        new FileWriter(Config.FILE_PATH.toFile()).close();
        for (Task task : tasks) {
            if (task.getTopic().equals(t.getTopic())) {
                delete = task; //ConcurrentModificationException
            }
        }
        tasks.remove(delete);

        for (Task task : tasks) {
            add(task);
        }
    }
}
