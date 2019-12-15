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

    public JsonMessage decodeJson(String jsonMessage) {
        try {
            return decodeJson0(jsonMessage);
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

    private JsonMessage decodeJson0(String jsonMessage) throws JsonProcessingException {
        return mapper.readValue(jsonMessage, JsonMessage.class);
    }
}
