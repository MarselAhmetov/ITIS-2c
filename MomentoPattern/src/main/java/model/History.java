package model;

import model.momento.Momento;

import java.util.Stack;

public class History {
    private Stack<Momento> backStack;
    private Stack<Momento> forwardStack;

    public History() {
        this.backStack = new Stack<>();
        this.forwardStack = new Stack<>();
    }

    public void back(Momento momento) {
        if (!backStack.isEmpty()) {
            forwardStack.add(momento);
            backStack.pop().recover();
        }
    }

    public void forward(Momento momento) {
        if (!forwardStack.isEmpty()) {
            backStack.add(momento);
            forwardStack.pop().recover();
        }
    }

    public void link(Momento momento) {
        backStack.add(momento);
        forwardStack.removeAllElements();
    }

    public void clearHistory() {
        backStack.removeAllElements();
    }
}
