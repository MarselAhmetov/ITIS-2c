package context.proxy;

import context.Task;
import context.TaskContext;
import model.Argument;
import model.Log;
import model.state.State;
import repository.LogFileRepository;
import repository.Repository;

import java.io.File;
import java.util.Date;

public class TaskProxy implements TaskContext {

    private TaskContext taskContext;
    private Repository<Log> repository;

    public TaskProxy(Integer developerId, Integer testerId, String text, String error) {
        this.taskContext = new Task(developerId, testerId, text, error);
        this.repository = new LogFileRepository(new File("log.txt"));
    }
    private TaskProxy(TaskProxy taskProxy) {
        this.taskContext = (TaskContext) taskProxy.taskContext.copy();
        this.repository = new LogFileRepository(new File("log.txt"));
    }

    public void up(Argument argument) {
        repository.add(new Log(new Date().toString(), "up", this.getClass().getName()));
        taskContext.up(argument);
    }

    public void down(Argument argument) {
        repository.add(new Log(new Date().toString(), "down", this.getClass().getName()));
        taskContext.down(argument);
    }

    public void changeState(State state) {
        repository.add(new Log(new Date().toString(), "changeState", this.getClass().getName()));
        taskContext.changeState(state);
    }

    public TaskProxy copy() {
        repository.add(new Log(new Date().toString(), "copy", this.getClass().getName()));
        return new TaskProxy(this);
    }

    public Integer getId() {
        return taskContext.getId();
    }
}
