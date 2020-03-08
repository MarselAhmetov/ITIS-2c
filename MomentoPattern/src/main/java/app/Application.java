package app;

import interpreter.InterpreterImpl;
import model.Browser;
import context.Context;
import model.Internet;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Internet.connection();
        Context.setBrowser(new Browser());
        InterpreterImpl interpreter = new InterpreterImpl(Context.getBrowser());
        Scanner scanner = new Scanner(System.in);
        while (true) {
            interpreter.handle(scanner.nextLine());
        }
    }
}
