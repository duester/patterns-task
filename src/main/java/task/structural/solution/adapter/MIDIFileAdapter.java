package task.structural.solution.adapter;

import task.structural.dependency.fourier.FourierTransformer;
import task.structural.dependency.midi.MIDIFile;
import task.structural.dependency.midi.Note;
import task.structural.dependency.midi.Sequencer;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MIDIFileAdapter implements MusicFileAdapter {
    private final MIDIFile file;
    private final Sequencer sequencer;

    public MIDIFileAdapter(MIDIFile file, Sequencer sequencer) {
        this.file = file;
        this.sequencer = sequencer;
    }

    @Override
    public double getCurrentTime() {
        return file.getCurrentTimeMS() / 1000.0;
    }

    @Override
    public void setCurrentTime(double currentTime) {
        file.setCurrentTimeMS((long) (currentTime * 1000));
    }

    @Override
    public double getTotalTime() {
        return file.getLength() / 1000.0;
    }

    @Override
    public int getTimesPlayed() {
        return file.getTimesPlayed();
    }

    @Override
    public void play() {
        file.playMIDI(sequencer);
    }

    @Override
    public Map<Double, Integer> getFrequencyMap(FourierTransformer transformer) {
        return file.getTracks().stream()
                .flatMap(t -> t.getNotes().stream())
                .collect(Collectors.toMap(Note::getPitch, b -> 1, (amount, i) -> amount + 1));
    }

    @Override
    public Map<String, String> getTagMap() {
        return new HashMap<>() {{
            put("title", file.getTitle());
            put("interpreter", file.getInterpreter());
        }};
    }
}
