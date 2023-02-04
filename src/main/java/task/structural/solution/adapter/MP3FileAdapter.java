package task.structural.solution.adapter;

import task.structural.dependency.fourier.FourierTransformer;
import task.structural.dependency.mp3.MP3File;
import task.structural.dependency.mp3.Tag;

import java.util.Map;
import java.util.stream.Collectors;

public class MP3FileAdapter implements MusicFileAdapter {
    private final MP3File file;

    public MP3FileAdapter(MP3File file) {
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
        file.playMP3();
    }

    @Override
    public Map<Double, Integer> getFrequencyMap(FourierTransformer transformer) {
        byte[] decodedData = file.decodeData();
        return transformer.getFrequencies(decodedData);
    }

    @Override
    public Map<String, String> getTagMap() {
        return file.getTags().stream().collect(Collectors.toMap(Tag::getCode, Tag::getValue));
    }
}
