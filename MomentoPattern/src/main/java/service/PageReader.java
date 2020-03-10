package service;

import factory.AdFactory;
import factory.TextAdFactory;
import factory.VideoAdFactory;
import model.ads.TextAd;
import model.ads.VideoAd;
import model.page.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PageReader {
    private Scanner scanner;
    private AdFactory factory;


    public PageReader(String path) {
        try {
            scanner = new Scanner(new FileReader(new File(path)));
        } catch (FileNotFoundException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
    }

    public Map<String, Page> readPages() {
        Integer countOfPage = scanner.nextInt();
        Map<String, Page> pageMap = new HashMap<>();
        for (int i = 0; i < countOfPage; i++) {
            String[] param = scanner.nextLine().split("=");
            switch (param[0]) {
                case "Common":
                    pageMap.put(param[1], PageCommon.builder()
                    .url(param[1])
                    .content(param[2])
                    .links(new HashMap<>())
                    .build());
                    break;
                case "TextAd":
                    pageMap.put(param[1], PageTextAd.builder()
                            .url(param[1])
                            .content(param[2])
                            .links(new HashMap<>())
                            .ads(new HashMap<>())
                            .build());
                    break;
                case "VideoAd":
                    pageMap.put(param[1], PageVideoAd.builder()
                            .url(param[1])
                            .content(param[2])
                            .links(new HashMap<>())
                            .ads(new HashMap<>())
                            .build());
                    break;
            }
        }
        for (int i = 0; i < countOfPage && scanner.hasNext(); i++) {
            String[] param = scanner.nextLine().split(":");
            if (param.length > 1) {
                String[] links = param[1].split(" ");
                for (String link : links) {
                    pageMap.get(param[0]).getLinks().put(link, pageMap.get(link));
                }
            }
        }
        try {
            scanner = new Scanner(new FileReader(new File("src/ads.txt")));
        } catch (FileNotFoundException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
        while (scanner.hasNext()) {
            String[] param = scanner.nextLine().split(":");
            String[] ads = param[2].split(" ");
            switch (param[0]) {
                case "VideoAd":
                    factory = VideoAdFactory.getInstance();
                    for (String ad : ads) {
                        String[] adPage = ad.split("=");
                        ((PageVideoAd) pageMap.get(param[1])).getAds().put(Integer.parseInt(adPage[1]), (VideoAd) factory.createAd(adPage[0]));
                    }
                    break;
                case "TextAd":
                    factory = TextAdFactory.getInstance();
                    for (String ad : ads) {
                        String[] adPage = ad.split("=");
                        ((PageTextAd) pageMap.get(param[1])).getAds().put(Integer.parseInt(adPage[1]), (TextAd) factory.createAd(adPage[0]));
                    }
                    break;
            }
        }
        return pageMap;
    }
}
