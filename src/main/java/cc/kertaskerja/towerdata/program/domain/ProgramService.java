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

    public Program tambahProgram(ProgramRequest request) {
        Program program = Program.of(
                request.kodeProgram(),
                request.namaProgram(),
                request.kodePemda(),
                request.penunjang()
        );

        return programRepository.save(program);
    }

    public Program ubahProgram(Long id, ProgramRequest request) {
        Program existingProgram = detailProgram(id);

        Program program = new Program(
                existingProgram.id(),
                request.kodeProgram(),
                request.namaProgram(),
                request.kodePemda(),
                request.penunjang(),
                existingProgram.createdDate(),
                null
        );

        return programRepository.save(program);
    }

    public void hapusProgram(Long id) {
        if (!programRepository.existsById(id)) {
            throw new ProgramNotFoundException(id);
        }

        programRepository.deleteById(id);
    }
}
