package model.page;

import lombok.*;
import model.ads.TextAd;
import model.Internet;

import java.util.HashMap;

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
}
