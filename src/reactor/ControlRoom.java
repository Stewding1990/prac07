package reactor;

public class ControlRoom extends RadiationMonitor {
    private double warningThreshold;
    private double ObservedRadiation;

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
        RadiationSensor sensor = (RadiationSensor) subject;
        ObservedRadiation = sensor.getRadiation();
        if(ObservedRadiation >= warningThreshold)
        {
            System.out.println(generateReport());
        }

    }

    /**
     * Generates a report based on the current observation.
     */
    @Override
    public String generateReport() {
        String report = String.format("%s :: WARNING :: %.4f :: %s", now(), ObservedRadiation, getLocation());
        return report;
    }
}