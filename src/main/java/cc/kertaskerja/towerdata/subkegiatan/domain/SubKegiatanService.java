package cc.kertaskerja.towerdata.subkegiatan.domain;

import cc.kertaskerja.towerdata.subkegiatan.domain.exception.SubKegiatanNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubKegiatanService {
    private final SubKegiatanRepository subKegiatanRepository;

    public SubKegiatanService(SubKegiatanRepository subKegiatanRepository) {
        this.subKegiatanRepository = subKegiatanRepository;
    }

    public List<SubKegiatan> semuaSubKegiatan() {
        return subKegiatanRepository.findAll(Pageable.unpaged()).getContent();
    }
    
    public Page<SubKegiatan> cariSubKegiatan(String kodeSubKegiatan, String namaSubKegiatan, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return subKegiatanRepository.findByKodeSubKegiatanContainingIgnoreCaseAndNamaSubKegiatanContainingIgnoreCase(
                kodeSubKegiatan, namaSubKegiatan, pageable
        );
    }

    public SubKegiatan detailSubKegiatan(String kodeSubKegiatan) {
        return subKegiatanRepository.findByKodeSubKegiatan(kodeSubKegiatan)
                .orElseThrow(() -> new SubKegiatanNotFoundException(kodeSubKegiatan));
    }

    public SubKegiatan tambahSubKegiatan(SubKegiatan subKegiatan) {
        return subKegiatanRepository.save(subKegiatan);
    }

    public SubKegiatan ubahSubKegiatan(String kodeSubKegiatan, SubKegiatan subKegiatan) {
        if (!subKegiatanRepository.existsByKodeSubKegiatan(kodeSubKegiatan)) {
            throw new SubKegiatanNotFoundException(kodeSubKegiatan);
        }

        return subKegiatanRepository.save(subKegiatan);
    }

    public void hapusSubKegiatan(String kodeSubKegiatan) {
        if (!subKegiatanRepository.existsByKodeSubKegiatan(kodeSubKegiatan)) {
            throw new SubKegiatanNotFoundException(kodeSubKegiatan);
        }

        subKegiatanRepository.deleteByKodeSubKegiatan(kodeSubKegiatan);
    }

}
