package com.a1arick.spbsu.homework4.try2.network;

import com.a1arick.spbsu.homework4.try2.server.model.AbstractGameModel;
import com.a1arick.spbsu.homework4.try2.server.model.GameModel;

public class AddTank {
    private int clientId;

    public AddTank() {
    }

    public AddTank(int clientId) {
        this.clientId = clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }
}
