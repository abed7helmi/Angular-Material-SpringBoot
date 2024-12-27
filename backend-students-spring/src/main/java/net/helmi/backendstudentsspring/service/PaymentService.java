package net.helmi.backendstudentsspring.service;

import jakarta.transaction.Transactional;
import net.helmi.backendstudentsspring.entites.Payment;
import net.helmi.backendstudentsspring.entites.PaymentStatus;
import net.helmi.backendstudentsspring.entites.PaymentType;
import net.helmi.backendstudentsspring.entites.Student;
import net.helmi.backendstudentsspring.repository.PaymentRepository;
import net.helmi.backendstudentsspring.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.time.LocalDate;

@Service
@Transactional
public class PaymentService {

    private PaymentRepository paymentRepository;
    private StudentRepository studentRepository;

    public PaymentService(PaymentRepository paymentRepository, StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }

    public Payment savePayment(MultipartFile file, double amount, PaymentType type,
                               LocalDate date, String studentCode) throws IOException {
        Path folderPath = Paths.get(System.getProperty("user.home"),"enset-students","payments"); // definir un Path ( une hier des dosssiers)
        // si ce path n'existe pas
        if(!Files.exists(folderPath)){
            Files.createDirectories(folderPath); // créer le path (un dossier)
        }
        String fileName = UUID.randomUUID().toString(); // generer un nom alea
        Path filePath = Paths.get(System.getProperty("user.home"),"enset-students","payments",fileName+".pdf");
        Files.copy(file.getInputStream(), filePath); // enregistrer le fichier dans le path
        Student student = studentRepository.findByCode(studentCode);
        Payment payment=Payment.builder()
                .type(type)
                .status(PaymentStatus.CREATED)
                .date(date)
                .student(student)
                .amount(amount)
                .file(filePath.toUri().toString())
                .build();
        return paymentRepository.save(payment);

    }

    public byte[] getPaymentFile(Long id) throws IOException {
        Payment payment = paymentRepository.findById(id).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));

    }

    public Payment updatePaymentStatus(PaymentStatus status, Long paymentId){
        Payment payment = paymentRepository.findById(paymentId).get();
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }
}
