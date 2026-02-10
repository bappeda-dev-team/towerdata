package cc.kertaskerja.towerdata.kegiatan.domain.exception;

public class KegiatanNotFoundException extends RuntimeException {
    public KegiatanNotFoundException(String kodeKegiatan) {
        super("Kegiatan dengan kodeKegiatan " + kodeKegiatan + " tidak ditemukan.");
    }
}
