package cc.kertaskerja.towerdata.subkegiatan.web;

import cc.kertaskerja.towerdata.subkegiatan.domain.SubKegiatan;
import cc.kertaskerja.towerdata.subkegiatan.domain.SubKegiatanService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("subkegiatan")
public class SubkegiatanController {
    private final SubKegiatanService subKegiatanService;

    public SubkegiatanController(SubKegiatanService subKegiatanService) {
        this.subKegiatanService = subKegiatanService;
    }

    @GetMapping("detail/{id}")
    public SubKegiatan getById(@PathVariable("id") Long id) {
        return subKegiatanService.detailSubKegiatan(id);
    }

    @GetMapping("search")
    public List<SubkegiatanSearchResponse> search(
            @RequestParam(value = "kode", required = false) String kodeSubKegiatan,
            @RequestParam(value = "nama", required = false) String namaSubKegiatan,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<SubKegiatan> subKegiatans = subKegiatanService.cariSubKegiatan(
                kodeSubKegiatan != null ? kodeSubKegiatan : "",
                namaSubKegiatan != null ? namaSubKegiatan : "",
                page,
                size
        );

        return subKegiatans.stream()
                .map(subKegiatan -> new SubkegiatanSearchResponse(
                        subKegiatan.kodeSubKegiatan(),
                        subKegiatan.namaSubKegiatan(),
                        subKegiatan.penunjang()
                ))
                .toList();
    }

    @GetMapping("penunjang/search")
    public List<SubkegiatanSearchResponse> getPenunjangSearchData(
            @RequestParam(value = "penunjang", required = false) Boolean penunjangFilter,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<SubKegiatan> subKegiatans = subKegiatanService.getDataByPenunjangFilter(penunjangFilter, page, size);

        return subKegiatans.stream()
                .map(subKegiatan -> new SubkegiatanSearchResponse(
                        subKegiatan.kodeSubKegiatan(),
                        subKegiatan.namaSubKegiatan(),
                        subKegiatan.penunjang()
                ))
                .toList();
    }

    
    @PutMapping("update/{id}")
    public SubKegiatan put(@PathVariable("id") Long id, @Valid @RequestBody SubkegiatanRequest request) {
        // Ambil data sub kegiatan yang sudah dibuat
        SubKegiatan existingSubKegiatan = subKegiatanService.detailSubKegiatan(id);

        SubKegiatan subKegiatan = new SubKegiatan(
                id,
                request.kodeSubKegiatan(),
                request.namaSubKegiatan(),
                request.kodePemda(),
                request.penunjang(),
                existingSubKegiatan.createdDate(),
                null
        );

        return subKegiatanService.ubahSubKegiatan(id, subKegiatan);
    }

    @PostMapping
    public ResponseEntity<SubKegiatan> post(@Valid @RequestBody SubkegiatanRequest request) {
        SubKegiatan subKegiatan = SubKegiatan.of(
                request.kodeSubKegiatan(),
                request.namaSubKegiatan(),
                request.kodePemda(),
                request.penunjang()
        );
        SubKegiatan saved = subKegiatanService.tambahSubKegiatan(subKegiatan);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.id())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @PostMapping("create/batch")
    public ResponseEntity<List<SubKegiatan>> postBatch(@Valid @RequestBody List<SubkegiatanRequest> requests) {
        List<SubKegiatan> savedSubKegiatans = requests.stream()
                .map(request -> SubKegiatan.of(
                        request.kodeSubKegiatan(),
                        request.namaSubKegiatan(),
                        request.kodePemda(),
                        request.penunjang()
                ))
                .map(subKegiatanService::tambahSubKegiatan)
                .toList();

        return ResponseEntity.ok(savedSubKegiatans);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        subKegiatanService.hapusSubKegiatan(id);
    }
}
