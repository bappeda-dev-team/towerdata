package cc.kertaskerja.towerdata.pemda.domain.exception;

public class PemdaNotFoundException extends RuntimeException {
    public PemdaNotFoundException(String kodePemda) {
        super("Pemda dengan kode " + kodePemda + " tidak ditemukan.");
    }
}
