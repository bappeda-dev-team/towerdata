package cc.kertaskerja.towerdata.jabatan.domain.exception;

public class JabatanNotFoundException extends RuntimeException{
    public JabatanNotFoundException(Long id) {
        super("Jabatan dengan id " + id + " tidak ditemukan.");
    }

    public JabatanNotFoundException(String kodeJabatan) {
        super("Jabatan dengan kode " + kodeJabatan + " tidak ditemukan.");
    }
}
