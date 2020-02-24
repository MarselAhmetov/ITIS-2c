package context;

import model.Argument;
import model.state.State;

public interface TaskContext {
    void up(Argument argument);
    void down(Argument argument);
    void changeState(State state);
    TaskContext copy();
    Integer getId();
}
