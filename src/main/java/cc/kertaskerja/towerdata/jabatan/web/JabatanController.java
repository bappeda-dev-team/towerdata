package cc.kertaskerja.towerdata.jabatan.web;

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

import cc.kertaskerja.towerdata.jabatan.domain.Jabatan;
import cc.kertaskerja.towerdata.jabatan.domain.JabatanService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("jabatan")
public class JabatanController {
    private final JabatanService jabatanService;

    public JabatanController(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }

    @GetMapping("detail/{id}")
    public Jabatan getById(@PathVariable("id") Long id) {
        return jabatanService.detailJabatan(id);
    }

    @GetMapping("search")
    public List<JabatanSearchResponse> search(
            @RequestParam(value = "kode", required = false) String kodeJabatan,
            @RequestParam(value = "nama", required = false) String namaJabatan,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<Jabatan> jabatans = jabatanService.cariJabatan(
                kodeJabatan != null ? kodeJabatan : "",
                namaJabatan != null ? namaJabatan : "",
                page,
                size);

        return jabatans.stream()
                .map(jabatan -> new JabatanSearchResponse(
                        jabatan.kodeJabatan(),
                        jabatan.namaJabatan(),
                        jabatan.penunjang()))
                .toList();
    }

    @GetMapping("penunjang/search")
    public List<JabatanSearchResponse> getPenunjangSearchData(
            @RequestParam(value = "penunjang", required = false) Boolean penunjangFilter,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Jabatan> jabatans = jabatanService.getDataByPenunjangFilter(penunjangFilter, page, size);

        return jabatans.stream()
                .map(jabatan -> new JabatanSearchResponse(
                        jabatan.kodeJabatan(),
                        jabatan.namaJabatan(),
                        jabatan.penunjang()
                ))
                .toList();
    }

    @PutMapping("update/{id}")
    public Jabatan put(@PathVariable("id") Long id, @Valid @RequestBody JabatanRequest request) {
        // Ambil data jabatan yang sudah dibuat
        Jabatan existingJabatan = jabatanService.detailJabatan(id);

        Jabatan jabatan = new Jabatan(
                id,
                request.kodeJabatan(),
                request.namaJabatan(),
                request.kodeJabatan(),
                request.penunjang(),
                existingJabatan.createdDate(),
                null
        );

        return jabatanService.ubahJabatan(id, jabatan);
    }

    @PostMapping
    public ResponseEntity<Jabatan> post(@Valid @RequestBody JabatanRequest request) {
        Jabatan jabatan = Jabatan.of(
                request.kodeJabatan(),
                request.namaJabatan(),
                request.kodePemda(),
                request.penunjang()
        );
        Jabatan saved = jabatanService.tambahJabatan(jabatan);
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
        jabatanService.hapusJabatan(id);
    }
}
