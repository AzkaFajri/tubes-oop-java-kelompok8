/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jadwalkuliah.controller;

import jadwalkuliah.manager.JadwalManager;
import jadwalkuliah.model.MataKuliah;
import jadwalkuliah.reminder.ReminderService;
import jadwalkuliah.exception.KonflikWaktuException;

public class JadwalController {

    private JadwalManager manager;
    private ReminderService reminderService;

    public JadwalController(JadwalManager manager) {
        this.manager = manager;
        this.reminderService = new ReminderService();
    }

    public void tambahJadwal(MataKuliah mk)
            throws KonflikWaktuException {
        manager.tambah(mk);
    }

    public void aktifkanReminder(String namaMK, java.time.LocalTime jamMulai, int menitSebelum) {
        reminderService.pasangReminder(namaMK, jamMulai, menitSebelum);
    }
}
