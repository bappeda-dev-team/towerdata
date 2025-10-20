package cc.kertaskerja.towerdata.urusan.web;

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

import cc.kertaskerja.towerdata.urusan.domain.Urusan;
import cc.kertaskerja.towerdata.urusan.domain.UrusanService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("urusan")
public class UrusanController {
	public final UrusanService urusanService;
	
	public UrusanController(UrusanService urusanService) {
		this.urusanService = urusanService;
	}
	
	@GetMapping("detail/{id}")
    public Urusan getById(@PathVariable("id") Long id) {
        return urusanService.detailUrusan(id);
    }
	
	@GetMapping("search")
    public List<UrusanSearchResponse> search(
            @RequestParam(value = "kode", required = false) String kodeUrusan,
            @RequestParam(value = "nama", required = false) String namaUrusan,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Urusan> urusans = urusanService.cariUrusan(
                kodeUrusan != null ? kodeUrusan : "",
                namaUrusan != null ? namaUrusan : "",
                page,
                size
        );

        return urusans.stream()
                .map(urusan -> new UrusanSearchResponse(
                        urusan.kodeUrusan(),
                        urusan.namaUrusan(),
                        urusan.penunjang()
                ))
                .toList();
    }
	
	@GetMapping("penunjang/search")
    public List<UrusanSearchResponse> getPenunjangSearchData(
            @RequestParam(value = "penunjang", required = false) Boolean penunjangFilter,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Urusan> urusans = urusanService.getDataByPenunjangFilter(penunjangFilter, page, size);

        return urusans.stream()
                .map(urusan -> new UrusanSearchResponse(
                        urusan.kodeUrusan(),
                        urusan.namaUrusan(),
                        urusan.penunjang()
                ))
                .toList();
    }
	
	@PutMapping("update/{id}")
    public Urusan put(@PathVariable("id") Long id, @Valid @RequestBody UrusanRequest request) {
        // Ambil data urusan yang sudah dibuat
        Urusan existingUrusan = urusanService.detailUrusan(id);

        Urusan urusan = new Urusan(
                id,
                request.kodeUrusan(),
                request.namaUrusan(),
                request.kodePemda(),
                request.penunjang(),
                existingUrusan.createdDate(),
                null
        );

        return urusanService.ubahUrusan(id, urusan);
    }
	
	@PostMapping
    public ResponseEntity<Urusan> post(@Valid @RequestBody UrusanRequest request) {
        Urusan urusan = Urusan.of(
                request.kodeUrusan(),
                request.namaUrusan(),
                request.kodePemda(),
                request.penunjang()
        );
        Urusan saved = urusanService.tambahUrusan(urusan);
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
        urusanService.hapusUrusan(id);
    }
}
