package jadwalkuliah.manager;

import java.util.*;

import jadwalkuliah.exception.KonflikWaktuException;
import jadwalkuliah.model.MataKuliah;

public class JadwalManager {

    private List<MataKuliah> daftar = new ArrayList<>();

    public void tambah(MataKuliah mk) throws KonflikWaktuException {
        for (MataKuliah m : daftar) {
            if (m.getHari() == mk.getHari() &&
               !(mk.getSelesai().isBefore(m.getMulai()) ||
                 mk.getMulai().isAfter(m.getSelesai()))) {
                throw new KonflikWaktuException(
                    "Bentrok dengan " + m.getNama()
                );
            }
        }
        daftar.add(mk);
    }

    public List<MataKuliah> getSemua() {
        return daftar;
    }} 
