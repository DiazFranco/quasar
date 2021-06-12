package challenge.fire.quasar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class QuasarApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuasarApplication.class, args);
	}

}


//instancia: ssh -i "quasar-aws.pem" ubuntu@ec2-52-3-246-162.compute-1.amazonaws.com

// levantar proyecto: java -jar quasar-0.0.1-SNAPSHOT.jar