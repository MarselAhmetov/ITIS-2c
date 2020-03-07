package model;

import lombok.*;

import java.util.HashMap;
import java.util.LinkedList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Page {
    private String url;
    private String content;
    private HashMap<String, Page> links;

    @Override
    public String toString() {
        return "\n  Page{" + "\n" +
                "       url='" + url + "'\n" +
                "       content='" + content + "'\n" +
                "       links=" + new LinkedList<>(links.keySet()) +
                '}' + "\n\n";
    }
}
