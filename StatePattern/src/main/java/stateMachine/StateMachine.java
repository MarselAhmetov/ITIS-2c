package stateMachine;

import context.TaskContext;
import model.Argument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateMachine {
    private HashMap<Integer, TaskContext> tasks;

    public StateMachine() {
        this.tasks = new HashMap<>();
    }

    public void addTask(TaskContext taskContext) {
        tasks.put(taskContext.getId(), taskContext);
    }

    public void upAllTasks(Argument argument) {
        for (TaskContext value : tasks.values()) {
             value.up(argument);
        }
    }

    public TaskContext getTaskById(Integer id) {
        return tasks.get(id);
    }

}
