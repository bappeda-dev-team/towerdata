package cc.kertaskerja.towerdata.subkegiatan.domain.exception;

public class SubKegiatanNotFoundException extends RuntimeException {
    public SubKegiatanNotFoundException(Long id) {
        super("Sub kegiatan dengan Id " + id + " tidak ditemukan.");
    }

    public SubKegiatanNotFoundException(String kodeSubKegiatan) {
        super("Sub kegiatan dengan kode " + kodeSubKegiatan + " tidak ditemukan.");
    }
}
