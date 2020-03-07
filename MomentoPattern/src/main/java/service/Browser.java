package service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.History;
import model.Page;
import model.momento.Momento;
import model.momento.MomentoBrowser;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Browser {

    private Page currentStage;
    private History history;

    public void goBack() {
        history.back(createMomento());
    }

    public void goForward() {
        history.forward(createMomento());
    }

    public String showContent() {
        return currentStage.getContent();
    }

    public Momento createMomento() {
        return new MomentoBrowser(this, currentStage, history);
    }

    public void link(String url) {
        Page newPage;
        if ((newPage = currentStage.getLinks().get(url)) != null) {
            history.link(createMomento());
            currentStage = newPage;
        }
    }

    public void goTo(String url) {

    }

}
