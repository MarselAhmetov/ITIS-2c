package app;

public class Application {
    public static void main(String[] args) {
        PageReader pageReader = new PageReader("src/pages.txt");
        System.out.println(pageReader.readPages());
    }
}
