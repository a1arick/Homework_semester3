package com.spbsu.a1arick.homework2.task1;

/**
 * Class realizing virus in network
 */
public class Virus {
    private final String name;
    private final double power;

    /**
     * Create virus
     * @param name name virus
     * @param power power virus
     */
    public Virus(String name, double power) {
        this.name = name;
        this.power = power;
        if (this.power > 1) {
            throw new IllegalArgumentException("Virus power can'tbe more than 1");
        }
    }

    /**
     * Get name virus
     * @return name virus
     */
    public String getName() {
        return name;
    }

    /**
     * Get power virus
     * @return power virus
     */
    public double getPower() {
        return power;
    }
}
