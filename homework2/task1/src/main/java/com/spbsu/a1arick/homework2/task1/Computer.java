package com.spbsu.a1arick.homework2.task1;

import java.util.*;

/**
 * Class realizing computer in network
 */

public class Computer {
    private final int id;
    private final OperationSystem operationSystem;
    private final Set<Computer> connectedComputers = new HashSet<>();
    private boolean isInfected = false;

    /**
     * Creates new clear computer without any connections
     *
     * @param id id computer
     * @param operationSystem operation system computer
     */
    public Computer(int id, OperationSystem operationSystem) {
        this.id = id;
        this.operationSystem = operationSystem;
    }

    /**
     * Tries to infect a computer with a virus
     * @param virus virus that wants to infect a computer
     * @return true if the virus infected this computer
     */
    public boolean infect(Virus virus) {
        return isInfected = canInfect(virus);
    }

    /**
     * Returns true if computer is infected
     * @return  true if computer is infected
     */
    public boolean isInfected() {
        return isInfected;
    }

    /**
     * Get id computer
     * @return id computer
     */
    public int getId(){
        return id;
    }

    /**
     * Сonnects computers
     * @param computer computer which connect
     */
    public void connect(Computer computer) {
        connectedComputers.add(computer);
        computer.connectedComputers.add(this);
    }

    /**
     * Return all computers which are connected to this
     * @return all computers which are connected to this
     */
    public Set<Computer> getConnectedComputers() {
        return connectedComputers;
    }

    /**
     *
     * @param virus
     * @return true if virus can infect this
     */
    public boolean canInfect(Virus virus) {
        return virus.getPower() > operationSystem.getResistance();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return id == computer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
