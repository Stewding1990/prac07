package reactor;


public class ResearchCentre extends RadiationMonitor {
    private String location;
    private double totalObservedRadiation;
    private int observationCount;

    /**
     * Constructs a ResearchCentre object, which observes reactor radiation readings
     * and constantly prints a report with the current moving average of the
     * recorded observations.
     *
     * @param location An arbitrary location.
     */
    public ResearchCentre(String location) {
        super(location);
        observationCount = 0;
        totalObservedRadiation = 0.0;
    }

    /**
     * Updates the monitor with a new observation and prints a report.
     */
    public void update(Subject subject) {
        RadiationSensor sensor = (RadiationSensor) subject;
         double ObservedRadiation = sensor.getRadiation();
         observationCount++;
         totalObservedRadiation += ObservedRadiation;
        System.out.println(generateReport());

    }

    /**
     * Generates a report of the current moving average, updated by a new
     * observation. The moving average is calculated by summing all observations
     * made so far, and dividing by the quantity of observations so far.
     */
    public String generateReport() {
        double average = totalObservedRadiation / observationCount;
        String report = String.format("%s :: moving average :: %.4f :: %s", now(), average, getLocation());
        return report;
    }

}