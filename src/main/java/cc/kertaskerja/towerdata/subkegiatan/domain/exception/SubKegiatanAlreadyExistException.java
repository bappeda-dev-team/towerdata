package cc.kertaskerja.towerdata.subkegiatan.domain.exception;

public class SubKegiatanAlreadyExistException extends RuntimeException {
    public SubKegiatanAlreadyExistException(Long id) {
        super("Sub Kegiatan dengan Id " + id + " sudah ada.");
    }
}
