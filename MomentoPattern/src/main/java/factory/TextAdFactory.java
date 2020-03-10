package factory;

import model.ads.Ad;
import model.ads.TextAd;

public class TextAdFactory implements AdFactory {
    private static TextAdFactory textAdFactory;

    public static TextAdFactory getInstance() {
        if (textAdFactory == null) {
            textAdFactory = new TextAdFactory();
        }
        return textAdFactory;
    }

    @Override
    public Ad createAd(String url) {
        return new TextAd(url);
    }
}
