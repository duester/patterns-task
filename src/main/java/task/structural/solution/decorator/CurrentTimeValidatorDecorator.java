package task.structural.solution.decorator;

import task.structural.solution.adapter.MusicFileAdapter;

public class CurrentTimeValidatorDecorator extends MusicFileAdapterDecorator {
    public CurrentTimeValidatorDecorator(MusicFileAdapter adapter) {
        super(adapter);
    }

    @Override
    public void setCurrentTime(double currentTime) {
        double totalTime = adapter.getTotalTime();
        if (currentTime < 0 || currentTime > totalTime) {
            throw new InvalidCurrentTimeException(currentTime, totalTime);
        }
        adapter.setCurrentTime(currentTime);
    }
}
