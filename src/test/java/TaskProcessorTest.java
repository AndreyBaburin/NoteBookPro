import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskProcessorTest {

    @Test
    void testReadAll2() throws IOException {
        List<String> expected = Files.readAllLines(Config.FILE_PATH);
        List<String> actual = new ArrayList<>();
        actual.add("Example; false; 27.04.2023, 21:07;");
        for (String line : expected) {
            for (String line1 : actual) {
                assertEquals(line1, line);
            }
        }

    }

    @Test
    void addTopic() {
        TaskProcessor processor = new TaskProcessor();
        Task task1 = new Task("Example");
        assertTrue(processor.add(task1));
    }

}