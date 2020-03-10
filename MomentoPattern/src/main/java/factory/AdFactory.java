package factory;

import model.ads.Ad;

public interface AdFactory {
    Ad createAd(String url);
}
