package task.structural.solution.audiolib;

import task.structural.dependency.fourier.FourierTransformer;
import task.structural.exception.MissingTagException;
import task.structural.solution.adapter.MusicFileAdapter;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;

/**
 * Разрабатываемая универсальная библиотека с новыми методами
 * для работы с различными звуковыми форматами
 */
public class AudioLib {

    // 1. вычислить оставшееся время проигрывания (сек)

    public double getRemainingTime(MusicFileAdapter adapter) {
        double currentTime = adapter.getCurrentTime();
        double totalTime = adapter.getTotalTime();
        return totalTime - currentTime;
    }

    // 2. проиграть композицию несколько раз подряд

    public void playInLoop(MusicFileAdapter adapter, int times) {
        for (int i = 0; i < times; i++) {
            adapter.setCurrentTime(0.0);
            adapter.play();
        }
    }

    // 3. статистика по частотному анализу

    public FrequencyAnalysisStatistics analyzeFrequencies(MusicFileAdapter adapter, FourierTransformer transformer) {
        Map<Double, Integer> frequencies = adapter.getFrequencyMap(transformer);
        if (frequencies.isEmpty()) {
            return null;
        }
        double minimal = frequencies.keySet().stream().min(Comparator.comparing(Function.identity())).get();
        double maximal = frequencies.keySet().stream().max(Comparator.comparing(Function.identity())).get();
        double rarest = frequencies.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
        double mostFrequent = frequencies.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        return new FrequencyAnalysisStatistics(minimal, maximal, rarest, mostFrequent);
    }

    public record FrequencyAnalysisStatistics(double minimal, double maximal, double rarest, double mostFrequent) {
    }

    // 4. получить название композиции

    public String getTitle(MusicFileAdapter adapter) {
        return getTagValue(adapter, "title");
    }

    public String getTagValue(MusicFileAdapter adapter, String tagCode) {
        Map<String, String> tags = adapter.getTagMap();
        String tagValue = tags.get(tagCode);
        if (tagValue == null) {
            throw new MissingTagException("title");
        }
        return tagValue;
    }
}
