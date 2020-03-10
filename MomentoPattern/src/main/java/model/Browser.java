package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.page.Page;
import model.page.PageCommon;
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
        return new MomentoBrowser(this, currentPage);
    }

    public void link(String url) {
        Page newPage;
        if ((newPage = currentPage.getLinks().get(url)) != null) {
            history.link(createMomento());
            currentPage = newPage;
        }
        System.out.println(showContent());
    }

    public void goToPage(String url) {
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
            history.link(createMomento());
            currentPage = page;
            System.out.println(showContent());
        } else {
            System.out.println("No any ads on this position");
        }
    }

    public void goToAd(String url) {
        history.link(createMomento());
        currentPage = Internet.getPage(url);
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


        public void recover() {
            browser.currentPage = momentoStage;
        }
    }
}
