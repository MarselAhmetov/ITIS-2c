package model.page;

import lombok.*;
import model.ads.VideoAd;
import context.Context;
import model.Internet;

import java.util.HashMap;
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
            for (Map.Entry<Integer, VideoAd> ad : ads.entrySet()) {
                if (ad.getKey() < position && !ad.getValue().isWatched()) {
                    Context.getBrowser().goToAd(ad.getValue().getAdUrl());
                }
            }
            return false;
        } else {
            currentPosition = position;
            return true;
        }
    }

    @Override
    public Page goToAd() {
        return Internet.getPage(ads.get(currentPosition).getAdUrl());
    }
}
