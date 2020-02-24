package models;

import javafx.geometry.Point2D;
import javafx.scene.Node;

public class GameObject {

    private Node view;
    private Point2D velocity = new Point2D(0, 0);

    private boolean alive = true;

    public GameObject(Node view) {
        this.view = view;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public void rotate(Integer value) {
        view.setRotate(value);
    }
    public Point2D getVelocity() {
        return velocity;
    }

    public Node getView() {
        return view;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isDead() {
        return !alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public double getRotate() {
        return view.getRotate();
    }

    public void moveUp() {
        view.setTranslateY(view.getTranslateY() - 30);
    }

    public void moveRight() {
        view.setTranslateX(view.getTranslateX() + 30);
    }


    public void moveDown() {
        view.setTranslateY(view.getTranslateY() + 30);
    }


    public void moveLeft() {
        view.setTranslateX(view.getTranslateX() - 30);
    }


    public boolean isColliding(GameObject other) {
        return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
    }
}