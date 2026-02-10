package cc.kertaskerja.towerdata.program.web;

import cc.kertaskerja.towerdata.program.domain.Program;
import cc.kertaskerja.towerdata.program.domain.ProgramService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Program")
public class ProgramController {
    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping("detail/{id}")
    public Program getById(@PathVariable("id") Long id) {
        return programService.detailProgram(id);
    }

    @GetMapping("detail/get-all-programs")
    public List<ProgramSearchResponse> findAll() {
        return programService.semuaProgram().stream()
                .map(program -> new ProgramSearchResponse(
                        program.kodeProgram(),
                        program.namaProgram()
                ))
                .toList();
    }

    @GetMapping("detail/cari-programs")
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
                        program.namaProgram()
                ))
                .toList();
    }

    @PutMapping("update/{id}")
    public Program put(@PathVariable("id") Long id, @Valid @RequestBody ProgramRequest request) {
        return programService.ubahProgram(id, request);
    }

    @PostMapping
    public ResponseEntity<Program> post(@Valid @RequestBody ProgramRequest request) {
        Program saved = programService.tambahProgram(request);
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
