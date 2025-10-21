package cc.kertaskerja.towerdata.opd.domain.exception;

public class OpdNotFoundException extends RuntimeException {
    public OpdNotFoundException(Long id) {
        super("Opd dengan id " + id + " tidak ditemukan.");
    }
}
