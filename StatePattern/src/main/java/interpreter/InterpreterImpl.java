package interpreter;

import context.TaskContext;
import lombok.AllArgsConstructor;
import stateMachine.StateMachine;

@AllArgsConstructor
public class InterpreterImpl implements Interpreter {
    private StateMachine stateMachine;


    @Override
    public void handle(String line) {
        String[] command = line.split(" ");
        switch (command[0]) {
            case "copy":
                System.out.println(copy(command[1]));
                break;
            default:
                System.out.println("No such command");
                break;
        }
    }

    private Integer copy(String id) {
        TaskContext taskContext = stateMachine.getTaskById(Integer.parseInt(id));
        if (taskContext != null) {
            TaskContext newTask = taskContext.copy();
            stateMachine.addTask(newTask);
            return newTask.getId();
        } else {
            return -1;
        }
    }
}
