package cc.kertaskerja.towerdata.opd.domain.exception;

public class OpdAlreadyExistException extends RuntimeException {
    public OpdAlreadyExistException(String kodeOpd) {
        super("Opd dengan kodeOpd " + kodeOpd + " sudah ada.");
    }
}
