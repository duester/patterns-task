package task.structural.solution;

import task.structural.dependency.mp3.MP3File;
import task.structural.solution.adapter.MP3FileAdapter;
import task.structural.solution.adapter.MusicFileAdapter;

public class Main {
    public static void main(String[] args) {
        MP3File mp3 = new MP3File("test.mp3");
        MusicFileAdapter adapter = new MP3FileAdapter(mp3);
        // MusicFileAdapter adapter = new LoggerDecorator(new MP3FileAdapter(mp3));
        // MusicFileAdapter adapter = new CurrentTimeValidatorDecorator(new LoggerDecorator(new MP3FileAdapter(mp3)));
        adapter.setCurrentTime(-1.0);
        adapter.play();
    }
}
