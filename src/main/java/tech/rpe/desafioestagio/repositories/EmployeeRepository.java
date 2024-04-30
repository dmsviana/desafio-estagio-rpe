package tech.rpe.desafioestagio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.rpe.desafioestagio.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Employee findByCpf(final String cpf);
}
