package pl.glownia.maciej.springbootdailywins.dailyWin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity(name = "dailywins")// mapping bean into database table
public class DailyWin {

    @Id
    @GeneratedValue
    private int id;
    private String username;
    @Size(min = 10, message = "Be more creative. Enter at least 10 characters.")
    private String description;
    private LocalDate targetDate;
    private boolean done;

    public DailyWin() {
    } // needed here - without it status=500, No default constructor for entity occur

    public DailyWin(int id, String username, String description, LocalDate targetDate, boolean done) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Victory{" +
                "id=" + id +
                ", victoryName='" + username + '\'' +
                ", description='" + description + '\'' +
                ", targetDate=" + targetDate +
                ", done=" + done +
                '}';
    }
}
