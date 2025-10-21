package cc.kertaskerja.towerdata.pegawai.domain;

import cc.kertaskerja.towerdata.pegawai.domain.exception.PegawaiNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PegawaiService {
    private PegawaiRepository pegawaiRepository;

    public PegawaiService(PegawaiRepository pegawaiRepository) {
        this.pegawaiRepository = pegawaiRepository;
    }

    public Page<Pegawai> cariPegawai(String kodePegawai, String namaPegawai, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pegawaiRepository.findByKodePegawaiContainingIgnoreCaseAndNamaPegawaiContainingIgnoreCase(
                kodePegawai, namaPegawai, pageable
        );
    }

    public Page<Pegawai> getDataByPenunjangFilter(Boolean penunjangFilter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (penunjangFilter == null) {
            return pegawaiRepository.findAll(pageable);
        } else {
            return pegawaiRepository.findByPenunjang(penunjangFilter, pageable);
        }
    }

    public Pegawai detailPegawai(Long id) {
        return pegawaiRepository.findById(id)
                .orElseThrow(() -> new PegawaiNotFoundException(id));
    }

    public Pegawai tambahPegawai(Pegawai pegawai) {
        return pegawaiRepository.save(pegawai);
    }

    public Pegawai ubahPegawai(Long id, Pegawai pegawai) {
        if (!pegawaiRepository.existsById(id)) {
            throw new PegawaiNotFoundException(id);
        }

        return pegawaiRepository.save(pegawai);
    }

    public void hapusPegawai(Long id) {
        if (!pegawaiRepository.existsById(id)) {
            throw new PegawaiNotFoundException(id);
        }

        pegawaiRepository.deleteById(id);
    }
}
