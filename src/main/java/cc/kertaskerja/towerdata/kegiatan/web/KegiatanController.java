package cc.kertaskerja.towerdata.kegiatan.web;

import cc.kertaskerja.towerdata.kegiatan.domain.Kegiatan;
import cc.kertaskerja.towerdata.kegiatan.domain.KegiatanService;
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
@RequestMapping("kegiatan")
@Tag(name = "Kegiatan")
public class KegiatanController {
    private final KegiatanService kegiatanService;

    public KegiatanController(KegiatanService kegiatanService) {
        this.kegiatanService = kegiatanService;
    }
    
    @GetMapping("detail/{kodeKegiatan}")
    public Kegiatan getByKode(@PathVariable("kodeKegiatan") String kodeKegiatan) {
        return kegiatanService.detailKegiatan(kodeKegiatan);
    }
    
    @GetMapping("detail/get-all-kegiatans")
    public List<KegiatanSearchResponse> findAll() {
        return kegiatanService.semuaKegiatan().stream()
                .map(kegiatan -> new KegiatanSearchResponse(
                        kegiatan.kodeKegiatan(),
                        kegiatan.namaKegiatan()
                ))
                .toList();
    }
    
    @GetMapping("detail/cari-kegiatans")
    public List<KegiatanSearchResponse> search(
            @RequestParam(value = "kode", required = false) String kodeKegiatan,
            @RequestParam(value = "nama", required = false) String namaKegiatan,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Kegiatan> kegiatans = kegiatanService.cariKegiatan(
                kodeKegiatan != null ? kodeKegiatan : "",
                namaKegiatan != null ? namaKegiatan : "",
                page,
                size
        );

        return kegiatans.stream()
                .map(kegiatan -> new KegiatanSearchResponse(
                        kegiatan.kodeKegiatan(),
                        kegiatan.namaKegiatan()
                ))
                .toList();
    }

    @PutMapping("update/{kodeKegiatan}")
    public Kegiatan put(@PathVariable("kodeKegiatan") String kodeKegiatan, @Valid @RequestBody KegiatanRequest request) {
        return kegiatanService.ubahKegiatan(kodeKegiatan, request);
    }

    @PostMapping
    public ResponseEntity<Kegiatan> post(@Valid @RequestBody KegiatanRequest request) {
        Kegiatan saved = kegiatanService.tambahKegiatan(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.id())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("delete/{kodeKegiatan}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("kodeKegiatan") String kodeKegiatan) {
        kegiatanService.hapusKegiatan(kodeKegiatan);
    }
}
