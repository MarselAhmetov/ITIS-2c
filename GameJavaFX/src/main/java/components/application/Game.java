package components.application;

import components.protocol.ClientSocket;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import models.GameObject;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import models.JsonMessage;
import models.actions.Move;
import components.service.JsonMessageService;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Game extends Application {
    private Pane root;
    private HashMap<Integer, GameObject> players = new HashMap<>();
    private Integer playerId = 1;
    private JsonMessageService jsonMessageService = new JsonMessageService();
    private ClientSocket clientSocket;

    public void makeAction(String message) {
        JsonMessage jsonMessage = jsonMessageService.decodeJson(message);
        switch (jsonMessage.getHeader()) {
            case "Move":
                LinkedHashMap move = (LinkedHashMap) jsonMessage.getPayload();
                Integer id = (Integer) move.get("playerId");
                    switch ((String) move.get("direction")) {
                        case "UP":
                            players.get(id).moveUp();
                            break;
                        case "DOWN":
                            players.get(id).moveDown();
                            break;
                        case "RIGHT":
                            players.get(id).moveRight();
                            break;
                        case "LEFT":
                            players.get(id).moveLeft();
                            break;
                    }
                break;
            case "SetPixel":
                break;
            case "SetPlayer":
                LinkedHashMap setPlayer = (LinkedHashMap) jsonMessage.getPayload();
                GameObject p = new Player(Color.ORANGE);
                System.out.println(setPlayer.get("id"));
                players.put((Integer) setPlayer.get("id"), p);
                addGameObject(p, 300, 300);
                break;
            case "PlayerData":
                LinkedHashMap playerData = (LinkedHashMap) jsonMessage.getPayload();
                Color color = Color.ORANGE;
                switch ((String) playerData.get("color")) {
                    case "RED":
                        color = Color.RED;
                        break;
                    case "GREEN":
                        color = Color.GREEN;
                        break;
                    case "BLUE":
                        color = Color.BLUE;
                        break;
                }
                GameObject player = new Player(color);
                playerId = (Integer) playerData.get("id");
                players.put(playerId, player);
                addGameObject(player, 300, 300);
                break;
        }
    }


    private Parent createContent() {
        root = new Pane();
        root.setPrefSize(600, 600);
        return root;
    }

    private void addGameObject(GameObject object, double x, double y) {
        Platform.runLater(() -> {
            object.getView().setTranslateX(x);
            object.getView().setTranslateY(y);

            root.getChildren().add(object.getView());
        });

    }

    private static class Player extends GameObject {
        Player(Color color) {
            super(new Rectangle(30, 30, color));
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        clientSocket = new ClientSocket();
        clientSocket.setGame(this);
        stage.setScene(new Scene(createContent()));
        clientSocket.startConnection("127.0.0.1", 4321);

        stage.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                clientSocket.sendMessage(jsonMessageService.createJson("Move", new Move(playerId, "LEFT")));
            } else if (e.getCode() == KeyCode.RIGHT) {
                clientSocket.sendMessage(jsonMessageService.createJson("Move", new Move(playerId, "RIGHT")));
            } else if (e.getCode() == KeyCode.UP) {
                clientSocket.sendMessage(jsonMessageService.createJson("Move", new Move(playerId, "UP")));
            } else if (e.getCode() == KeyCode.DOWN) {
                clientSocket.sendMessage(jsonMessageService.createJson("Move", new Move(playerId, "DOWN")));
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}