package service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.History;
import model.Page;
import model.momento.Momento;

import java.util.HashMap;

@Data
@AllArgsConstructor
@Builder
public class Browser {

    private Page currentPage;
    private History history;

    public Browser() {
        currentPage = PageCommon.builder()
                .url("home.html")
                .links(new HashMap<>())
                .content("Home page")
                .build();
        this.history = new History();
    }

    public void goBack() {
        history.back(createMomento());
        System.out.println(showContent());
    }

    public void goForward() {
        history.forward(createMomento());
        System.out.println(showContent());
    }

    public String showContent() {
        return currentPage.toString();
    }

    public Momento createMomento() {
        return new MomentoBrowser(this, currentPage, history);
    }

    public void link(String url) {
        Page newPage;
        if ((newPage = currentPage.getLinks().get(url)) != null) {
            history.link(createMomento());
            currentPage = newPage;
        }
        System.out.println(showContent());
    }

    public void goTo(String url) {
        Page page;
        if ((page = Internet.getPage(url)) != null) {
            currentPage = page;
            history.clearHistory();
            System.out.println(showContent());
        }
    }

    public void goToAd() {
        Page page;
        if ((page = currentPage.goToAd()) != null) {
            currentPage = page;
            history.link(createMomento());
            System.out.println(showContent());
        } else {
            System.out.println("No any ads on this position");
        }
    }

    public void goToAd(String url) {
        currentPage = Internet.getPage(url);
        history.link(createMomento());
        System.out.println(showContent());
    }

    public void goToPosition(Integer position) {
        if (!currentPage.changePosition(position)) {
            System.out.println("Wrong position");
        }
        System.out.println(showContent());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class MomentoBrowser implements Momento {

        private Browser browser;

        private Page momentoStage;
        private History history;


        public void recover() {
            browser.currentPage = momentoStage;
        }
    }
}