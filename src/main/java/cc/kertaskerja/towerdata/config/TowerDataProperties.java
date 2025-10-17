package cc.kertaskerja.towerdata.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kertaskerja")
public class TowerDataProperties {
	private String status;
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
        this.status = status;
    }
}
