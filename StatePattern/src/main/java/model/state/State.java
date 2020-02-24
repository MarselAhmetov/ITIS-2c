package model.state;

import context.TaskContext;
import model.Argument;

public abstract class State {
    protected TaskContext task;
    protected Integer developerId;
    protected Integer testerId;
    protected String text;
    protected String error;

    public State(TaskContext task) {
        this.task = task;
    }

    public State(State state, TaskContext task) {
        this.task = task;
        this.developerId = state.developerId;
        this.testerId = state.testerId;
        this.text = state.text;
        this.error = state.error;
    }

    public abstract void up(Argument argument);

    public abstract void down(Argument argument);

    public abstract State copy(TaskContext taskContext);

}



