package cc.kertaskerja.towerdata.rekening.domain.exception;

public class RekeningAlreadyExistException extends RuntimeException {
    public RekeningAlreadyExistException(Long id) {
        super("Rekening dengan id " + id + " sudah ada.");
    }
}
