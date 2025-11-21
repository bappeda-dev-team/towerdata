package cc.kertaskerja.towerdata.rekening.domain.exception;

public class RekeningNotFoundException extends RuntimeException {
    public RekeningNotFoundException(String kodeRekening) {
        super("Rekening dengan kode " + kodeRekening + " tidak ditemukan.");
    }
}
