import context.proxy.TaskProxy;
import interpreter.Interpreter;
import interpreter.InterpreterImpl;
import stateMachine.StateMachine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StateMachine stateMachine = new StateMachine();
        stateMachine.addTask(new TaskProxy(null, null, null, null));
        stateMachine.addTask(new TaskProxy(null, null, null, null));
        stateMachine.addTask(new TaskProxy(null, null, null, null));
        TaskProxy taskProxy = new TaskProxy(1, 1, null, null);
        stateMachine.addTask(taskProxy.copy());
        stateMachine.addTask(taskProxy.copy());
        TaskProxy taskProxy1 = (TaskProxy) stateMachine.getTaskById(4).copy();
        System.out.println(taskProxy1.getId());
        Interpreter interpreter = new InterpreterImpl(stateMachine);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String string = scanner.nextLine();
            interpreter.handle(string);
        }
    }
}
