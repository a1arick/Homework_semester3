package com.spbsu.a1arick.homework2.task1;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class NetworkTest {


    @Test
    public void add() {
        Network network = new Network();
        network.add(1, "Windows");
        network.add(2,"Linux");
        network.add(3,"IOS");
        network.add(4,"IOs");
        assertEquals(3, network.size());
    }

    @Test
    public void connect() {
        Computer computer1 = new Computer(1,OperationSystem.WINDOWS);
        Computer computer2 = new Computer(2,OperationSystem.IOS);
        Computer computer3 = new Computer(3,OperationSystem.ANDROID);
        computer1.connect(computer2);
        computer2.connect(computer3);
        Set<Computer> connectedComputers1 = computer1.getConnectedComputers();
        Set<Computer> connectedComputers2 = computer2.getConnectedComputers();
        Set<Computer> connectedComputers3 = computer3.getConnectedComputers();
        assertEquals(1,connectedComputers1.size());
        assertEquals(2,connectedComputers2.size());
        assertEquals(1,connectedComputers3.size());

    }

    @Test
    public void infect1() {
        Network network = new Network();
        network.add(1, "Windows");
        network.add(2, "Linux");
        network.add(3, "IOS");
        network.add(4, "Android");
        network.connect(1,2);
        network.connect(3,2);
        network.connect(1,3);
        network.connect(1,4);
        network.connect(2,4);
        network.connect(3,4);
        network.infect(1, new Virus("Trojan", 0.05));
        assertEquals(4, network.getHealthySize());
        assertEquals(0, network.getInfectSize());
    }

    @Test
    public void infect2() {
        Network network = new Network();
        network.add(1, "Windows");
        network.add(2, "Linux");
        network.add(3, "IOS");
        network.add(4, "Android");
        network.connect(1,2);
        network.connect(3,2);
        network.connect(1,3);
        network.connect(1,4);
        network.connect(2,4);
        network.connect(3,4);
        network.infect(1, new Virus("Trojan", 0.98));
        assertEquals(0, network.getHealthySize());
        assertEquals(4, network.getInfectSize());
    }

    @Test
    public void infect3() {
        Network network = new Network();
        network.add(1, "Windows");
        network.add(2, "Linux");
        network.add(3, "IOS");
        network.add(4, "Android");
        network.connect(1,2);
        network.connect(3,2);
        network.connect(1,3);
        network.connect(1,4);
        network.connect(2,4);
        network.connect(3,4);
        network.infect(1, new Virus("Trojan", 0.5));
        assertEquals(2, network.getHealthySize());
        assertEquals(2, network.getInfectSize());
    }

    @Test
    public void infect4() {
        Network network = new Network();
        network.add(1, "Windows");
        network.add(2, "Linux");
        network.add(3, "IOS");
        network.add(4, "Android");
        network.connect(1,2);
        network.connect(3,2);
        network.connect(1,3);
        network.connect(1,4);
        network.connect(2,4);
        network.connect(3,4);
        network.infect(1, new Virus("Trojan", 0.6));
        assertEquals(1, network.getHealthySize());
        assertEquals(3, network.getInfectSize());
    }
    
    @Test
    public void infect5() {
        Network network = new Network();
        Computer a = new Computer(1, OperationSystem.WINDOWS);
        Computer b = new Computer(2, OperationSystem.LINUX);
        Computer c = new Computer(3, OperationSystem.IOS);
        Computer d = new Computer(4, OperationSystem.ANDROID);
        network.add(a);
        assertEquals(1, network.size());
        network.add(b);
        assertEquals(2, network.size());
        network.add(c);
        assertEquals(3, network.size());
        network.add(d);
        assertEquals(4, network.size());
        network.connect(a.getId(), b.getId());
        network.connect(b.getId(), c.getId());
        assertEquals(1, a.getConnectedComputers().size());
        assertEquals(2, b.getConnectedComputers().size());
        assertEquals(1, c.getConnectedComputers().size());
        network.connect(a.getId(), c.getId());
        assertEquals(2, a.getConnectedComputers().size());
        assertEquals(2, c.getConnectedComputers().size());
        network.connect(a.getId(), d.getId());
        assertEquals(3, a.getConnectedComputers().size());
        assertEquals(1, d.getConnectedComputers().size());
        network.connect(b.getId(), d.getId());
        assertEquals(3, b.getConnectedComputers().size());
        assertEquals(2, d.getConnectedComputers().size());
        network.connect(c.getId(), d.getId());
        assertEquals(3, c.getConnectedComputers().size());
        assertEquals(3, d.getConnectedComputers().size());
        network.infect(1, new Virus("Trojan", 0.6));
        assertEquals(1, network.getHealthySize());
        assertEquals(3, network.getInfectSize());
        assertTrue(a.isInfected());
        assertFalse(b.isInfected());
        assertTrue(c.isInfected());
        assertTrue(d.isInfected());
    }
}
