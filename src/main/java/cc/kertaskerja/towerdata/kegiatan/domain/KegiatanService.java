package cc.kertaskerja.towerdata.kegiatan.domain;

import cc.kertaskerja.towerdata.kegiatan.domain.exception.KegiatanNotFoundException;
import cc.kertaskerja.towerdata.subkegiatan.domain.exception.SubKegiatanAlreadyExistException;
import cc.kertaskerja.towerdata.subkegiatan.domain.exception.SubKegiatanNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class KegiatanService {
    private final KegiatanRepository kegiatanRepository;

    public KegiatanService(KegiatanRepository kegiatanRepository) {
        this.kegiatanRepository = kegiatanRepository;
    }
    
    public Page<Kegiatan> cariKegiatan(String kodeKegiatan, String namaKegiatan, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return kegiatanRepository.findByKodeKegiatanContainingIgnoreCaseAndNamaKegiatanContainingIgnoreCase(
                kodeKegiatan, namaKegiatan, pageable
        );
    }
    
    public Page<Kegiatan> getDataByPenunjangFilter(Boolean penunjangFilter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (penunjangFilter == null) {
            return kegiatanRepository.findAll(pageable);
        } else {
            return kegiatanRepository.findByPenunjang(penunjangFilter, pageable);
        }
    }

    public Kegiatan detailKegiatan(Long id) {
        return kegiatanRepository.findById(id)
                .orElseThrow(() -> new SubKegiatanAlreadyExistException(id));
    }

    public Kegiatan tambahKegiatan(Kegiatan kegiatan) {
        return kegiatanRepository.save(kegiatan);
    }

    public Kegiatan ubahKegiatan(Long id, Kegiatan kegiatan) {
        if (!kegiatanRepository.existsById(id)) {
            throw new SubKegiatanNotFoundException(id);
        }

        return kegiatanRepository.save(kegiatan);
    }

    public void hapusKegiatan(Long id) {
        if (!kegiatanRepository.existsById(id)) {
            throw new KegiatanNotFoundException(id);
        }

        kegiatanRepository.deleteById(id);
    }
}
