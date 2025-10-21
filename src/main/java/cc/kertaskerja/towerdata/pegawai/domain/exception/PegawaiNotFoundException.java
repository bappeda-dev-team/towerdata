package cc.kertaskerja.towerdata.pegawai.domain.exception;

public class PegawaiNotFoundException extends RuntimeException {
    public PegawaiNotFoundException(Long id) {
        super("Pegawai dengan id " + id + " tidak ditemukan.");
    }
}
