package cc.kertaskerja.towerdata.rekening.domain.exception;

public class RekeningAlreadyExistException extends RuntimeException {
    public RekeningAlreadyExistException(String kodeRekening) {
        super("Rekening dengan kode " + kodeRekening + " sudah ada.");
    }
}
