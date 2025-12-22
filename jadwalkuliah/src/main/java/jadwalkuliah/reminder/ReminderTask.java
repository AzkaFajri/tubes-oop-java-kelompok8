/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jadwalkuliah.reminder;

import javax.swing.*;
import java.time.LocalTime;

public class ReminderTask implements Runnable {

    private String namaMK;
    private LocalTime jamMulai;

    public ReminderTask(String namaMK, LocalTime jamMulai) {
        this.namaMK = namaMK;
        this.jamMulai = jamMulai;
    }

    @Override
    public void run() {
        JOptionPane.showMessageDialog(
            null,
            "⏰ Reminder Jadwal Kuliah\n\n" +
            "Mata Kuliah : " + namaMK +
            "\nJam Mulai   : " + jamMulai,
            "Pengingat Jadwal",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
