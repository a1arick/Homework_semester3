package com.a1arick.spbsu.homework4.try2.network;

/**
 * Move cannon for Network calls
 */
public class CannonMove {
    private int clientId;
    private boolean isRight;

    public CannonMove() {
    }

    public CannonMove(int clientId, boolean isRight) {
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
