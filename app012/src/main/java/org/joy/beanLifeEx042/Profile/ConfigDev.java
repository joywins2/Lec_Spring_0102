package org.joy.beanLifeEx042.Profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("develop")
public class ConfigDev {
	@Bean
	public ProfileEx profileEx(){
		ProfileEx prof = new ProfileEx();
		prof.setIp("localhost");
		prof.setPort("9090");
		return prof;
	}
}
