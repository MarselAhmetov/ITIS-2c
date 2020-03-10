package model.page;

import lombok.*;

import java.util.HashMap;
import java.util.LinkedList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Page {
    private String url;
    private String content;
    private HashMap<String, Page> links;
    private Integer currentPosition = 0;

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
        return "\n" + url + " {" + "\n" +
                "  Content='" + content + "'\n" +
                "  Position='" + currentPosition + "'\n" +
                "  Links=" + new LinkedList<>(links.keySet()) +
                "\n}" + "\n";
    }
}
