package cc.kertaskerja.towerdata.opd.web;

import cc.kertaskerja.towerdata.opd.domain.Opd;
import cc.kertaskerja.towerdata.opd.domain.OpdService;
import cc.kertaskerja.towerdata.opd.web.response.OpdSearchResponse;
import cc.kertaskerja.towerdata.opd.web.response.OpdSelectionResponse;
import cc.kertaskerja.towerdata.pegawai.domain.PegawaiService;
import cc.kertaskerja.towerdata.bidangurusan.domain.BidangUrusanService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("opd")
public class OpdController {
    private final OpdService opdService;
    private final PegawaiService pegawaiService;
    private final BidangUrusanService bidangUrusanService;

    public OpdController(OpdService opdService, PegawaiService pegawaiService, BidangUrusanService bidangUrusanService) {
        this.opdService = opdService;
        this.pegawaiService = pegawaiService;
        this.bidangUrusanService = bidangUrusanService;
    }
    
    /**
     * 
     * @param kodeOpd
     * @return satu data opd
     */
    @GetMapping("detail/{kodeOpd}")
    public Opd getByKodeOpd(@PathVariable("kodeOpd") String kodeOpd) {
        return opdService.detailOpdByKodeOpd(kodeOpd);
    }

    @GetMapping("/detail/cari")
    public List<OpdSearchResponse> search(
            @RequestParam(value = "kode", required = false) String kodeOpd,
            @RequestParam(value = "nama", required = false) String namaOpd,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Opd> opds = opdService.cariOpd(
                kodeOpd != null ? kodeOpd : "",
                namaOpd != null ? namaOpd : "",
                page,
                size
        );

        return opds.stream()
                .map(opd -> new OpdSearchResponse(
                        opd.kodeOpd(),
                        opd.namaOpd(),
                        opd.subOpd()
                ))
                .toList();
    }

    /**
     * pilih all opd
     * @return list semua data opd
     */
    @GetMapping("detail/findall")
    public List<OpdSelectionResponse> getOpdSelection() {
        return opdService.findAll()
                .stream()
                .map(opd -> new OpdSelectionResponse(
                        opd.kodeOpd(),
                        opd.namaOpd()
                ))
                .toList();
    }

    @PutMapping("update/{kodeOpd}")
    public Opd put(@PathVariable("kodeOpd") String kodeOpd, @Valid @RequestBody OpdRequest request) {
        Opd existingOpd = opdService.detailOpdByKodeOpd(kodeOpd);

        Opd opd = new Opd(
                existingOpd.id(),
                request.kodeOpd(),
                request.namaOpd(),
                request.kodePemda(),
                request.subOpd(),
                existingOpd.createdDate(),
                null
        );

        return opdService.ubahOpd(kodeOpd, opd);
    }

    @PostMapping
    public ResponseEntity<Opd> post(@Valid @RequestBody OpdRequest request) {
        Opd opd = Opd.of(
                request.kodeOpd(),
                request.namaOpd(),
                request.kodePemda(),
                request.subOpd()
        );
        Opd saved = opdService.tambahOpd(opd);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.id())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("delete/{kodeOpd}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("kodeOpd") String kodeOpd) {
        opdService.hapusOpd(kodeOpd);
    }
}
