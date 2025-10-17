package cc.kertaskerja.towerdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TowerdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(TowerdataApplication.class, args);
	}

}
