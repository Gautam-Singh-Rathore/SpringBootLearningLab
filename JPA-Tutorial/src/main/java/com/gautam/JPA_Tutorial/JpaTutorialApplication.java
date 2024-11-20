package com.gautam.JPA_Tutorial;

import com.gautam.JPA_Tutorial.models.Author;
import com.gautam.JPA_Tutorial.models.Video;
import com.gautam.JPA_Tutorial.repositories.AuthorRepository;
import com.gautam.JPA_Tutorial.repositories.VideoRepository;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Modifying;

@SpringBootApplication
public class JpaTutorialApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(JpaTutorialApplication.class, args);
	}

	@Bean // Beans should always be public
	public CommandLineRunner commandLineRunner(
			AuthorRepository repository,
			VideoRepository videoRepository
	){
		return args->{
			/*var author = Author.builder()
					.firstName("Gautam")
					.lastName("Singh")
					.email("test@gmail.com")
					.age(34)
					.build();

			repository.save(author);*/

			/*var video = Video.builder()
					.name("abc")
					.length(5)
					.build();
			videoRepository.save(video);
			*/


			for(int i=0 ; i<50 ; i++){
				Faker faker = new Faker();
				var author = Author.builder()
						.firstName(faker.name().firstName())
						.lastName(faker.name().lastName())
						.age(faker.number().numberBetween(19 , 67))
						.email("test"+i+"@gmail.com")
						.build();

				repository.save(author);
			};

			// Update Author with ID=1
			var author = Author.builder()
					.id(1)
					.firstName("Gautam Singh ")
					.lastName("Rathore")
					.age(19)
					.email("gautam@gmail.com")
					.build();

			//repository.save(author);

			// update Author a set a.age = 22 where a.id=1
			repository.updateAuthor(22,1);

			//repository.updateAllAuthorsAges(99);

			// find by named query
			repository.findByNamedQuery(50).forEach(System.out::println);


			// update with named query
			repository.updateByNamedQuery(12);
		};
	}

}
