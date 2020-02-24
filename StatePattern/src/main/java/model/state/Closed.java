package model.state;

import context.TaskContext;
import model.Argument;

public class Closed extends State {

    public Closed(TaskContext task) {
        super(task);
    }

    private Closed(State state, TaskContext taskContext) {
        super(state, taskContext);
    }

    public void up(Argument argument) {

    }

    public void down(Argument argument) {

    }

    @Override
    public State copy(TaskContext taskContext) {
        return new Closed(this, taskContext);
    }

}
