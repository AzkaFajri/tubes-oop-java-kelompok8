/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jadwalkuliah.reminder;

import java.util.TimerTask;
import javax.swing.*;

public class ReminderTask extends TimerTask {
    private String pesan;

    public ReminderTask(String pesan) {
        this.pesan = pesan;
    }

    @Override
    public void run() {
        JOptionPane.showMessageDialog(
            null,
            pesan,
            "Reminder Jadwal Kuliah",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
