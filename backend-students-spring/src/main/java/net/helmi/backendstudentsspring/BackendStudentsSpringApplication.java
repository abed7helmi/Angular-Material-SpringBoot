package net.helmi.backendstudentsspring;

import net.helmi.backendstudentsspring.entites.Payment;
import net.helmi.backendstudentsspring.entites.PaymentStatus;
import net.helmi.backendstudentsspring.entites.PaymentType;
import net.helmi.backendstudentsspring.entites.Student;
import net.helmi.backendstudentsspring.repository.PaymentRepository;
import net.helmi.backendstudentsspring.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class BackendStudentsSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendStudentsSpringApplication.class, args);
	}



	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository, PaymentRepository paymentRepository){
		return args -> {
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.code("112233").firstName("Mohamed").programId("GLSID").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.code("112244").firstName("Imane").programId("GLSID").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.code("112255").firstName("Aymane").programId("BDCC").build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.code("112266").firstName("Lobna").programId("BDCC").build());

			PaymentType[] paymentTypes = PaymentType.values();
			Random random=new Random();
			studentRepository.findAll().forEach(st->{
				for (int i = 0; i <10 ; i++) {
					int index = random.nextInt(paymentTypes.length);
					Payment payment = Payment.builder()
							.date(LocalDate.now())
							.amount(1000+(int)(Math.random()*20000))
							.type(paymentTypes[index])
							.status(PaymentStatus.CREATED)
							.student(st)
							.build();
					paymentRepository.save(payment);
				}
			});
		};
	}

}
