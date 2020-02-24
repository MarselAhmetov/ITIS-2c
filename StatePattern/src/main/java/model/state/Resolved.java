package model.state;

import context.TaskContext;
import model.Argument;

public class Resolved extends State {

    public Resolved(TaskContext task) {
        super(task);
    }

    private Resolved(State state, TaskContext taskContext) {
        super(state, taskContext);
    }

    public void up(Argument argument) {
        task.changeState(new Testing(task));
    }

    public void down(Argument argument) {
        task.changeState(new InProgess(task));
    }

    @Override
    public State copy(TaskContext taskContext) {
        return new Resolved(this, taskContext);
    }

}
