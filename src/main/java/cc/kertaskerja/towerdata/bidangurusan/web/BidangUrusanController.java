package cc.kertaskerja.towerdata.bidangurusan.web;

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

import cc.kertaskerja.towerdata.bidangurusan.domain.BidangUrusan;
import cc.kertaskerja.towerdata.bidangurusan.domain.BidangUrusanService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("bidangurusan")
public class BidangUrusanController {
	private final BidangUrusanService bidangUrusanService;
	
	public BidangUrusanController(BidangUrusanService bidangUrusanService) {
		this.bidangUrusanService = bidangUrusanService;
	}
	
	@GetMapping("detail/{id}")
    public BidangUrusan getById(@PathVariable("id") Long id) {
        return bidangUrusanService.detailBidangUrusan(id);
    }
	
	@GetMapping("cari")
    public List<BidangUrusanSearchResponse> search(
            @RequestParam(value = "kode", required = false) String kodeBidangUrusan,
            @RequestParam(value = "nama", required = false) String namaBidangUrusan,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<BidangUrusan> bidangurusans = bidangUrusanService.cariBidangUrusan(
                kodeBidangUrusan != null ? kodeBidangUrusan : "",
                namaBidangUrusan != null ? namaBidangUrusan : "",
                page,
                size
        );

        return bidangurusans.stream()
                .map(bidangurusan -> new BidangUrusanSearchResponse(
                        bidangurusan.kodeBidangUrusan(),
                        bidangurusan.namaBidangUrusan(),
                        bidangurusan.penunjang(),
                        bidangurusan.opdId()
                ))
                .toList();
    }
	
	@GetMapping("penunjang/cari")
    public List<BidangUrusanSearchResponse> getPenunjangSearchData(
            @RequestParam(value = "penunjang", required = false) Boolean penunjangFilter,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<BidangUrusan> bidangUrusans = bidangUrusanService.getDataByPenunjangFilter(penunjangFilter, page, size);

        return bidangUrusans.stream()
                .map(bidangurusan -> new BidangUrusanSearchResponse(
                        bidangurusan.kodeBidangUrusan(),
                        bidangurusan.namaBidangUrusan(),
                        bidangurusan.penunjang(),
                        bidangurusan.opdId()
                ))
                .toList();
    }
	
	@PutMapping("update/{id}")
    public BidangUrusan put(@PathVariable("id") Long id, @Valid @RequestBody BidangUrusanRequest request) {
        // Ambil data bidang urusan yang sudah dibuat
        BidangUrusan existingBidangUrusan = bidangUrusanService.detailBidangUrusan(id);

        BidangUrusan bidangUrusan = new BidangUrusan(
                id,
                request.kodeBidangUrusan(),
                request.namaBidangUrusan(),
                request.kodePemda(),
                request.penunjang(),
                request.opdId(),
                existingBidangUrusan.createdDate(),
                null
        );

        return bidangUrusanService.ubahBidangUrusan(id, bidangUrusan);
    }
	
	@PostMapping
    public ResponseEntity<BidangUrusan> post(@Valid @RequestBody BidangUrusanRequest request) {
        BidangUrusan bidangUrusan = BidangUrusan.of(
                request.kodeBidangUrusan(),
                request.namaBidangUrusan(),
                request.kodePemda(),
                request.penunjang(),
                request.opdId()
        );
        BidangUrusan saved = bidangUrusanService.tambahBidangUrusan(bidangUrusan);
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
        bidangUrusanService.hapusBidangUrusan(id);
    }
}
