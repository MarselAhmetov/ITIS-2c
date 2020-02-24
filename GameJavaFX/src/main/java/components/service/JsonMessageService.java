package components.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.JsonMessage;

public class JsonMessageService {

    private ObjectMapper mapper = new ObjectMapper();

    public String createJson(String header, Object payload) {
        try {
            return createJson0(header, payload);
        } catch (JsonProcessingException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
    }

    public <T> T decodeJson(String jsonMessage, Class<T> c) {
        try {
            return decodeJson0(jsonMessage, c);
        } catch (JsonProcessingException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
    }


    private String createJson0(String header, Object payload) throws JsonProcessingException {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setHeader(header);
        jsonMessage.setPayload(payload);

        return mapper.writeValueAsString(jsonMessage);
    }

    private <T> T decodeJson0(String jsonMessage, Class<T> c) throws JsonProcessingException {
        return mapper.readValue(jsonMessage, c);
    }

}
