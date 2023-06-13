package com.ges.exerciceservice;

import com.base.basemodel.dto.PlanComptableDTOKafka;
import com.base.basemodel.dto.SocieteDTOKafka;
import graphql.scalars.ExtendedScalars;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
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
//			InitialData.creeExercice();
//			InitialData.creeSaisirJournaux();
//			InitialData.creeJounal();

		};
	};

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {

		UserDetails user = User.withDefaultPasswordEncoder() // (1)
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		UserDetails admin = User.withDefaultPasswordEncoder() // (2)
				.username("admin")
				.password("password")
				.roles("USER","ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user, admin); // (3)
	}
	@Bean
	public RedisTemplate<String, SocieteDTOKafka> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, SocieteDTOKafka> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		RedisCacheManager cacheManager = RedisCacheManager.create(connectionFactory);
		return cacheManager;
	}
	@Bean
	public RedisTemplate<String, PlanComptableDTOKafka> redisTemplatePlan(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, PlanComptableDTOKafka> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}

}
