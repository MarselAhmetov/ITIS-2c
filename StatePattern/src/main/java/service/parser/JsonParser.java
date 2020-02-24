package service.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser implements Parser {
    private ObjectMapper mapper;

    public JsonParser() {
        this.mapper = new ObjectMapper();
    }

    public <T> T decodeObject(String json, Class<T> c) {
        try {
            return decodeObject0(json, c);
        } catch (JsonProcessingException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
    }

    private <T> T decodeObject0(String json, Class<T> c) throws JsonProcessingException {
        return mapper.readValue(json, c);
    }

    public String parseToString(Object object) {
        try {
            return parseToString0(object);
        } catch (JsonProcessingException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
    }

    private String parseToString0(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
