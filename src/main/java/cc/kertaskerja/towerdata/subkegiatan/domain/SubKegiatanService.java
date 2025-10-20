package cc.kertaskerja.towerdata.subkegiatan.domain;

import cc.kertaskerja.towerdata.subkegiatan.domain.exception.SubKegiatanNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubKegiatanService {
    private final SubKegiatanRepository subKegiatanRepository;

    public SubKegiatanService(SubKegiatanRepository subKegiatanRepository) {
        this.subKegiatanRepository = subKegiatanRepository;
    }

    public Page<SubKegiatan> cariSubKegiatan(String kodeSubKegiatan, String namaSubKegiatan, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return subKegiatanRepository.findByKodeSubKegiatanContainingIgnoreCaseAndNamaSubKegiatanContainingIgnoreCase(
                kodeSubKegiatan, namaSubKegiatan, pageable
        );
    }

    public Page<SubKegiatan> getDataByPenunjangFilter(Boolean penunjangFilter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (penunjangFilter == null) {
            return subKegiatanRepository.findAll(pageable);
        } else {
            return subKegiatanRepository.findByPenunjang(penunjangFilter, pageable);
        }
    }

    public SubKegiatan detailSubKegiatan(Long id) {
        return subKegiatanRepository.findById(id)
                .orElseThrow(() -> new SubKegiatanNotFoundException(id));
    }

    public SubKegiatan tambahSubKegiatan(SubKegiatan subKegiatan) {
        return subKegiatanRepository.save(subKegiatan);
    }

    public SubKegiatan ubahSubKegiatan(Long id, SubKegiatan subKegiatan) {
        if (!subKegiatanRepository.existsById(id)) {
            throw new SubKegiatanNotFoundException(id);
        }

        return subKegiatanRepository.save(subKegiatan);
    }

    public void hapusSubKegiatan(Long id) {
        if (!subKegiatanRepository.existsById(id)) {
            throw new SubKegiatanNotFoundException(id);
        }

        subKegiatanRepository.deleteById(id);
    }

}
