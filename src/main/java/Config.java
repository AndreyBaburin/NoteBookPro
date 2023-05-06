import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    private Config() {}
    public static final String FILE_NAME = "MyTopic.txt";
    public static final Path FILE_PATH = Paths.get(FILE_NAME);
    Path test = Path.of("MyTopicTest.txt");
    public static String SEPARATOR1 = ";";
}
