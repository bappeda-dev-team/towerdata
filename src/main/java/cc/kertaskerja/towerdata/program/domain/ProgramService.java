package cc.kertaskerja.towerdata.program.domain;

import cc.kertaskerja.towerdata.program.domain.exception.ProgramNotFoundException;
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

    public Page<Program> getDataByPenunjangFilter(Boolean penunjangFilter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (penunjangFilter == null) {
            return programRepository.findAll(pageable);
        } else {
            return programRepository.findByPenunjang(penunjangFilter, pageable);
        }
    }

    public Program detailProgram(Long id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new ProgramNotFoundException(id));
    }

    public Program tambahProgram(Program program) {
        return programRepository.save(program);
    }

    public Program ubahProgram(Long id, Program program) {
        if (!programRepository.existsById(id)) {
            throw new ProgramNotFoundException(id);
        }

        return programRepository.save(program);
    }

    public void hapusProgram(Long id) {
        if (!programRepository.existsById(id)) {
            throw new ProgramNotFoundException(id);
        }

        programRepository.deleteById(id);
    }
}
