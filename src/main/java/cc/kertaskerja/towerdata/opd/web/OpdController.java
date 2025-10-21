package cc.kertaskerja.towerdata.opd.web;

import cc.kertaskerja.towerdata.opd.domain.Opd;
import cc.kertaskerja.towerdata.opd.domain.OpdService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("opd")
public class OpdController {
    private final OpdService opdService;

    public OpdController(OpdService opdService) {
        this.opdService = opdService;
    }

    @GetMapping("detail/{id}")
    public Opd getById(@PathVariable("id") Long id) {
        return opdService.detailOpd(id);
    }

    @GetMapping("search")
    public List<OpdSearchResponse> search(
            @RequestParam(value = "kode", required = false) String kodeOpd,
            @RequestParam(value = "nama", required = false) String namaOpd,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Opd> opds = opdService.cariOpd(
                kodeOpd != null ? kodeOpd : "",
                namaOpd != null ? namaOpd : "",
                page,
                size
        );

        return opds.stream()
                .map(opd -> new OpdSearchResponse(
                        opd.kodeOpd(),
                        opd.namaOpd(),
                        opd.penunjang()
                ))
                .toList();
    }

    @GetMapping("penunjang/search")
    public List<OpdSearchResponse> getPenunjangSearchData(
            @RequestParam(value = "penunjang", required = false) Boolean penunjangFilter,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Opd> opds = opdService.getDataByPenunjangFilter(penunjangFilter, page, size);

        return opds.stream()
                .map(opd -> new OpdSearchResponse(
                        opd.kodeOpd(),
                        opd.namaOpd(),
                        opd.penunjang()
                ))
                .toList();
    }

    @PutMapping("update/{id}")
    public Opd put(@PathVariable("id") Long id, @Valid @RequestBody OpdRequest request) {
        // Ambil data opd yang sudah dibuat
        Opd existingOpd = opdService.detailOpd(id);

        Opd opd = new Opd(
                id,
                request.kodeOpd(),
                request.namaOpd(),
                request.kodePemda(),
                request.penunjang(),
                existingOpd.createdDate(),
                null
        );

        return opdService.ubahOpd(id, opd);
    }

    @PostMapping
    public ResponseEntity<Opd> post(@Valid @RequestBody OpdRequest request) {
        Opd opd = Opd.of(
                request.kodeOpd(),
                request.namaOpd(),
                request.kodePemda(),
                request.penunjang()
        );
        Opd saved = opdService.tambahOpd(opd);
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
        opdService.hapusOpd(id);
    }
}
