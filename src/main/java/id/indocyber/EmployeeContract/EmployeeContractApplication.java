package id.indocyber.EmployeeContract;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeContractApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

		dotenv.entries().forEach(entry -> {
			System.setProperty(entry.getKey(), entry.getValue());
			System.out.println("Setting property: " + entry.getKey() + "=" + entry.getValue());

		});

		SpringApplication.run(EmployeeContractApplication.class, args);
	}

}
