package model.state;

import context.TaskContext;
import model.Argument;

public class Assigned extends State {

    public Assigned(TaskContext task) {
        super(task);
    }

    private Assigned(State state, TaskContext taskContext) {
        super(state, taskContext);
    }

    public void up(Argument argument) {
        task.changeState(new InProgess(task));
    }

    public void down(Argument argument) {
        task.changeState(new Open(task));
    }

    @Override
    public State copy(TaskContext taskContext) {
        return new Assigned(this, taskContext);
    }

}
