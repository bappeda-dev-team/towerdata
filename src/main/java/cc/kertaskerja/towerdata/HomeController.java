package cc.kertaskerja.towerdata;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.kertaskerja.towerdata.config.TowerDataProperties;

@RestController
public class HomeController {
	private final TowerDataProperties towerDataProperties;
	
	public HomeController(TowerDataProperties towerDataProperties) {
		this.towerDataProperties = towerDataProperties;
	}
	
	@GetMapping("/")
	public String getStatus() {
		return towerDataProperties.getStatus();
	}
}
