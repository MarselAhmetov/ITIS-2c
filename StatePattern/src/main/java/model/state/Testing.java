package model.state;

import context.TaskContext;
import model.Argument;

public class Testing extends State {

    public Testing(TaskContext task) {
        super(task);
    }

    private Testing(State state,TaskContext taskContext) {
        super(state, taskContext);
    }

    public void up(Argument argument) {
        task.changeState(new Closed(task));
    }

    public void down(Argument argument) {
        task.changeState(new Assigned(task));
    }

    @Override
    public State copy(TaskContext taskContext) {
        return new Testing(this, taskContext);
    }

}
