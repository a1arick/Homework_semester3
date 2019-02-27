package com.a1arick.spbsu.homework4.try2.network;

import com.a1arick.spbsu.homework4.try2.server.model.ServerItem;

import java.util.List;

public class Update {
    private List<ServerItem> serverItems;

    public Update() {
    }

    public Update(List<ServerItem> serverItems) {
        this.serverItems = serverItems;
    }

    public List<ServerItem> getServerItems() {
        return serverItems;
    }

    public void setServerItems(List<ServerItem> serverItems) {
        this.serverItems = serverItems;
    }
}
