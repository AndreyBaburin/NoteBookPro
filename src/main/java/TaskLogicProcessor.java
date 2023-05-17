import java.io.IOException;
import java.util.List;

public interface TaskLogicProcessor {
    public boolean add(Task newTopic) throws IOException;
    public List<Task> readAllTasks() throws IOException;
    public List<Task> readAll() throws IOException;
    public void addWithoutRepeat(Task newTopic) throws IOException;
    public void deleteTaskByName(String name) throws IOException;
    public void deleteTaskByNumberLine(int line) throws IOException;
}
