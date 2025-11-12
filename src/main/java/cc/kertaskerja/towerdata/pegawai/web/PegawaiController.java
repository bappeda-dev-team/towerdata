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
import jakarta.validation.Valid;

@RestController
@RequestMapping("pegawai")
public class PegawaiController {
	private final PegawaiService pegawaiService;
	private final OpdService opdService;

	public PegawaiController(PegawaiService pegawaiService, OpdService opdService) {
		this.pegawaiService = pegawaiService;
		this.opdService = opdService;
	}
	
	@GetMapping("detail/{id}")
    public Pegawai getById(@PathVariable("id") Long id) {
        return pegawaiService.detailPegawai(id);
    }
	
	@GetMapping("search")
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
                        pegawai.kodePegawai(),
                        pegawai.namaPegawai(),
                        pegawai.penunjang(),
                        pegawai.opdId(),
                        pegawai.namaRolePegawai()
                ))
                .toList();
    }
	
	@GetMapping("penunjang/search")
    public List<PegawaiSearchResponse> getPenunjangSearchData(
            @RequestParam(value = "penunjang", required = false) Boolean penunjangFilter,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Pegawai> pegawais = pegawaiService.getDataByPenunjangFilter(penunjangFilter, page, size);

        return pegawais.stream()
                .map(pegawai -> new PegawaiSearchResponse(
                        pegawai.kodePegawai(),
                        pegawai.namaPegawai(),
                        pegawai.penunjang(),
                        pegawai.opdId(),
                        pegawai.namaRolePegawai()
                ))
                .toList();
    }
	
	@PutMapping("update/{id}")
    public Pegawai put(@PathVariable("id") Long id, @Valid @RequestBody PegawaiRequest request) {
        // Ambil data pegawai yang sudah dibuat
        Pegawai existingPegawai = pegawaiService.detailPegawai(id);

        Pegawai pegawai = new Pegawai(
                id,
                request.kodePegawai(),
                request.namaPegawai(),
                request.kodePemda(),
                request.opdId(),
                request.penunjang(),
                request.namaRolePegawai(),
                existingPegawai.createdDate(),
                null
        );

        return pegawaiService.ubahPegawai(id, pegawai);
    }
	
	@PostMapping
    public ResponseEntity<Pegawai> post(@Valid @RequestBody PegawaiRequest request) {
        Pegawai pegawai = Pegawai.of(
                request.kodePegawai(),
                request.namaPegawai(),
                request.kodePemda(),
                request.opdId(),
                request.penunjang(),
                request.namaRolePegawai()
        );
        Pegawai saved = pegawaiService.tambahPegawai(pegawai);
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
        pegawaiService.hapusPegawai(id);
    }
}
