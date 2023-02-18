package pl.glownia.maciej.springbootdailywins.dailyWin;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
class DailyWinService {

    private static List<DailyWin> dailyWins = new ArrayList<>();

    private static int dailyWinsCount = 0;

    static {
        dailyWins.add(new DailyWin(++dailyWinsCount, "maciek", "Learn Spring Boot 2 x 45 mins", LocalDate.now(), false));
        dailyWins.add(new DailyWin(++dailyWinsCount, "maciek", "Learn one difference between Java and Kotlin", LocalDate.now(), false));
        dailyWins.add(new DailyWin(++dailyWinsCount, "maciek", "Read a book - 10 pages", LocalDate.now(), false));
        dailyWins.add(new DailyWin(++dailyWinsCount, "maciek", "Do cardio training for 30 mins", LocalDate.now(), false));
        dailyWins.add(new DailyWin(++dailyWinsCount, "maciek", "Drink 3l of water", LocalDate.now(), false));
    }

    public List<DailyWin> findByUsername(String username) {
        Predicate<? super DailyWin> predicate =
                dailyWin -> dailyWin.getUsername().equalsIgnoreCase(username); // predicate check every object from the list if matches
        return dailyWins.stream().filter(predicate).toList();
    }

    public void addDailyWin(String username, String description, LocalDate targetDate, boolean done) {
        DailyWin dailyWin = new DailyWin(++dailyWinsCount, username, description, targetDate, done);
        dailyWins.add(dailyWin);
    }

    public void deleteById(int id) {
        Predicate<? super DailyWin> predicate
                = dailyWin -> dailyWin.getId() == id;
        dailyWins.removeIf(predicate);
    }

    public DailyWin findById(int id) {
        Predicate<? super DailyWin> predicate = dailyWin -> dailyWin.getId() == id;
        return dailyWins.stream().filter(predicate).findFirst().get();
    }

    public void updateDailyWin(DailyWin dailyWin) {
        deleteById(dailyWin.getId());
        dailyWins.add(dailyWin);
    }
}
