package cc.kertaskerja.towerdata.jabatanpegawai.web;

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

import cc.kertaskerja.towerdata.jabatanpegawai.domain.JabatanPegawai;
import cc.kertaskerja.towerdata.jabatanpegawai.domain.JabatanPegawaiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("jabatanpegawai")
@Tag(name = "Jabatan Pegawai")
public class JabatanPegawaiController {
	public JabatanPegawaiService jabatanPegawaiService;
	
	public JabatanPegawaiController(JabatanPegawaiService jabatanPegawaiService) {
		this.jabatanPegawaiService = jabatanPegawaiService;
	}
	
	@GetMapping("detail/{id}")
    public JabatanPegawai getById(@PathVariable("id") Long id) {
        return jabatanPegawaiService.detailJabatanPegawai(id);
    }
	
	@GetMapping("detail/findall")
    public List<JabatanPegawaiSearchResponse> getAll() {
        return jabatanPegawaiService.semuaJabatanPegawai().stream()
                .map(jabatanPegawai -> new JabatanPegawaiSearchResponse(
                        jabatanPegawai.nipPegawai(),
                        jabatanPegawai.kodeJabatan(),
                        jabatanPegawai.kodePemda()))
                .toList();
    }
	
	@GetMapping("detail/cari")
    public List<JabatanPegawaiSearchResponse> search(
            @RequestParam(value = "nip", required = false) String nipPegawai,
            @RequestParam(value = "kode", required = false) String kodeJabatan,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<JabatanPegawai> jabatanPegawais = jabatanPegawaiService.cariJabatanPegawai(
                nipPegawai != null ? nipPegawai : "",
                kodeJabatan != null ? kodeJabatan : "",
                page,
                size);

        return jabatanPegawais.stream()
                .map(jabatanPegawai -> new JabatanPegawaiSearchResponse(
                        jabatanPegawai.nipPegawai(),
                        jabatanPegawai.kodeJabatan(),
                        jabatanPegawai.kodePemda()))
                .toList();
    }
	
	@PutMapping("update/{id}")
    public JabatanPegawai put(@PathVariable("id") Long id, @Valid @RequestBody JabatanPegawaiRequest request) {
        return jabatanPegawaiService.ubahJabatanPegawai(id, request);
    }
	
	@PostMapping
    public ResponseEntity<JabatanPegawai> post(@Valid @RequestBody JabatanPegawaiRequest request) {
        JabatanPegawai saved = jabatanPegawaiService.tambahJabatanPegawai(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{kode}")
                .buildAndExpand(saved.id())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }
	
	@DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        jabatanPegawaiService.hapusJabatanPegawai(id);
    }
}
