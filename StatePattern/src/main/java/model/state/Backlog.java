package model.state;

import context.TaskContext;
import model.Argument;

public class Backlog extends State {

    public Backlog(TaskContext task) {
        super(task);
    }

    private Backlog(State state, TaskContext taskContext) {
        super(state, taskContext);
    }

    public void up(Argument argument) {
        task.changeState(new Open(task));
    }

    public void down(Argument argument) {
    }

    @Override
    public State copy(TaskContext taskContext) {
        return new Backlog(this, taskContext);
    }

}
