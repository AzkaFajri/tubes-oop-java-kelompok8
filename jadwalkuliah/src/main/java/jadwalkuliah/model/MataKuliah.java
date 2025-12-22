package jadwalkuliah.model;

import java.time.*;

public class MataKuliah {
    private String nama;
    private String hari;
    private LocalTime mulai;
    private LocalTime selesai;

    public MataKuliah(String nama, String hari, String par, LocalTime mulai, LocalTime selesai, String text2, int par1) {
        this.nama = nama;
        this.hari = hari;
        this.mulai = mulai;
        this.selesai = selesai;
    }

    public String getNama() { return nama; }
    public String getHari() { return hari; }
    public LocalTime getMulai() { return mulai; }
    public LocalTime getSelesai() { return selesai; }

    @Override
    public String toString() {
        return nama + " (" + mulai + " - " + selesai + ")";
    }
}