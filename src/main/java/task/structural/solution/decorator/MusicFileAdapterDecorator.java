package task.structural.solution.decorator;

import task.structural.dependency.fourier.FourierTransformer;
import task.structural.solution.adapter.MusicFileAdapter;

import java.util.Map;

public class MusicFileAdapterDecorator implements MusicFileAdapter {
    protected final MusicFileAdapter adapter;

    public MusicFileAdapterDecorator(MusicFileAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public double getCurrentTime() {
        return adapter.getCurrentTime();
    }

    @Override
    public void setCurrentTime(double currentTime) {
        adapter.setCurrentTime(currentTime);
    }

    @Override
    public double getTotalTime() {
        return adapter.getTotalTime();
    }

    @Override
    public int getTimesPlayed() {
        return adapter.getTimesPlayed();
    }

    @Override
    public void play() {
        adapter.play();
    }

    @Override
    public Map<Double, Integer> getFrequencyMap(FourierTransformer transformer) {
        return adapter.getFrequencyMap(transformer);
    }

    @Override
    public Map<String, String> getTagMap() {
        return adapter.getTagMap();
    }
}
