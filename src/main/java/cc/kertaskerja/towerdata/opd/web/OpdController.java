package cc.kertaskerja.towerdata.opd.web;

import cc.kertaskerja.towerdata.opd.domain.Opd;
import cc.kertaskerja.towerdata.opd.domain.OpdService;
import cc.kertaskerja.towerdata.opd.web.response.OpdSearchResponse;
import cc.kertaskerja.towerdata.opd.web.response.OpdSelectionResponse;
import cc.kertaskerja.towerdata.pegawai.domain.Pegawai;
import cc.kertaskerja.towerdata.pegawai.domain.PegawaiService;
import cc.kertaskerja.towerdata.pegawai.web.response.PegawaiResponse;
import cc.kertaskerja.towerdata.bidangurusan.domain.BidangUrusanService;
import cc.kertaskerja.towerdata.bidangurusan.web.OpdBidangUrusanResponse;
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
     * @param id
     * @return satu data opd
     */
    @GetMapping("detail/{id}")
    public Opd getById(@PathVariable("id") Long id) {
        return opdService.detailOpd(id);
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
                        opd.penunjang()
                ))
                .toList();
    }

    @GetMapping("detail/penunjang/cari")
    public List<OpdSearchResponse> getPenunjangSearchData(
            @RequestParam(value = "penunjang", required = false) Boolean penunjangFilter,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<Opd> opds = opdService.getDataByPenunjangFilter(penunjangFilter, page, size);

        return opds.stream()
                .map(opd -> new OpdSearchResponse(
                        opd.kodeOpd(),
                        opd.namaOpd(),
                        opd.penunjang()
                ))
                .toList();
    }

    /**
     * pilih opd dropdown
     * @return list semua data opd
     */
    @GetMapping("detail/pilihOpd")
    public List<OpdSelectionResponse> getOpdSelection() {
        return opdService.findAll()
                .stream()
                .map(opd -> new OpdSelectionResponse(
                        opd.id(),
                        opd.kodeOpd(),
                        opd.namaOpd()
                ))
                .toList();
    }

    @GetMapping("detail/{opdId}/pegawai/kode/{kodePegawai}")
    public List<PegawaiResponse> getPegawaiByKodePegawaiInOpd(
            @PathVariable("opdId") Long opdId,
            @PathVariable("kodePegawai") String kodePegawai
    ) {
        List<Pegawai> pegawais = pegawaiService.getPegawaiByKodePegawai(kodePegawai);
        String namaOpd = opdService.detailOpd(opdId).namaOpd();

        return pegawais.stream()
                .filter(pegawai -> pegawai.opdId().equals(opdId))
                .map(pegawai -> new PegawaiResponse(
                        pegawai.kodePegawai(),
                        pegawai.namaPegawai(),
                        pegawai.penunjang(),
                        pegawai.opdId(),
                        namaOpd,
                        pegawai.namaRolePegawai()
                ))
                .toList();
    }

    /**
     * pilih bidang urusan berdasarkan opd
     * @return list bidang urusan berdasarkan opd
     */
    @GetMapping("detail/{opdId}/bidangurusan/pilih")
    public List<OpdBidangUrusanResponse> getBidangUrusanSelectionByOpd(
            @PathVariable("opdId") Long opdId,
            @RequestParam(value = "penunjang", required = false) Boolean penunjangFilter,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        // Validate OPD exists
        opdService.detailOpd(opdId);
        
        var bidangUrusanPage = (penunjangFilter != null) 
                ? bidangUrusanService.getBidangUrusanByOpdIdAndPenunjang(opdId, penunjangFilter, page, size)
                : bidangUrusanService.getBidangUrusanByOpdId(opdId, page, size);

        return bidangUrusanPage.stream()
                .map(bidangUrusan -> new OpdBidangUrusanResponse(
                        bidangUrusan.id(),
                        bidangUrusan.kodeBidangUrusan(),
                        bidangUrusan.namaBidangUrusan()
                ))
                .toList();
    }

    @PutMapping("update/{id}")
    public Opd put(@PathVariable("id") Long id, @Valid @RequestBody OpdRequest request) {
        // Ambil data opd yang sudah dibuat
        Opd existingOpd = opdService.detailOpd(id);

        Opd opd = new Opd(
                id,
                request.kodeOpd(),
                request.namaOpd(),
                request.kodePemda(),
                request.penunjang(),
                existingOpd.createdDate(),
                null
        );

        return opdService.ubahOpd(id, opd);
    }

    @PostMapping
    public ResponseEntity<Opd> post(@Valid @RequestBody OpdRequest request) {
        Opd opd = Opd.of(
                request.kodeOpd(),
                request.namaOpd(),
                request.kodePemda(),
                request.penunjang()
        );
        Opd saved = opdService.tambahOpd(opd);
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
        opdService.hapusOpd(id);
    }
}
