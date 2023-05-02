import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
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

    public void readAll2() throws IOException {
        List<String> lines = Files.readAllLines(Config.FILE_PATH);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    public boolean add(Task newTopic) {
        String writableString = parser.toWritableString(newTopic) + System.lineSeparator();
        try {
            Files.writeString(Config.FILE_PATH, writableString, StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteTask(int line) throws IOException {
        List<String> lines = Files.readAllLines(Config.FILE_PATH);
        try {
            lines.remove(line);
            System.out.println("Тема удалена");
            new FileWriter(Config.FILE_PATH.toFile()).close();
            for (String newLine : lines) {
                String[] split = newLine.split(Config.SEPARATOR1);
                Task task = new Task(split[0]);
                add(task);
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Введите число");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Темы с таким номером не существует");
            System.out.println("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        readAll2();
    }

    public void deleteTask2(int line) throws IOException {
        List<String> lines = Files.readAllLines(Config.FILE_PATH);
        lines.remove(line);
        System.out.println("Тема удалена");
        new FileWriter(Config.FILE_PATH.toFile()).close();
        for (String newLine : lines) {
            try {
                Files.writeString(Config.FILE_PATH, newLine, StandardOpenOption.APPEND);
                Files.writeString(Config.FILE_PATH, "\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        readAll2();
    }

}