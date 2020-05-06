package reactor;

import java.util.ArrayList;

public class ResearchCentre extends RadiationMonitor {
    private ArrayList<Double> observations;

    /**
     * Constructs a ResearchCentre object, which observes reactor radiation readings
     * and constantly prints a report with the current moving average of the
     * recorded observations.
     *
     * @param location An arbitrary location.
     */
    public ResearchCentre(String location) {
        super(location);
        observations = new ArrayList<Double>();
    }

    /**
     * Updates the monitor with a new observation and prints a report.
     */
    public void update(Subject subject) {
        observation = ((RadiationSensor) subject).getRadiation();
        observations.add(observation);
        System.out.println(generateReport());
    }

    /**
     * Generates a report of the current moving average, updated by a new
     * observation. The moving average is calculated by summing all observations
     * made so far, and dividing by the quantity of observations so far.
     */
    public String generateReport() {
        double movingAverage = movingAverage();
        String str = String.format("%s :: moving average :: %.4f :: %s", now(), movingAverage, this.getLocation());
        return str;
    }

    private double movingAverage() {
        double sum = 0;
        for (double observation : observations) {
            sum += observation;
        }
        if (observations.size() > 0) {
            double average = sum / observations.size();
            return average;
        } else {
            return 0;
        }
    }

}