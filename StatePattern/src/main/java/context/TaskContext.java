package context;

import model.Argument;
import model.state.State;
import prototype.Prototype;

public interface TaskContext extends Prototype {
    void up(Argument argument);
    void down(Argument argument);
    void changeState(State state);
    Integer getId();
}
