package interpreter;

import model.Browser;

public class InterpreterImpl implements Interpreter {
    Browser browser;

    public InterpreterImpl(Browser browser) {
        this.browser = browser;
    }

    public void handle(String line) {
        String[] param = line.split(" ");
        switch (param[0]) {
            case "gotoPosition":
                browser.goToPosition(Integer.parseInt(param[1]));
                break;
            case "goto":
                browser.goToPage(param[1]);
                break;
            case "link":
                browser.link(param[1]);
                break;
            case "gotoAd":
                browser.goToAd();
                break;
            case "show":
                System.out.println(browser.showContent());
                break;
            case "back":
                browser.goBack();
                break;
            case "forward":
                browser.goForward();
                break;

        }
    }
}
