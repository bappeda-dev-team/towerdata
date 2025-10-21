package cc.kertaskerja.towerdata.pegawai.domain.exception;

public class PegawaiAlreadyExistException extends RuntimeException {
    public PegawaiAlreadyExistException(Long id) {
        super("Pegawai dengan id " + id + " sudah ada.");
    }
}
