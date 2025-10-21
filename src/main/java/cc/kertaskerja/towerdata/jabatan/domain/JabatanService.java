package cc.kertaskerja.towerdata.jabatan.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cc.kertaskerja.towerdata.jabatan.domain.exception.JabatanNotFoundException;

@Service
public class JabatanService {
    private final JabatanRepository jabatanRepository;

    public JabatanService(JabatanRepository jabatanRepository) {
        this.jabatanRepository = jabatanRepository;
    }

    public Page<Jabatan> cariJabatan(String kodeJabatan, String namaJabatan, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return jabatanRepository.findByKodeJabatanContainingIgnoreCaseAndNamaJabatanContainingIgnoreCase(
                kodeJabatan, namaJabatan, pageable
        );
    }

    public Page<Jabatan> getDataByPenunjangFilter(Boolean penunjangFilter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (penunjangFilter == null) {
            return jabatanRepository.findAll(pageable);
        } else {
            return jabatanRepository.findByPenunjang(penunjangFilter, pageable);
        }
    }

    public Jabatan detailJabatan(Long id) {
        return jabatanRepository.findById(id)
                .orElseThrow(() -> new JabatanNotFoundException(id));
    }

    public Jabatan tambahJabatan(Jabatan jabatan) {
        return jabatanRepository.save(jabatan);
    }

    public Jabatan ubahJabatan(Long id, Jabatan jabatan) {
        if (!jabatanRepository.existsById(id)) {
            throw new JabatanNotFoundException(id);
        }

        return jabatanRepository.save(jabatan);
    }

    public void hapusJabatan(Long id) {
        if (!jabatanRepository.existsById(id)) {
            throw new JabatanNotFoundException(id);
        }

        jabatanRepository.deleteById(id);
    }
}
