package cc.kertaskerja.towerdata.program.domain;

import cc.kertaskerja.towerdata.program.domain.exception.ProgramNotFoundException;
import cc.kertaskerja.towerdata.program.web.ProgramRequest;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public Page<Program> cariProgram(String kodeProgram, String namaProgram, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return programRepository.findByKodeProgramContainingIgnoreCaseAndNamaProgramContainingIgnoreCase(
                kodeProgram, namaProgram, pageable
        );
    }

    public List<Program> semuaProgram() {
        return programRepository.findAll(Pageable.unpaged()).getContent();
    }

    public Program detailProgram(String kodeProgram) {
        return programRepository.findByKodeProgram(kodeProgram)
                .orElseThrow(() -> new ProgramNotFoundException(kodeProgram));
    }

    public Program tambahProgram(ProgramRequest request) {
        Program program = Program.of(
                request.kodeProgram(),
                request.namaProgram()
        );

        return programRepository.save(program);
    }

    public Program ubahProgram(String kodeProgram, ProgramRequest request) {
        Program existingProgram = programRepository.findByKodeProgram(kodeProgram)
                .orElseThrow(() -> new ProgramNotFoundException(kodeProgram));

        Program program = new Program(
                existingProgram.id(),
                request.kodeProgram(),
                request.namaProgram(),
                existingProgram.createdDate(),
                null
        );

        return programRepository.save(program);
    }

    public void hapusProgram(String kodeProgram) {
        Program existingProgram = programRepository.findByKodeProgram(kodeProgram)
                .orElseThrow(() -> new ProgramNotFoundException(kodeProgram));

        programRepository.deleteById(existingProgram.id());
    }
}
