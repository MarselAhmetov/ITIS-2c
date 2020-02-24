package models;

import models.gameModels.PlayerModel;

public class JsonMessageSetPlayer {
    private String header;
    private PlayerModel payload;

    public JsonMessageSetPlayer() {
    }

    public JsonMessageSetPlayer(String header, PlayerModel payload) {
        this.header = header;
        this.payload = payload;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public PlayerModel getPayload() {
        return payload;
    }

    public void setPayload(PlayerModel payload) {
        this.payload = payload;
    }
}
