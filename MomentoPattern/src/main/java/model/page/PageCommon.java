package model.page;

import lombok.*;

import java.util.HashMap;

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

}
