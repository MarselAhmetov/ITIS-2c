package model.state;

import context.TaskContext;
import model.Argument;

public class Draft extends State {

    public Draft(TaskContext task) {
        super(task);
    }

    private Draft(State state, TaskContext taskContext) {
        super(state, taskContext);
    }

    public void up(Argument argument) {
        task.changeState(new Open(task));
    }

    public void down(Argument argument) {

    }

    @Override
    public State copy(TaskContext taskContext) {
        return new Draft(this, taskContext);
    }

}
