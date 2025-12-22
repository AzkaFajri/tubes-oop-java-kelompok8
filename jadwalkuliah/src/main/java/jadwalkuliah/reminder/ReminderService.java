/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jadwalkuliah.reminder;

import java.time.*;
import java.util.Timer;
import java.util.TimerTask;

public class ReminderService {

    private Timer timer = new Timer();

    public void pasangReminder(String namaMK, LocalTime jamMulai, int menitSebelum) {

        LocalDateTime sekarang = LocalDateTime.now();
        LocalDateTime waktuKuliah = LocalDateTime.of(LocalDate.now(), jamMulai);
        LocalDateTime waktuReminder = waktuKuliah.minusMinutes(menitSebelum);

        long delay = Duration.between(sekarang, waktuReminder).toMillis();
        if (delay <= 0) return;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new ReminderTask(namaMK, jamMulai).run();
            }
        }, delay);
    }
}
