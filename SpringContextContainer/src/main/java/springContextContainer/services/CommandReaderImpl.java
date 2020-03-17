package springContextContainer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springContextContainer.model.NameEntity;

import java.util.List;
import java.util.Scanner;

@Component
public class CommandReaderImpl {
    Scanner scanner;

    @Autowired
    NameContainer nameContainer;

    public CommandReaderImpl() {
        this.scanner = new Scanner(System.in);
    }

    public void read() {
        String line = scanner.nextLine();
        String[] param = line.split(" ");
        switch (param[0]) {
            case "add":
                add(param[1]);
                break;
            case "out":
                System.out.println(out());
                break;
            case "stop":
                stop();
                break;
        }
    }

    private void add(String name) {
        nameContainer.add(NameEntity.builder()
        .name(name)
        .build());
    }

    private List<NameEntity> out() {
        return nameContainer.out();
    }

    private void stop() {
        System.exit(0);
    }
}
