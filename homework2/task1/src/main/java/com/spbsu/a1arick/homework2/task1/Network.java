package com.spbsu.a1arick.homework2.task1;

import java.util.*;

/**
 * Class realizing network
 */
public class Network {

    private final Map<Integer, Computer> computers = new HashMap<>();
    private int networkSize = 0;
    private int healthySize = 0;
    private int infectSize = 0;
    /**
     * add computer to network
     * @param id id computer
     * @param os operation system computer
     */
    public void add(int id, String os) {
        OperationSystem system = stringToOS(os);
        if(system == null) return;
        computers.put(id, new Computer(id, system));
        networkSize++;
        healthySize++;
    }

    /**
     * connects computers on the network
     * @param id1 id first computer
     * @param id2 id second computer
     */
    public void connect(int id1, int id2) {
        Computer computer1 = computers.get(id1);
        Computer computer2 = computers.get(id2);
        if(computer1 != null && computer2 != null) {
            computer1.connect(computer2);
        }
    }

    /**
     * Infects the computer with a virus
     * @param id id computer
     * @param virus name of virus
     */
    public void infect(int id, Virus virus) {
        Computer computer = computers.get(id);
        if(computer != null) {
            dfs(computer, virus, new HashSet<>());
        }
    }

    /**
     * Network traversal
     * @param computer computer
     * @param virus vitus
     * @param used to not bypass the computer several times
     */
    public void dfs(Computer computer, Virus virus, Set<Computer> used) {
        if (!used.contains(computer)) {
            if (computer.infect(virus)) {
                System.out.println("Computer " + computer.getId() + " : infected");
                StateNetwork();
                for (Computer connectedComputer : computer.getConnectedComputers()) {
                    if(connectedComputer.canInfect(virus) && !connectedComputer.isInfected()) {
                        dfs(connectedComputer, virus, used);
                    }
                }
            } else {
                System.out.println("Computer " + computer.getId() + " : wasn't infected");
            }
        }

    }

    /**
     * State computers in network
     */
    private void StateNetwork() {
        healthySize = 0;
        infectSize = 0;
        ArrayList<Integer> healthy = new ArrayList<>();
        ArrayList<Integer> infect = new ArrayList<>();
        for (Map.Entry<Integer, Computer> computerEntry : computers.entrySet()) {

        }

        for (Map.Entry<Integer, Computer> computerEntry : computers.entrySet()) {
            if(computerEntry.getValue().isInfected()) infect.add(computerEntry.getKey());
            else healthy.add(computerEntry.getKey());
        }
        System.out.print("Healthy: ");
        for (Integer id : healthy) {
            healthySize++;
            System.out.print(id.toString() + ' ');
        }
        System.out.println();
        System.out.print("Infect: ");
        for (Integer id : infect) {
            infectSize++;
            System.out.print(id.toString() + ' ');
        }
        System.out.println();
    }

    /**
     * Return operatoin system
     * @param os operation system string
     * @return operation system
     */
    public static OperationSystem stringToOS(String os) {
        if(os.equals("Windows")) return OperationSystem.WINDOWS;
        if(os.equals("IOS")) return OperationSystem.IOS;
        if(os.equals("Android")) return OperationSystem.ANDROID;
        if(os.equals("Linux")) return OperationSystem.LINUX;
        if(os.equals("Mac_OS")) return OperationSystem.MAC_OS;
        return null;
    }

    /**
     * return size computer in network
     * @return size computer in network
     */
    public int size(){
        return networkSize;
    }

    /**
     * return size healthy computer in network
     * @return size healthy computer in network
     */
    public int getHealthySize(){
        return healthySize;
    }

    /**
     * return size infect computer in network
     * @return size infect computer in network
     */
    public int getInfectSize(){
        return infectSize;
    }
}
