package net.helmi.backendstudentsspring.web;

import net.helmi.backendstudentsspring.entites.Payment;
import net.helmi.backendstudentsspring.entites.PaymentStatus;
import net.helmi.backendstudentsspring.entites.PaymentType;
import net.helmi.backendstudentsspring.entites.Student;
import net.helmi.backendstudentsspring.repository.PaymentRepository;
import net.helmi.backendstudentsspring.repository.StudentRepository;
import net.helmi.backendstudentsspring.service.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
//@CrossOrigin("*")
public class StudentRestController {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
    private PaymentService paymentService;

//    private PaymentService paymentService;

    public StudentRestController(StudentRepository studentRepository, PaymentRepository paymentRepository, PaymentService paymentService) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }

    @GetMapping(path = "/students")
    public List<Student> allStudents(){
        return studentRepository.findAll();
    }
    @GetMapping("/students/{code}")
    public Student getStudentByCode(@PathVariable String code){
        return studentRepository.findByCode(code);
    }
    @GetMapping(path = "/studentsByProgram")
    public List<Student> studentsByProgram(@RequestParam String programId){
        return studentRepository.findByProgramId(programId);
    }

    @GetMapping("/payments")
    public List<Payment> allPayments(){
        return paymentRepository.findAll();
    }
    @GetMapping("/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id){
        return paymentRepository.findById(id).get();
    }
    @GetMapping("/students/{code}/payments")
    public List<Payment> paymentsByStudentCode(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }
    @GetMapping("/paymentsByStatus")
    public List<Payment> paymentsByStaus(@RequestParam PaymentStatus status){
        return paymentRepository.findByStatus(status);
    }
    @PutMapping("/payments/{paymentId}/updateStatus")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status, @PathVariable Long paymentId){
        return paymentService.updatePaymentStatus(status,paymentId);
    }

    /// stocker un fichoer dans un path
    @PostMapping(path="/payments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file, double amount, PaymentType type,
                               LocalDate date, String studentCode) throws IOException {
        return paymentService.savePayment(file,amount,type,date,studentCode);

    }

    @GetMapping(path="payments/{id}/file",produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long id) throws IOException {
        return paymentService.getPaymentFile(id);

    }
}