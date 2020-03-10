package model.page;

import lombok.*;

import java.util.HashMap;
import java.util.LinkedList;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PageCommon extends Page {
    String url;
    String content;
    HashMap<String, Page> links;
    Integer currentPosition = 0;


    @Override
    public Page goToAd() {
        return null;
    }

    @Override
    public String toString() {
        return "\n" + url + " {" + "\n" +
                "  Content='" + content + "'\n" +
                "  Position='" + currentPosition + "'\n" +
                "  Links=" + new LinkedList<>(links.keySet()) +
                "\n}" + "\n";
    }

}
