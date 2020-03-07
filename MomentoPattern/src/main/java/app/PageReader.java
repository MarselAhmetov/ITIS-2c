package app;

import model.Page;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PageReader {
    private File file;
    private FileReader fileReader;
    private Scanner scanner;


    public PageReader() {
        file = new File("src/pages.txt");
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
        scanner = new Scanner(fileReader);
    }

    public Map<String, Page> readPages() {
        Map<String, Page> pageMap = new HashMap<>();
        String[] pages = scanner.nextLine().split("  ");
        for (String page : pages) {
            String[] param = page.split("=");
            pageMap.put(param[0], Page.builder()
            .url(param[0])
            .content(param[1])
                    .links(new HashMap<>())
            .build());
        }
        for (int i = 0; i < pages.length && scanner.hasNext(); i++) {
            String[] param = scanner.nextLine().split(":");
            if (param.length > 1) {
                String[] links = param[1].split(" ");
                for (String link : links) {
                    pageMap.get(param[0]).getLinks().put(link, pageMap.get(link));
                }
            }
        }
        return pageMap;
    }
}
