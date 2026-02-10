package cc.kertaskerja.towerdata.program.domain.exception;

public class ProgramNotFoundException extends RuntimeException {
    public ProgramNotFoundException(Long id) {
        super("Program dengan id " + id + " tidak ditemukan.");
    }

    public ProgramNotFoundException(String kodeProgram) {
        super("Program dengan kode " + kodeProgram + " tidak ditemukan.");
    }
}
