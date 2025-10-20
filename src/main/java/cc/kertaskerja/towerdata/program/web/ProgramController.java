package cc.kertaskerja.towerdata.program.web;

import cc.kertaskerja.towerdata.program.domain.Program;
import cc.kertaskerja.towerdata.program.domain.ProgramService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("program")
public class ProgramController {
    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping("detail/{id}")
    public Program getById(@PathVariable("id") Long id) {
        return programService.detailProgram(id);
    }

    @GetMapping("search")
    public List<ProgramSearchResponse> search(
            @RequestParam(value = "kode", required = false) String kodeProgram,
            @RequestParam(value = "nama", required = false) String namaProgram,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Program> programs = programService.cariProgram(
                kodeProgram != null ? kodeProgram : "",
                namaProgram != null ? namaProgram : "",
                page,
                size
        );

        return programs.stream()
                .map(program -> new ProgramSearchResponse(
                        program.kodeProgram(),
                        program.namaProgram(),
                        program.penunjang()
                ))
                .toList();
    }

    @GetMapping("penunjang/search")
    public List<ProgramSearchResponse> getPenunjangSearchData(
            @RequestParam(value = "penunjang", required = false) Boolean penunjangFilter,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Program> programs = programService.getDataByPenunjangFilter(penunjangFilter, page, size);

        return programs.stream()
                .map(program -> new ProgramSearchResponse(
                        program.kodeProgram(),
                        program.namaProgram(),
                        program.penunjang()
                ))
                .toList();
    }

    @PutMapping("update/{id}")
    public Program put(@PathVariable("id") Long id, @Valid @RequestBody ProgramRequest request) {
        // Ambil data program yang sudah dibuat
        Program existingProgram = programService.detailProgram(id);

        Program program = new Program(
                id,
                request.kodeProgram(),
                request.namaProgram(),
                request.kodePemda(),
                request.penunjang(),
                existingProgram.createdDate(),
                null
        );

        return programService.ubahProgram(id, program);
    }

    @PostMapping
    public ResponseEntity<Program> post(@Valid @RequestBody ProgramRequest request) {
        Program program = Program.of(
                request.kodeProgram(),
                request.namaProgram(),
                request.kodePemda(),
                request.penunjang()
        );
        Program saved = programService.tambahProgram(program);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.id())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        programService.hapusProgram(id);
    }
}
