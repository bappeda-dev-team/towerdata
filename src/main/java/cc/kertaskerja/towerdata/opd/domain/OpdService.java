package cc.kertaskerja.towerdata.opd.domain;

import cc.kertaskerja.towerdata.opd.domain.exception.OpdNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class OpdService {
    private OpdRepository opdRepository;

    public OpdService(OpdRepository opdRepository) {
        this.opdRepository = opdRepository;
    }

    public Page<Opd> cariOpd(String kodeOpd, String namaOpd, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return opdRepository.findByKodeOpdContainingIgnoreCaseAndNamaOpdContainingIgnoreCase(
                kodeOpd, namaOpd, pageable
        );
    }

    public Page<Opd> getDataByPenunjangFilter(Boolean penunjangFilter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (penunjangFilter == null) {
            return opdRepository.findAll(pageable);
        } else {
            return opdRepository.findByPenunjang(penunjangFilter, pageable);
        }
    }

    public List<Opd> findAll() {
        return StreamSupport.stream(opdRepository.findAll().spliterator(), false)
                .toList();
    }

    public Opd detailOpdByKodeOpd(String kodeOpd) {
        return opdRepository.findByKodeOpd(kodeOpd)
                .orElseThrow(() -> new OpdNotFoundException(kodeOpd));
    }

    public Opd tambahOpd(Opd opd) {
        return opdRepository.save(opd);
    }

    public Opd ubahOpd(String kodeOpd, Opd opd) {
        if (!opdRepository.existsByKodeOpd(kodeOpd)) {
            throw new OpdNotFoundException(kodeOpd);
        }

        return opdRepository.save(opd);
    }

    public void hapusOpd(String kodeOpd) {
        if (!opdRepository.existsByKodeOpd(kodeOpd)) {
            throw new OpdNotFoundException(kodeOpd);
        }

        opdRepository.deleteByKodeOpd(kodeOpd);
    }
}
