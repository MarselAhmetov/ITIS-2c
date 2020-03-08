package app;

import interpreter.InterpreterImpl;
import service.Browser;
import service.Context;
import service.Internet;
import service.PageReader;

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
