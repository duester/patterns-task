package task.structural.solution;

import org.junit.jupiter.api.Test;
import task.structural.dependency.fourier.FourierTransformer;
import task.structural.dependency.midi.MIDIFile;
import task.structural.dependency.midi.Note;
import task.structural.dependency.midi.Sequencer;
import task.structural.dependency.midi.Track;
import task.structural.dependency.mp3.MP3File;
import task.structural.dependency.mp3.Tag;
import task.structural.dependency.wav.WavFile;
import task.structural.solution.adapter.MIDIFileAdapter;
import task.structural.solution.adapter.MP3FileAdapter;
import task.structural.solution.adapter.MusicFileAdapter;
import task.structural.solution.adapter.WavFileAdapter;
import task.structural.solution.audiolib.AudioLib;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AudioLibTest {

    @Test
    void getRemainingTime() {
        MusicFileAdapter adapter = new MP3FileAdapter(createMP3());
        // длина несжатых данных = 6
        // => длина сжатых данных = 3
        // => общая продолжительность = 3 / 1 = 3 с
        adapter.setCurrentTime(2.0);
        double remainingTime = new task.structural.solution.audiolib.AudioLib().getRemainingTime(adapter);
        assertEquals(1.0, remainingTime);
    }

    @Test
    void playInLoop_Wav() {
        MusicFileAdapter adapter = new WavFileAdapter(createWav());
        new AudioLib().playInLoop(adapter, 2);
        assertEquals(2, adapter.getTimesPlayed());
    }

    @Test
    void analyzeFrequencies_MIDI() {
        Sequencer sequencer = new Sequencer();
        MusicFileAdapter adapter = new MIDIFileAdapter(createMIDI(), sequencer);
        AudioLib.FrequencyAnalysisStatistics stat = new AudioLib().analyzeFrequencies(adapter, createFourierTransformer());
        assertEquals(100.0, stat.minimal());
        assertEquals(440.0, stat.maximal());
        assertEquals(440.0, stat.rarest());
        assertEquals(100.0, stat.mostFrequent());
    }

    @Test
    void getTitle_MP3() {
        MusicFileAdapter adapter = new MP3FileAdapter(createMP3());
        String title = new AudioLib().getTitle(adapter);
        assertEquals("Test", title);
    }

    // ------

    private MP3File createMP3() {
        byte[] data = new byte[]{0, 0, 0, 0, 0, 0};
        List<Tag> tags = List.of(
                new Tag("title", "Test")
        );
        return new MP3File(data, 1, tags);
    }

    private WavFile createWav() {
        byte[] data = new byte[]{0, 0, 0, 0, 0, 0};
        Map<String, String> tags = new HashMap<>() {{
            put("title", "Test");
        }};
        return new WavFile(data, 1, tags);
    }

    private MIDIFile createMIDI() {
        List<Track> tracks = List.of(
                new Track(List.of(
                        new Note(500, 100.0, 500),
                        new Note(1000, 440.0, 1000),
                        new Note(2000, 100.0, 500)
                ))
        );
        return new MIDIFile(tracks, "Test", "Test");
    }

    private FourierTransformer createFourierTransformer() {
        return new FourierTransformer() {
            @Override
            public Map<Double, Integer> getFrequencies(byte[] data) {
                return new HashMap<>() {{
                    put(100.0, 2);
                    put(440.0, 1);
                    put(800.0, 3);
                }};
            }
        };
    }
}
