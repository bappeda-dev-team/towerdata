package cc.kertaskerja.towerdata.rekening.web;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cc.kertaskerja.towerdata.rekening.domain.Rekening;
import cc.kertaskerja.towerdata.rekening.domain.RekeningService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("rekening")
public class RekeningController {
    private final RekeningService rekeningService;

    public RekeningController(RekeningService rekeningService) {
        this.rekeningService = rekeningService;
    }

    @GetMapping("detail/{kodeRekening}")
    public Rekening getByKodeRekening(@PathVariable("kodeRekening") String kodeRekening) {
        return rekeningService.detailRekening(kodeRekening);
    }

    @GetMapping("detail/findall")
    public List<Rekening> getAll() {
        return rekeningService.semuaRekening();
    }

    @GetMapping("detail/cari")
    public List<RekeningSearchResponse> search(
            @RequestParam(value = "kode", required = false) String kodeRekening,
            @RequestParam(value = "nama", required = false) String namaRekening,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Rekening> rekenings = rekeningService.cariRekening(
                kodeRekening != null ? kodeRekening : "",
                namaRekening != null ? namaRekening : "",
                page,
                size
        );

        return rekenings.stream()
                .map(rekening -> new RekeningSearchResponse(
                        rekening.kodeRekening(),
                        rekening.namaRekening(),
                        rekening.aktif()
                ))
                .toList();
    }

    @PutMapping("update/{kodeRekening}")
    public Rekening put(@PathVariable("kodeRekening") String kodeRekening, @Valid @RequestBody RekeningRequest request) {
        // Ambil data Rekening yang sudah dibuat
        Rekening existingRekening = rekeningService.detailRekening(kodeRekening);

        Rekening rekening = new Rekening(
                existingRekening.id(),
                request.kodeRekening(),
                request.namaRekening(),
                request.aktif(),
                existingRekening.createdDate(),
                null
        );

        return rekeningService.ubahRekening(kodeRekening, rekening);
    }

    @PostMapping
    public ResponseEntity<Rekening> post(@Valid @RequestBody RekeningRequest request) {
        Rekening rekening = Rekening.of(
                request.kodeRekening(),
                request.namaRekening(),
                request.aktif()
        );
        Rekening saved = rekeningService.tambahRekening(rekening);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{kodeRekening}")
                .buildAndExpand(saved.kodeRekening())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("delete/{kodeRekening}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("kodeRekening") String kodeRekening) {
        rekeningService.hapusRekening(kodeRekening);
    }
}
