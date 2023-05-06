import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskProcessorTest {

    @Test
    void testReadAll2() throws IOException {
        List<String> expected = Files.readAllLines(Config.FILE_PATH);
        List<String> actual = new ArrayList<>();
        actual.add("Topic1; false; 27.04.2023, 21:07;");
        actual.add("Topic2; false; 27.04.2023, 21:07;");
        for (String line : expected) {
            for (String line1 : actual) {
                assertEquals(line1, line);
            }
        }
    }

    @Test
    void readAll3() throws IOException {
        TaskProcessor processor = new TaskProcessor();
        TaskStringParser parser = new TaskStringParser();
        List<Task> expected = processor.readAll3();
        List<Task> actual = new ArrayList<>();
        Path test = Path.of("MyTopicTest.txt");
        List<String> lines = Files.readAllLines(test);
        for (String line : lines) {
            if (!line.isEmpty()) {
                Task task = parser.parse(line);
                actual.add(task);
            }
        }
        assertEquals(expected, actual);
    }

    @Test
   void addTask() {
        TaskProcessor processor = new TaskProcessor();
        Task task1 = new Task("Example");
        assertTrue(processor.add(task1));
    }


    @Test
    void deleteTask2() throws IOException {
        TaskProcessor processor = new TaskProcessor();
        Task task1 = new Task("Example1");
        Task task2 = new Task("Example2");
        Task task3 = new Task("Example3");
        processor.deleteTask2(1);
        List<String> expected = Files.readAllLines(Config.FILE_PATH);
        List<String> actual = Files.readAllLines(Path.of("MyTopicTest.txt"));
        Assertions.assertEquals(expected, actual);
    }


//    @Test
//    void deleteTask3() throws IOException {
//        TaskProcessor processor = new TaskProcessor();
//        Task task1 = new Task("Topic 1");
//        Task task2 = new Task("Topic 2");
//        Task task3 = new Task("Topic 3");
//        Task task4 = new Task("Topic 4");
//        processor.deleteTask3(2);
//        List<String> expected = Files.readAllLines(Config.FILE_PATH);
//        List<String> actual = Files.readAllLines(Path.of("MyTopicTest.txt"));
//        Assertions.assertEquals(expected, actual);
//
//    }
}