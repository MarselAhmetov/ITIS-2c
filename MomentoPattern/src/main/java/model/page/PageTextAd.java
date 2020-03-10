package model.page;

import lombok.*;
import model.ads.TextAd;
import model.Internet;

import java.util.HashMap;
import java.util.LinkedList;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PageTextAd extends Page {
    String url;
    String content;
    HashMap<String, Page> links;
    Integer currentPosition = 0;
    HashMap<Integer, TextAd> ads;

    @Override
    public Page goToAd() {
        return Internet.getPage(ads.get(currentPosition).getAdUrl());
    }

    @Override
    public String toString() {
        return "\n" + url + " {" + "\n" +
                "  Content='" + content + "'\n" +
                "  Position='" + currentPosition + "'\n" +
                "  Links=" + new LinkedList<>(links.keySet()) +
                "  Ads=" + new LinkedList<>(ads.keySet()) +
                "\n}" + "\n";
    }
}
