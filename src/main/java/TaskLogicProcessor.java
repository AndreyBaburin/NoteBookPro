import java.io.IOException;
import java.util.List;

public interface TaskLogicProcessor {
    public boolean add(Task newTopic) throws IOException;

    List<Task> readAll() throws IOException;
}
