package stateMachine;

import context.TaskContext;
import model.Argument;

import java.util.ArrayList;

public class StateMachine {
    private ArrayList<TaskContext> tasks;

    public StateMachine() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(TaskContext taskContext) {
        tasks.add(taskContext);
    }

    public void upAllTasks(Argument argument) {
        for (TaskContext task : tasks) {
            task.up(argument);
        }
    }

    public TaskContext getTaskById(Integer id) {
        for (TaskContext task : tasks) {
            if (task.getId() == id)
                return task;
        }
        return null;
    }

}
