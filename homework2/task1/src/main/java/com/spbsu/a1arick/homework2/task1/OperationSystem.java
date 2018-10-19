package com.spbsu.a1arick.homework2.task1;

/**
 * Listed operation systems
 */
public enum OperationSystem {
    WINDOWS(.1), LINUX(.7), MAC_OS(.6), ANDROID(.5), IOS(.42);
    /**
     * resistance operation system
     */
    private final double resistance;

    /**
     * Create operation system
     * @param infectionProbability resistance operation system
     */
    OperationSystem(double infectionProbability) {
        this.resistance = infectionProbability;
    }

    /**
     * Get resistance
     * @return resistance
     */
    public double getResistance() {
        return resistance;
    }
}
