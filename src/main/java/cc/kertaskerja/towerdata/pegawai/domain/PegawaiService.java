package cc.kertaskerja.towerdata.pegawai.domain;

import cc.kertaskerja.towerdata.pegawai.domain.exception.PegawaiNotFoundException;
import cc.kertaskerja.towerdata.opd.domain.Opd;
import cc.kertaskerja.towerdata.opd.domain.OpdRepository;
import cc.kertaskerja.towerdata.opd.domain.exception.OpdNotFoundException;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PegawaiService {
    private PegawaiRepository pegawaiRepository;
    private OpdRepository opdRepository;

    public PegawaiService(PegawaiRepository pegawaiRepository, OpdRepository opdRepository) {
        this.pegawaiRepository = pegawaiRepository;
        this.opdRepository = opdRepository;
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
    
    public List<Pegawai> getPegawaiByKodePegawai(String kodePegawai) {
        return pegawaiRepository.findByKodePegawai(kodePegawai);
    }

    public Pegawai detailPegawai(Long id) {
        return pegawaiRepository.findById(id)
                .orElseThrow(() -> new PegawaiNotFoundException(id));
    }

    public Pegawai tambahPegawai(Pegawai pegawai) {
        // Validasi OPD jika kodeOpd tidak null
        if (pegawai.kodeOpd() != null) {
            if (!opdRepository.existsByKodeOpd(pegawai.kodeOpd())) {
                throw new OpdNotFoundException(pegawai.kodeOpd());
            }
        }

        return pegawaiRepository.save(pegawai);
    }

    public Pegawai ubahPegawai(Long id, Pegawai pegawai) {
        if (!pegawaiRepository.existsById(id)) {
            throw new PegawaiNotFoundException(id);
        }

        // Validasi OPD jika kodeOpd tidak null
        if (pegawai.kodeOpd() != null) {
            if (!opdRepository.existsByKodeOpd(pegawai.kodeOpd())) {
                throw new OpdNotFoundException(pegawai.kodeOpd());
            }
        }

        return pegawaiRepository.save(pegawai);
    }

    public Page<Pegawai> getPegawaiByKodeOpd(String kodeOpd, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pegawaiRepository.findByKodeOpd(kodeOpd, pageable);
    }

    public void hapusPegawai(Long id) {
        if (!pegawaiRepository.existsById(id)) {
            throw new PegawaiNotFoundException(id);
        }

        pegawaiRepository.deleteById(id);
    }
}
