package cc.kertaskerja.towerdata.subkegiatan.web;

import cc.kertaskerja.towerdata.subkegiatan.domain.SubKegiatan;
import cc.kertaskerja.towerdata.subkegiatan.domain.SubKegiatanService;
import cc.kertaskerja.towerdata.subkegiatan.web.response.SubkegiatanRequest;
import cc.kertaskerja.towerdata.subkegiatan.web.response.SubkegiatanSearchResponse;
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
@RequestMapping("subkegiatan")
@Tag(name = "Subkegiatan")
public class SubkegiatanController {
    private final SubKegiatanService subKegiatanService;

    public SubkegiatanController(SubKegiatanService subKegiatanService) {
        this.subKegiatanService = subKegiatanService;
    }

    @GetMapping("detail/{kodeSubKegiatan}")
    public SubKegiatan getByKodeSubKegiatan(@PathVariable("kodeSubKegiatan") String kodeSubKegiatan) {
        return subKegiatanService.detailSubKegiatan(kodeSubKegiatan);
    }
    
    @GetMapping("detail/get-all-subkegiatans")
    public List<SubkegiatanSearchResponse> findAll() {
        return subKegiatanService.semuaSubKegiatan().stream()
                .map(subKegiatan -> new SubkegiatanSearchResponse(
                        subKegiatan.kodeSubKegiatan(),
                        subKegiatan.namaSubKegiatan()
                ))
                .toList();
    }

    @GetMapping("detail/cari-subkegiatans")
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
                        subKegiatan.namaSubKegiatan()
                ))
                .toList();
    }

    @PutMapping("update/{kodeSubKegiatan}")
    public SubKegiatan put(
            @PathVariable("kodeSubKegiatan") String kodeSubKegiatan,
            @Valid @RequestBody SubkegiatanRequest request
    ) {
        SubKegiatan existingSubKegiatan = subKegiatanService.detailSubKegiatan(kodeSubKegiatan);

        SubKegiatan subKegiatan = new SubKegiatan(
                existingSubKegiatan.id(),
                request.kodeSubKegiatan(),
                request.namaSubKegiatan(),
                existingSubKegiatan.createdDate(),
                null
        );

        return subKegiatanService.ubahSubKegiatan(kodeSubKegiatan, subKegiatan);
    }

    @PostMapping
    public ResponseEntity<SubKegiatan> post(@Valid @RequestBody SubkegiatanRequest request) {
        SubKegiatan subKegiatan = SubKegiatan.of(
                request.kodeSubKegiatan(),
                request.namaSubKegiatan()
        );
        SubKegiatan saved = subKegiatanService.tambahSubKegiatan(subKegiatan);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.id())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("delete/{kodeSubKegiatan}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("kodeSubKegiatan") String kodeSubKegiatan) {
        subKegiatanService.hapusSubKegiatan(kodeSubKegiatan);
    }
}
