package com.ges.exerciceservice;

import com.ges.exerciceservice.config.InitialData;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@SpringBootApplication
@EnableFeignClients
public class ExerciceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExerciceServiceApplication.class, args);
	}

	@Bean
	public RuntimeWiringConfigurer runtimeWiringConfigurer() {
		return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.Date);
	}
	@Bean
	CommandLineRunner start(){
		return args -> {
			InitialData.creeExercice();
			InitialData.creeSaisirJournaux();
			InitialData.creeJounal();
		};
	};


}
