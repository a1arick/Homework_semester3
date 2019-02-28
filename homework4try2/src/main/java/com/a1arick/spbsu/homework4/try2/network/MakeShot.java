package com.a1arick.spbsu.homework4.try2.network;

import com.a1arick.spbsu.homework4.try2.server.model.ShotType;

public class MakeShot {
    private int clientId;
    private ShotType type;

    public MakeShot() {
    }

    public MakeShot(int clientId, ShotType type) {
        this.clientId = clientId;
        this.type = type;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public ShotType getType() {
        return type;
    }

    public void setType(ShotType type) {
        this.type = type;
    }
}
