package cc.kertaskerja.towerdata.kegiatan.web;

import cc.kertaskerja.towerdata.kegiatan.domain.Kegiatan;
import cc.kertaskerja.towerdata.kegiatan.domain.KegiatanService;
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
public class KegiatanController {
    private final KegiatanService kegiatanService;

    public KegiatanController(KegiatanService kegiatanService) {
        this.kegiatanService = kegiatanService;
    }
    
    @GetMapping("detail/{id}")
    public Kegiatan getById(@PathVariable("id") Long id) {
        return kegiatanService.detailKegiatan(id);
    }
    
    @GetMapping("search")
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
                        kegiatan.namaKegiatan(),
                        kegiatan.penunjang()
                ))
                .toList();
    }
    
    @GetMapping("penunjang/search")
    public List<KegiatanSearchResponse> getPenunjangSearchData(
            @RequestParam(value = "penunjang", required = false) Boolean penunjangFilter,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Kegiatan> kegiatans = kegiatanService.getDataByPenunjangFilter(penunjangFilter, page, size);

        return kegiatans.stream()
                .map(kegiatan -> new KegiatanSearchResponse(
                        kegiatan.kodeKegiatan(),
                        kegiatan.namaKegiatan(),
                        kegiatan.penunjang()
                ))
                .toList();
    }

    @PutMapping("update/{id}")
    public Kegiatan put(@PathVariable("id") Long id, @Valid @RequestBody KegiatanRequest request) {
        // Ambil data kegiatan yang sudah dibuat
        Kegiatan existingKegiatan = kegiatanService.detailKegiatan(id);

        Kegiatan kegiatan = new Kegiatan(
                id,
                request.kodeKegiatan(),
                request.namaKegiatan(),
                request.kodePemda(),
                request.penunjang(),
                existingKegiatan.createdDate(),
                null
        );

        return kegiatanService.ubahKegiatan(id, kegiatan);
    }

    @PostMapping
    public ResponseEntity<Kegiatan> post(@Valid @RequestBody KegiatanRequest request) {
        Kegiatan kegiatan = Kegiatan.of(
                request.kodeKegiatan(),
                request.namaKegiatan(),
                request.kodePemda(),
                request.penunjang()
        );
        Kegiatan saved = kegiatanService.tambahKegiatan(kegiatan);
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
        kegiatanService.hapusKegiatan(id);
    }
}
