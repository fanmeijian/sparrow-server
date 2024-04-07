package cn.sparrowmini.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan({"cn.sparrowmini.org","cn.sparrowmini.pem","cn.sparrowmini.common","cn.sparrowmini.portal","cn.sparrowmini.file","cn.sparrowmini.server"})
@ComponentScan("cn.sparrowmini")
@EnableJpaRepositories({"cn.sparrowmini","cn.sparrowmini.org"})
@SpringBootApplication
public class SparrowApplication {

	public static void main(String[] args) {
		SpringApplication.run(SparrowApplication.class, args);

	}

}
