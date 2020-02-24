package model.state;

import context.TaskContext;
import model.Argument;

public class InProgess extends State {

    public InProgess(TaskContext task) {
        super(task);
    }

    private InProgess(State state, TaskContext taskContext) {
        super(state, taskContext);
    }

    public void up(Argument argument) {
        task.changeState(new Resolved(task));
    }

    public void down(Argument argument) {
        task.changeState(new Assigned(task));
    }

    @Override
    public State copy(TaskContext taskContext) {
        return new InProgess(this, taskContext);
    }

}
