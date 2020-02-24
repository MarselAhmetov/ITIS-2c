package service.parser;

public interface Parser {
    <T> T decodeObject(String string, Class<T> c);
    String parseToString(Object object);
}
