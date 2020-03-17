package springContextContainer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springContextContainer.config.ApplicationContextConfig;
import springContextContainer.services.CommandReaderImpl;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        CommandReaderImpl commandReader = (CommandReaderImpl) context.getBean("commandReaderImpl");
        while (true) {
            commandReader.read();
        }
    }
}
