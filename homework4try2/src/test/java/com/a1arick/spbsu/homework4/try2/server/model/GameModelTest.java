package com.a1arick.spbsu.homework4.try2.server.model;

import com.a1arick.spbsu.homework4.try2.server.ServerItem;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GameModelTest {

    private TestGameModel model;

    @Before
    public void setUp() throws Exception {

        model = new TestGameModel();
    }

    @Test
    public void test1() {
        model.addTank(0);
        model.addTank(1);
        // повернуть пушку на 90 градусов
        model.cannonMove(0, false);

        model.makeShot(0, ShotType.BULLET);

        model.setTime(1);



        model.setTime(123);
        List<ServerItem> items = model.update();

        ServerItem serverItem = items.stream()
                .filter(i -> i.getClientId() == 1 && i.isDead())
                .findAny()
                .orElse(null);
        if (serverItem == null) {
            fail();
        }
        assertTrue(serverItem.isDead());

    }
}