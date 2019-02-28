package com.a1arick.spbsu.homework4.try2.network;

import com.a1arick.spbsu.homework4.try2.server.model.*;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

import java.util.ArrayList;
import java.util.TreeSet;

public class Network {

    static public final int serverPort = 54555;
    // 54555
    static public void register (EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(Update.class);
        kryo.register(AddTank.class);
        kryo.register(Move.class);
        kryo.register(ArrayList.class);
        kryo.register(Shot.class);
        kryo.register(Tank.class);
        kryo.register(Card.class);
        kryo.register(Point.class);
        kryo.register(TreeSet.class);
        kryo.register(CannonMove.class);
        kryo.register(ShotType.class);
        kryo.register(MakeShot.class);
    }
}
