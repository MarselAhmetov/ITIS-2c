package service.generator;

public class TaskIdGenerator {
    private static Integer id = 0;

    public static Integer getId() {
        return id++;
    }
}
