/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jadwalkuliah.reminder;

import jadwalkuliah.model.MataKuliah;
import java.time.*;
import java.util.*;

public class ReminderService {
    private Timer timer = new Timer();

    public void pasangReminder(MataKuliah mk, int menitSebelum) {
        LocalDate tanggal = LocalDate.now();
        while (tanggal.getDayOfWeek() != mk.getHari()) {
            tanggal = tanggal.plusDays(1);
        }

        LocalDateTime waktuKuliah =
            LocalDateTime.of(tanggal, mk.getMulai());
        LocalDateTime waktuReminder =
            waktuKuliah.minusMinutes(menitSebelum);

        long delay =
            Duration.between(LocalDateTime.now(), waktuReminder).toMillis();

        if (delay > 0) {
            timer.schedule(
                new ReminderTask(
                    "Mata kuliah " + mk.getNama() +
                    " dimulai " + menitSebelum + " menit lagi"
                ),
                delay
            );
        }
    }
}
