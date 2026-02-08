package cc.kertaskerja.towerdata.kegiatan.domain;

import cc.kertaskerja.towerdata.kegiatan.domain.exception.KegiatanNotFoundException;
import cc.kertaskerja.towerdata.kegiatan.web.KegiatanRequest;

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
                .orElseThrow(() -> new KegiatanNotFoundException(id));
    }

    public Kegiatan tambahKegiatan(KegiatanRequest request) {
        Kegiatan kegiatan = Kegiatan.of(
                request.kodeKegiatan(),
                request.namaKegiatan(),
                request.kodePemda(),
                request.penunjang()
        );

        return kegiatanRepository.save(kegiatan);
    }

    public Kegiatan ubahKegiatan(Long id, KegiatanRequest request) {
        Kegiatan existingKegiatan = detailKegiatan(id);

        Kegiatan kegiatan = new Kegiatan(
                existingKegiatan.id(),
                request.kodeKegiatan(),
                request.namaKegiatan(),
                request.kodePemda(),
                request.penunjang(),
                existingKegiatan.createdDate(),
                null
        );

        return kegiatanRepository.save(kegiatan);
    }

    public void hapusKegiatan(Long id) {
        if (!kegiatanRepository.existsById(id)) {
            throw new KegiatanNotFoundException(id);
        }

        kegiatanRepository.deleteById(id);
    }
}
