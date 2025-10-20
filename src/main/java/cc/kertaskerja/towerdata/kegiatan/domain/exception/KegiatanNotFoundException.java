package cc.kertaskerja.towerdata.kegiatan.domain.exception;

public class KegiatanNotFoundException extends RuntimeException {
    public KegiatanNotFoundException(Long id) {
        super("Kegiatan dengan id " + id + " tidak ditemukan.");
    }
}
