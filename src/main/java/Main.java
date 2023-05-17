import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        prepareFile();
        TaskProcessor processor = new TaskProcessor();
        Task task1 = new Task("Topic 1");
        Task task2 = new Task("Topic 2");
        Task task3 = new Task("Topic 3");
        Task task4 = new Task("Topic 4");
        Task task5 = new Task("Topic 1");
        processor.addWithoutRepeat(task1);
        processor.addWithoutRepeat(task2);
        processor.addWithoutRepeat(task3);
        processor.addWithoutRepeat(task4);
        processor.addWithoutRepeat(task5);
        processor.readAllTasks().forEach(System.out::println);
        System.out.println();
        processor.deleteTaskByName("Topic 1");
        processor.readAllTasks().forEach(System.out::println);
        System.out.println();
        processor.deleteTaskByName(task2);
        processor.readAllTasks().forEach(System.out::println);
    }

    private static void prepareFile() throws IOException {
        Path pathToFile = Paths.get(Config.FILE_NAME);
        if (Files.exists(pathToFile)) {

            System.out.println("Файл найден");
        } else {
            System.out.println("Новый файл создан");
            Files.createFile(pathToFile);
        }
    }

    private static void printMenu() {
        System.out.println("Что вы хотите сделать?");
        System.out.println("Добавить тему - 1");
        System.out.println("Прочитать файл - 2");
        System.out.println("Удалить тему - 3");
        System.out.println("Выход - 0");
    }
}
