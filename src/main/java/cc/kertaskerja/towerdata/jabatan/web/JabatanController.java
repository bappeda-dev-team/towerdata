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

    @GetMapping("detail/{kodeJabatan}")
    public Jabatan getByKode(@PathVariable("kodeJabatan") String kodeJabatan) {
        return jabatanService.detailJabatan(kodeJabatan);
    }

    @GetMapping("detail/findall")
    public List<JabatanSearchResponse> getAll() {
        return jabatanService.semuaJabatan().stream()
                .map(jabatan -> new JabatanSearchResponse(
                        jabatan.kodeJabatan(),
                        jabatan.namaJabatan(),
                        jabatan.struktural()))
                .toList();
    }

    @GetMapping("detail/cari")
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
                        jabatan.struktural()))
                .toList();
    }

    @PutMapping("update/{kodeJabatan}")
    public Jabatan put(@PathVariable("kodeJabatan") String kodeJabatan, @Valid @RequestBody JabatanRequest request) {
        // Ambil data jabatan yang sudah dibuat
        Jabatan existingJabatan = jabatanService.detailJabatan(kodeJabatan);

        Jabatan jabatan = new Jabatan(
                existingJabatan.id(),
                request.kodeJabatan(),
                request.namaJabatan(),
                request.struktural(),
                existingJabatan.createdDate(),
                null
        );

        return jabatanService.ubahJabatan(kodeJabatan, jabatan);
    }

    @PostMapping
    public ResponseEntity<Jabatan> post(@Valid @RequestBody JabatanRequest request) {
        Jabatan jabatan = Jabatan.of(
                request.kodeJabatan(),
                request.namaJabatan(),
                request.struktural()
        );
        Jabatan saved = jabatanService.tambahJabatan(jabatan);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{kode}")
                .buildAndExpand(saved.kodeJabatan())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("delete/{kodeJabatan}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("kodeJabatan") String kodeJabatan) {
        jabatanService.hapusJabatan(kodeJabatan);
    }
}
