package cc.kertaskerja.towerdata.subkegiatan.domain.exception;

public class SubKegiatanNotFoundException extends RuntimeException {
    public SubKegiatanNotFoundException(Long id) {
        super("Sub kegiatan dengan Id " + id + " tidak ditemukan.");
    }
}
