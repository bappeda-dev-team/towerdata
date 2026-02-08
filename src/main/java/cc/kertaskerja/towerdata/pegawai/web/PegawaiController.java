package cc.kertaskerja.towerdata.pegawai.web;

import java.net.URI;
import java.util.List;

import cc.kertaskerja.towerdata.opd.domain.OpdService;
import cc.kertaskerja.towerdata.pegawai.web.response.PegawaiResponse;
import cc.kertaskerja.towerdata.pegawai.web.response.PegawaiSearchResponse;
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

import cc.kertaskerja.towerdata.pegawai.domain.Pegawai;
import cc.kertaskerja.towerdata.pegawai.domain.PegawaiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pegawai")
@Tag(name = "Pegawai")
public class PegawaiController {
	private final PegawaiService pegawaiService;
	private final OpdService opdService;

	public PegawaiController(PegawaiService pegawaiService, OpdService opdService) {
		this.pegawaiService = pegawaiService;
		this.opdService = opdService;
	}
	
	@GetMapping("detail/{nipPegawai}")
    public Pegawai getByNipPegawai(@PathVariable("nipPegawai") String nipPegawai) {
        return pegawaiService.detailPegawai(nipPegawai);
    }
	
	@GetMapping("detail/findall")
    public List<PegawaiResponse> findAll() {
        return pegawaiService.findAll()
                .stream()
                .map(pegawai -> new PegawaiResponse(
                        pegawai.nipPegawai(),
                        pegawai.namaPegawai(),
                        pegawai.kodeOpd(),
                        pegawai.aktif(),
                        pegawai.khusus()
                ))
                .toList();
    }
	
	@GetMapping("detail/cari")
    public List<PegawaiSearchResponse> search(
            @RequestParam(value = "kode", required = false) String kodePegawai,
            @RequestParam(value = "nama", required = false) String namaPegawai,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Pegawai> pegawais = pegawaiService.cariPegawai(
                kodePegawai != null ? kodePegawai : "",
                namaPegawai != null ? namaPegawai : "",
                page,
                size
        );

        return pegawais.stream()
                .map(pegawai -> new PegawaiSearchResponse(
                        pegawai.nipPegawai(),
                        pegawai.namaPegawai(),
                        pegawai.kodeOpd(),
                        pegawai.aktif(),
                        pegawai.khusus()
                ))
                .toList();
    }
	
	@PutMapping("update/{nipPegawai}")
    public Pegawai put(@PathVariable("nipPegawai") String nipPegawai, @Valid @RequestBody PegawaiRequest request) {
        return pegawaiService.ubahPegawai(nipPegawai, request);
    }
	
    @PostMapping
    public ResponseEntity<Pegawai> post(@Valid @RequestBody PegawaiRequest request) {
        Pegawai saved = pegawaiService.tambahPegawai(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.id())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }
	
	@DeleteMapping("delete/{nipPegawai}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("nipPegawai") String nipPegawai) {
        pegawaiService.hapusPegawai(nipPegawai);
    }
}
