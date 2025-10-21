package cc.kertaskerja.towerdata.rekening.domain.exception;

public class RekeningNotFoundException extends RuntimeException {
    public RekeningNotFoundException(Long id) {
        super("Rekening dengan id " + id + " tidak ditemukan.");
    }
}
