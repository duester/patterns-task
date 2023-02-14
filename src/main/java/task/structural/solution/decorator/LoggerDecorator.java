package task.structural.solution.decorator;

import task.structural.dependency.fourier.FourierTransformer;
import task.structural.solution.adapter.MusicFileAdapter;

import java.util.Map;
import java.util.logging.Logger;

public class LoggerDecorator extends MusicFileAdapterDecorator {
    private static final String PREFIX = "LoggerDecorator";
    private static final Logger LOGGER = Logger.getLogger("");

    public LoggerDecorator(MusicFileAdapter adapter) {
        super(adapter);
    }

    @Override
    public double getCurrentTime() {
        double time = adapter.getCurrentTime();
        LOGGER.info(PREFIX + ":: getCurrentTime = " + time);
        return time;
    }

    @Override
    public void setCurrentTime(double currentTime) {
        LOGGER.info(PREFIX + ":: setCurrentTime " + currentTime);
        adapter.setCurrentTime(currentTime);
    }

    @Override
    public double getTotalTime() {
        double time = adapter.getTotalTime();
        LOGGER.info(PREFIX + ":: getTotalTime = " + time);
        return time;
    }

    @Override
    public int getTimesPlayed() {
        int times = adapter.getTimesPlayed();
        LOGGER.info(PREFIX + ":: getTimesPlayed = " + times);
        return times;
    }

    @Override
    public void play() {
        LOGGER.info(PREFIX + ":: play");
        adapter.play();
    }

    @Override
    public Map<Double, Integer> getFrequencyMap(FourierTransformer transformer) {
        Map<Double, Integer> map = adapter.getFrequencyMap(transformer);
        LOGGER.info(PREFIX + ":: getFrequencyMap = " + map);
        return map;
    }

    @Override
    public Map<String, String> getTagMap() {
        Map<String, String> map = adapter.getTagMap();
        LOGGER.info(PREFIX + ":: getTagMap = " + map);
        return map;
    }
}
