package com.tpe;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.NamingConventions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class CarServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setFieldMatchingEnabled(true) // !!! filed lari otomatik olarak eslestir
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE) // !!! model mapper'ın yalnızca sınıfın özel alanlarına erişmesine izin verir.
				.setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR) ; // !!! ModelMapper, kaynak sınıfta "get" ve "set" önekleri ile başlayan
		// methodları arayarak, hangi alanların eşleştirileceğini belirler. Bu sayede, ModelMapper, kaynak sınıfın
		// adlandırma kurallarına uygun olarak, hedef sınıftaki alanları belirler ve bu alanlar arasında
		// veri eşleştirmesi yapar.

		return modelMapper;

	}

}