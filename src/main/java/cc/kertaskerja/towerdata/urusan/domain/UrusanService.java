package cc.kertaskerja.towerdata.urusan.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cc.kertaskerja.towerdata.urusan.domain.exception.UrusanNotFoundException;

@Service
public class UrusanService {
	private final UrusanRepository urusanRepository;
	
	public UrusanService(UrusanRepository urusanRepository) {
		this.urusanRepository = urusanRepository;
	}
	
	public Page<Urusan> cariUrusan(String kodeUrusan, String namaUrusan, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return urusanRepository.findByKodeUrusanContainingIgnoreCaseAndNamaUrusanContainingIgnoreCase(
                kodeUrusan, namaUrusan, pageable
        );
    }
	
	public Page<Urusan> getDataByPenunjangFilter(Boolean penunjangFilter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (penunjangFilter == null) {
            return urusanRepository.findAll(pageable);
        } else {
            return urusanRepository.findByPenunjang(penunjangFilter, pageable);
        }
    }
	
	public Urusan detailUrusan(Long id) {
        return urusanRepository.findById(id)
                .orElseThrow(() -> new UrusanNotFoundException(id));
    }
	
	public Urusan tambahUrusan(Urusan urusan) {
        return urusanRepository.save(urusan);
    }
	
	public Urusan ubahUrusan(Long id, Urusan urusan) {
        if (!urusanRepository.existsById(id)) {
            throw new UrusanNotFoundException(id);
        }

        return urusanRepository.save(urusan);
    }
	
	public void hapusUrusan(Long id) {
        if (!urusanRepository.existsById(id)) {
            throw new UrusanNotFoundException(id);
        }

        urusanRepository.deleteById(id);
    }
}
