package task.structural.solution.adapter;

import task.structural.dependency.fourier.FourierTransformer;
import task.structural.dependency.wav.WavFile;

import java.util.Map;

public class WavFileAdapter implements MusicFileAdapter {
    private final WavFile file;

    public WavFileAdapter(WavFile file) {
        this.file = file;
    }

    @Override
    public double getCurrentTime() {
        return file.getCurrentTime();
    }

    @Override
    public void setCurrentTime(double currentTime) {
        file.setCurrentTime(currentTime);
    }

    @Override
    public double getTotalTime() {
        return file.getTotalTime();
    }

    @Override
    public int getTimesPlayed() {
        return file.getTimesPlayed();
    }

    @Override
    public void play() {
        file.playWav();
    }

    @Override
    public Map<Double, Integer> getFrequencyMap(FourierTransformer transformer) {
        byte[] decodedData = file.getRawData();
        return transformer.getFrequencies(decodedData);
    }

    @Override
    public Map<String, String> getTagMap() {
        return file.getTags();
    }
}
