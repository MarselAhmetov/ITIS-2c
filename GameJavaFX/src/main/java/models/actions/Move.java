package models.actions;

public class Move {
    private Integer playerId;
    private String direction;

    public Move(Integer playerId, String direction) {
        this.playerId = playerId;
        this.direction = direction;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
