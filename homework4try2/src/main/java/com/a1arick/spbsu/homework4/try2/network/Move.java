package com.a1arick.spbsu.homework4.try2.network;

public class Move {
    private int clientId;
    private boolean isRight;

    public Move() {
    }

    public Move(int clientId, boolean isRight) {
        this.clientId = clientId;
        this.isRight = isRight;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public int getClientId() {
        return clientId;
    }

    public boolean isRight() {
        return isRight;
    }
}
