package service;

import model.Page;

import java.util.Map;

public class Internet {

    static Map<String, Page> pages;

    public static void connection() {
        PageReader pageReader = new PageReader("src/pages.txt");
        pages = pageReader.readPages();
    }


    public static Page getPage(String url) {
        Page page;
        if ((page = pages.get(url)) != null) {
            return page;
        } else {
            System.out.println("Error 404");
            return null;
        }
    }

}
