package reactor;

import java.util.Random;

public class RadiationSensor extends Subject {
    private String location;
    private double radiation;
    private Random random;

    /**
     * Constructs a RadiationSensor object
     *
     * @param location An arbitrary location.
     * @param seed     A seed for the random number generator used to simulate radiation
     *                  readings.
     */
    public RadiationSensor(String location, int seed) {
        this.location = location;
        random = new Random(seed);
    }

    /**
     * Gets the location
     *
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the radiation.
     *
     * @return radiation
     */
    public double getRadiation() {
        return radiation;
    }

    /**
     * Updates radiation, changes the state to true, and notifies all observers of
     * the change.
     */
    public void readRadiation() {
        radiation = random.nextDouble() * 10;
//        setChanged();
        notifyObservers();
    }

}