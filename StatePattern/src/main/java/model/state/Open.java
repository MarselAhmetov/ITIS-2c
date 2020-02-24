package model.state;

import context.TaskContext;
import model.Argument;

public class Open extends State {

    public Open(TaskContext task) {
        super(task);
    }

    private Open(State state, TaskContext taskContext) {
        super(state, taskContext);
    }

    public void up(Argument argument) {
        State state = new Assigned(task);
        state.developerId = argument.getDeveloperId();
        task.changeState(state);
    }

    public void down(Argument argument) {
        task.changeState(new Backlog(task));
    }

    @Override
    public State copy(TaskContext taskContext) {
        return new Open(this, taskContext);
    }
}

