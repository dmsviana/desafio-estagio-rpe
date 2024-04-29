package tech.rpe.desafioestagio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.rpe.desafioestagio.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    Customer findByCpf(final String cpf);
}
