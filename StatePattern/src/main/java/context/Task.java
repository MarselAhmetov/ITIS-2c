package context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Argument;
import model.state.State;
import model.state.Draft;
import service.generator.TaskIdGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task implements TaskContext {
    private Integer id;
    private Integer developerId;
    private Integer testerId;
    private String text;
    private String error;
    private State state;

    public Task(Integer developerId, Integer testerId, String text, String error) {
        this.id = TaskIdGenerator.getId();
        this.developerId = developerId;
        this.testerId = testerId;
        this.text = text;
        this.error = error;
        this.state = new Draft(this);
    }

    private Task(Task task) {
        this.id = TaskIdGenerator.getId();
        this.developerId = task.developerId;
        this.testerId = task.testerId;
        this.text = task.text;
        this.error = task.error;
        this.state = task.state.copy(this);
    }

    public void up(Argument argument) {
        state.up(argument);
    }

    public void down(Argument argument) {
        state.down(argument);
    }

    public void changeState(State state) {
        this.state = state;
    }

    @Override
    public TaskContext copy() {
        return new Task(this);
    }
}
