package behavior;

public interface Behavior {

    /**
     * Update the behavior data
     * @param set
     */
    void acknowledge(FactSet set);

    /**
     * Ask the behavior to update it's belief, ...
     */
    void update();

    /**
     * Ask the behavioral agent to decide what to do
     * @return
     */
    DecisionSet decide();
}
