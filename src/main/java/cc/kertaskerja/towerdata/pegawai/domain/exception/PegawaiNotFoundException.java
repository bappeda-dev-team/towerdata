package cc.kertaskerja.towerdata.pegawai.domain.exception;

public class PegawaiNotFoundException extends RuntimeException {
    public PegawaiNotFoundException(String nipPegawai) {
        super("Pegawai dengan nip " + nipPegawai + " tidak ditemukan.");
    }
}
