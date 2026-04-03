package com.tdea.c2.mmb;

import org.springframework.boot.SpringApplication; //Permite arrancar la aplicación
import org.springframework.boot.autoconfigure.SpringBootApplication; //Configura automaticamente esta aplicación como ppal
import org.springframework.data.jpa.repository.config.EnableJpaAuditing; //Auditoria automática de JPA para por ej llenar la fecha automaticamente
import org.springframework.boot.persistence.autoconfigure.EntityScan; //Permite mapear las entity de otros paquetes
@SpringBootApplication
@EnableJpaAuditing
@EntityScan("com.tdea.c2.mmb.modelo")


public class MmbApplication {

	public static void main(String[] args) {
		//Permite la ejecución del SpringApplication
		SpringApplication.run(MmbApplication.class, args);
	}

}
