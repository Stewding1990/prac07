package reactor;

public class ControlRoom extends RadiationMonitor {
    double warningThreshold;

    /**
     * Constructs a ControlRoom object, which observes reactor radiation readings
     * and prints reports if the radiation exceeds a threshold.
     *
     * @param location         An arbitrary location.
     * @param warningThreshold The radiation threshold for when reports should be printed.
     */
    public ControlRoom(String location, double warningThreshold) {
        super(location);
        this.warningThreshold = warningThreshold;
    }

    /**
     * Updates the monitor with a new observation and prints a report if and only if
     * the observation is equal to or greater than the warning threshold.
     */
    public void update(Subject subject) {
        observation = ((RadiationSensor) subject).getRadiation();
        if (this.observation >= this.warningThreshold) {
            System.out.println(generateReport());
        }
    }

    /**
     * Generates a report based on the current observation.
     */
    @Override
    public String generateReport() {
        double obs = observation * 10000;
        obs = Math.round(obs);
        obs = obs / 10000;

        return now() + " :: WARNING :: " + obs + " :: " + this.getLocation();
    }
}