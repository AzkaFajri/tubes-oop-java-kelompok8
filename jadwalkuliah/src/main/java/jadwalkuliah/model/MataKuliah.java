package jadwalkuliah.model;

import java.time.*;

public class MataKuliah {
    private String nama;
    private DayOfWeek hari;
    private LocalTime mulai;
    private LocalTime selesai;

    public MataKuliah(String nama, DayOfWeek hari, LocalTime mulai, LocalTime selesai) {
        this.nama = nama;
        this.hari = hari;
        this.mulai = mulai;
        this.selesai = selesai;
    }

    public String getNama() { return nama; }
    public DayOfWeek getHari() { return hari; }
    public LocalTime getMulai() { return mulai; }
    public LocalTime getSelesai() { return selesai; }

    @Override
    public String toString() {
        return nama + " (" + mulai + " - " + selesai + ")";
    }
}