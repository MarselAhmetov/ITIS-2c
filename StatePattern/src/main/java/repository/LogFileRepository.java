package repository;

import service.parser.JsonParser;
import model.Log;
import service.parser.Parser;

import java.io.*;
import java.util.Scanner;

public class LogFileRepository implements Repository<Log> {
    private FileWriter writer;
    private Scanner reader;
    private Parser parser;

    public LogFileRepository(File file) {
        try {
            this.writer = new FileWriter(file, true);
            this.reader = new Scanner(new FileReader(file));
            this.parser = new JsonParser();
        } catch (IOException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
    }

    public void add(Log log) {
        try {
            writer.write(parser.parseToString(log) + "\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
    }

    public Log read() {
        return null;
    }

    public void update(Log log) {

    }

    public void delete(Log log) {

    }
}
