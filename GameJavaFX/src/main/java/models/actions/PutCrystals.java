package models.actions;

public class PutCrystals {
    private Integer score;
    private Integer playerId;

    public PutCrystals() {
    }

    public PutCrystals(Integer score, Integer playerId) {
        this.score = score;
        this.playerId = playerId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
}
