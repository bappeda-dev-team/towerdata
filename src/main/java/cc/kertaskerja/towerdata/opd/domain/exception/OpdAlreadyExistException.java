package cc.kertaskerja.towerdata.opd.domain.exception;

public class OpdAlreadyExistException extends RuntimeException {
    public OpdAlreadyExistException(Long id) {
        super("Opd dengan id " + id + " sudah ada.");
    }
}
