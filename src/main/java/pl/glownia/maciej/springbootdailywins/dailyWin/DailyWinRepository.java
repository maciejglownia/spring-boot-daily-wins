package pl.glownia.maciej.springbootdailywins.dailyWin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyWinRepository extends JpaRepository<DailyWin, Integer> {

    public List<DailyWin> findByUsername(String username);
}
