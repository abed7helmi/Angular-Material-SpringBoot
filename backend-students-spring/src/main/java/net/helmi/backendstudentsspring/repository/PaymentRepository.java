package net.helmi.backendstudentsspring.repository;

import net.helmi.backendstudentsspring.entites.Payment;
import net.helmi.backendstudentsspring.entites.PaymentStatus;
import net.helmi.backendstudentsspring.entites.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);
}
