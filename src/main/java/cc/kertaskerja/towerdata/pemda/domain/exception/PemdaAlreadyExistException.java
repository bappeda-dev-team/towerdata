package cc.kertaskerja.towerdata.pemda.domain.exception;

public class PemdaAlreadyExistException extends RuntimeException {
    public PemdaAlreadyExistException(String kodePemda) {
        super("Pemda dengan kodePemda " + kodePemda + " sudah ada.");
    }
}
