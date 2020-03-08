package model;

import lombok.*;

import java.util.HashMap;
import java.util.LinkedList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Page {
    String url;
    String content;
    HashMap<String, Page> links;
    Integer currentPosition = 0;

    public boolean changePosition(Integer position) {
        if (position > 99 || position < 0) {
            return false;
        } else {
            currentPosition = position;
            return true;
        }
    }

    public abstract Page goToAd();

    @Override
    public String toString() {
        return "\n  Page{" + "\n" +
                "       url='" + url + "'\n" +
                "       content='" + content + "'\n" +
                "       content='" + currentPosition + "'\n" +
                "       links=" + new LinkedList<>(links.keySet()) +
                '}' + "\n\n";
    }
}