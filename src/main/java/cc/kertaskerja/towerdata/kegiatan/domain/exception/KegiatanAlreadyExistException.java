package cc.kertaskerja.towerdata.kegiatan.domain.exception;

public class KegiatanAlreadyExistException extends RuntimeException {
    public KegiatanAlreadyExistException(Long id) {
        super("Kegiatan dengan id " + id + " sudah ada.");
    }
}
