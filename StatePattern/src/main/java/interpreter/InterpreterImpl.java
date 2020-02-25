package interpreter;

import context.TaskContext;
import context.proxy.TaskProxy;
import lombok.AllArgsConstructor;
import model.Argument;
import stateMachine.StateMachine;

import java.util.jar.Attributes;

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
            case "up":
                up(command);
                break;
            case "down":
                down(command);
                break;
            case "new_task":
                newTask(command);
                break;
            default:
                System.out.println("No such command");
                break;
        }
    }

    private Integer copy(String id) {
        TaskContext taskContext = stateMachine.getTaskById(Integer.parseInt(id));
        if (taskContext != null) {
            TaskContext newTask = (TaskContext) taskContext.copy();
            stateMachine.addTask(newTask);
            return newTask.getId();
        } else {
            return -1;
        }
    }

    // up taskId developerId=1 testerId=2 errorText=text
    private void up(String[] command) {
        Argument argument = new Argument();
        for (int i = 2; i < command.length; i++) {
            String[] parameter = command[i].split("=");
            switch (parameter[0]) {
                case "developerId":
                    argument.setDeveloperId(Integer.parseInt(parameter[1]));
                    break;
                case "testerId":
                    argument.setTesterId(Integer.parseInt(parameter[1]));
                    break;
                case "error":
                    argument.setError(parameter[1]);
                    break;
                case "text":
                    argument.setText(parameter[1]);
                    break;
            }
        }
        stateMachine.getTaskById(Integer.parseInt(command[1])).up(argument);
    }

    private void down(String[] command) {
        Argument argument = new Argument();
        for (int i = 2; i < command.length; i++) {
            String[] parameter = command[i].split("=");
            switch (parameter[0]) {
                case "developerId":
                    argument.setDeveloperId(Integer.parseInt(parameter[1]));
                    break;
                case "testerId":
                    argument.setTesterId(Integer.parseInt(parameter[1]));
                    break;
                case "error":
                    argument.setError(parameter[1]);
                    break;
                case "text":
                    argument.setText(parameter[1]);
                    break;
            }
        }
        stateMachine.getTaskById(Integer.parseInt(command[1])).down(argument);
    }

    private void newTask(String[] command) {
        Argument argument = Argument.builder().text(command[1]).build();
        stateMachine.addTask(new TaskProxy(argument));
    }
}
