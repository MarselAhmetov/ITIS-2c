package model.page;

import lombok.*;
import model.ads.VideoAd;
import context.Context;
import model.Internet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageVideoAd extends Page {
    String url;
    String content;
    HashMap<String, Page> links;
    Integer currentPosition = 0;
    HashMap<Integer, VideoAd> ads;

    @Override
    public boolean changePosition(Integer position) {
        if (position > 99 || position < 0) {
            return false;
        } else {
            currentPosition = position;
            for (Map.Entry<Integer, VideoAd> ad : ads.entrySet()) {
                if (ad.getKey() < position && !ad.getValue().isWatched()) {
                    Context.getBrowser().goToAd(ad.getValue().getAdUrl());
                    ad.getValue().setWatched(true);
                    break;
                }
            }
            return true;
        }
    }

    @Override
    public Page goToAd() {
        if (ads.get(currentPosition) != null) {
            return Internet.getPage(ads.get(currentPosition).getAdUrl());
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "\n" + url + " {" + "\n" +
                "  Content='" + content + "'\n" +
                "  Position='" + currentPosition + "'\n" +
                "  Links=" + new LinkedList<>(links.keySet()) + "'\n" +
                "  Ads=" + new LinkedList<>(ads.keySet()) +
                "\n}" + "\n";
    }
}
