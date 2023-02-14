package task.structural.solution.decorator;

public class InvalidCurrentTimeException extends RuntimeException {
    public InvalidCurrentTimeException(double currentTime, double totalTime) {
        super("Неверное значение текущего времени: " + currentTime + " не входит в диапазон (0, " + totalTime + ")");
    }
}
