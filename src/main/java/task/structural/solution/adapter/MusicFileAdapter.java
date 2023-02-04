package task.structural.solution.adapter;

import task.structural.dependency.fourier.FourierTransformer;

import java.util.Map;

public interface MusicFileAdapter {
    double getCurrentTime();

    void setCurrentTime(double currentTime);

    double getTotalTime();

    int getTimesPlayed();

    void play();

    Map<Double, Integer> getFrequencyMap(FourierTransformer transformer);

    Map<String, String> getTagMap();
}
