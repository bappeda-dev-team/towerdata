package cc.kertaskerja.towerdata.pemda.web;

import cc.kertaskerja.towerdata.pemda.domain.Pemda;
import cc.kertaskerja.towerdata.pemda.domain.PemdaService;
import cc.kertaskerja.towerdata.pemda.web.response.PemdaSearchResponse;
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
@RequestMapping("pemda")
@Tag(name = "Pemda")
public class PemdaController {
    private final PemdaService pemdaService;

    public PemdaController(PemdaService pemdaService) {
        this.pemdaService = pemdaService;
    }

    @GetMapping("detail/{kodePemda}")
    public Pemda getByKodePemda(@PathVariable("kodePemda") String kodePemda) {
        return pemdaService.detailPemdaByKodePemda(kodePemda);
    }

    @GetMapping("detail/findall")
    public List<PemdaSearchResponse> findAll() {
        return pemdaService.findAll()
                .stream()
                .map(pemda -> new PemdaSearchResponse(
                        pemda.kodePemda(),
                        pemda.namaPemda()
                ))
                .toList();
    }

    @GetMapping("/detail/cari")
    public List<PemdaSearchResponse> search(
            @RequestParam(value = "kode", required = false) String kodePemda,
            @RequestParam(value = "nama", required = false) String namaPemda,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Pemda> pemdas = pemdaService.cariPemda(
                kodePemda != null ? kodePemda : "",
                namaPemda != null ? namaPemda : "",
                page,
                size
        );

        return pemdas.stream()
                .map(pemda -> new PemdaSearchResponse(
                        pemda.kodePemda(),
                        pemda.namaPemda()
                ))
                .toList();
    }

    @PutMapping("update/{kodePemda}")
    public Pemda put(@PathVariable("kodePemda") String kodePemda, @Valid @RequestBody PemdaRequest request) {
        return pemdaService.ubahPemda(kodePemda, request);
    }

    @PostMapping
    public ResponseEntity<Pemda> post(@Valid @RequestBody PemdaRequest request) {
        Pemda saved = pemdaService.tambahPemda(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.id())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("delete/{kodePemda}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("kodePemda") String kodePemda) {
        pemdaService.hapusPemda(kodePemda);
    }
}
