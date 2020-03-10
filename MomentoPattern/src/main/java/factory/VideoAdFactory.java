package factory;

import model.ads.Ad;
import model.ads.VideoAd;

public class VideoAdFactory implements AdFactory {
    private static VideoAdFactory videoAdFactory;

    public static VideoAdFactory getInstance() {
        if (videoAdFactory == null) {
            videoAdFactory = new VideoAdFactory();
        }
        return videoAdFactory;
    }

    @Override
    public Ad createAd(String url) {
        return new VideoAd(false, url);
    }
}
