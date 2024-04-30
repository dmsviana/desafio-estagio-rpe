package tech.rpe.desafioestagio.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.rpe.desafioestagio.controllers.dtos.CustomerDto;
import tech.rpe.desafioestagio.exceptions.CustomerAlreadyExistsException;
import tech.rpe.desafioestagio.exceptions.CustomerNotFoundException;
import tech.rpe.desafioestagio.model.Customer;
import tech.rpe.desafioestagio.model.mapper.CustomerMapper;
import tech.rpe.desafioestagio.repositories.CustomerRepository;
import tech.rpe.desafioestagio.services.implementation.CustomerServiceImpl;
import tech.rpe.desafioestagio.utils.CustomerUtil;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {


    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void findCustomer_WithValidId_ShouldReturnCustomer() {

        Customer customer = CustomerUtil.createCustomerDefault();

        CustomerDto customerResponseDto = CustomerUtil.createExpectedResponseDefault();

        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        CustomerDto response = customerService.findById(customer.getId());

        assertAll("response",
                () -> assertEquals(customerResponseDto.id(), response.id()),
                () -> assertEquals(customerResponseDto.name(), response.name()),
                () -> assertEquals(customerResponseDto.cpf(), response.cpf()),
                () -> assertEquals(customerResponseDto.phoneNumber(), response.phoneNumber()),
                () -> assertEquals(customerResponseDto.address(), response.address())
        );

        verify(customerRepository).findById(customer.getId());
    }

    @Test
    void findCustomer_WithInvalidId_ShouldReturnCustomerNotFoundException() {

            when(customerRepository.findById("123")).thenReturn(Optional.empty());
            assertThrows(CustomerNotFoundException.class, () -> customerService.findById("123"));
            verify(customerRepository).findById("123");
    }

    @Test
    void createCustomer_WithValidData_ShouldReturnCustomer() {
        Customer customer = CustomerUtil.createCustomerDefault();

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDto expectedResponse = CustomerUtil.createExpectedResponseDefault();
        CustomerDto response = customerService.create(CustomerMapper.toDTO(customer));

        assertAll("response",
                () -> assertEquals(expectedResponse.id(), response.id()),
                () -> assertEquals(expectedResponse.name(), response.name()),
                () -> assertEquals(expectedResponse.cpf(), response.cpf()),
                () -> assertEquals(expectedResponse.phoneNumber(), response.phoneNumber()),
                () -> assertEquals(expectedResponse.address(), response.address())
        );

        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void createCustomer_WithExistingCpf_ShouldReturnCustomerAlreadyExistsException() {
        Customer customer = CustomerUtil.createCustomerDefault();

        when(customerRepository.findByCpf(any())).thenReturn(customer);

        assertThrows(CustomerAlreadyExistsException.class, () -> customerService.create(CustomerMapper.toDTO(customer)));
        verify(customerRepository, times(0)).save(any(Customer.class));
    }

    @Test
    void updateCustomer_WithValidData_ShouldReturnCustomer() {
        Customer customer = CustomerUtil.createCustomerDefault();

        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDto expectedResponse = CustomerUtil.createExpectedResponseDefault();
        CustomerDto response = customerService.update(customer.getId(), CustomerMapper.toDTO(customer));

        assertAll("response",
                () -> assertEquals(expectedResponse.id(), response.id()),
                () -> assertEquals(expectedResponse.name(), response.name()),
                () -> assertEquals(expectedResponse.cpf(), response.cpf()),
                () -> assertEquals(expectedResponse.phoneNumber(), response.phoneNumber()),
                () -> assertEquals(expectedResponse.address(), response.address())
        );

        verify(customerRepository).findById(customer.getId());
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void updateCustomer_WithInvalidId_ShouldReturnCustomerNotFoundException() {
        Customer customer = CustomerUtil.createCustomerDefault();

        when(customerRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.update(customer.getId(), CustomerMapper.toDTO(customer)));
        verify(customerRepository).findById(customer.getId());
        verify(customerRepository, times(0)).save(any(Customer.class));
    }

    @Test
    void deleteCustomer_WithValidId_ShouldNotThrowAnyException() {
        Customer customer = CustomerUtil.createCustomerDefault();

        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        doNothing().when(customerRepository).delete(any(Customer.class));

        assertDoesNotThrow(() -> customerService.delete(customer.getId()));
        verify(customerRepository).findById(customer.getId());
        verify(customerRepository).delete(any(Customer.class));
    }

    @Test
    void deleteCustomer_WithInvalidId_ShouldThrowCustomerNotFoundException() {
        when(customerRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.delete("123"));
        verify(customerRepository).findById("123");
        verify(customerRepository, times(0)).delete(any(Customer.class));
    }

    @Test
    void findAllCustomers_ShouldReturnListOfCustomers() {
        List<Customer> customers = List.of(CustomerUtil.createCustomerDefault());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDto> response = customerService.findAll();

        assertEquals(1, response.size());
        verify(customerRepository).findAll();
    }

    @Test
    void findByCpf_WithValidCpf_ShouldReturnCustomer() {
        Customer customer = CustomerUtil.createCustomerDefault();

        when(customerRepository.findByCpf(any())).thenReturn(customer);

        CustomerDto response = customerService.findByCpf(customer.getCpf());

        assertEquals(customer.getCpf(), response.cpf());
        verify(customerRepository).findByCpf(customer.getCpf());
    }

    @Test
    void findByCpf_WithInvalidCpf_ShouldThrowCustomerNotFoundException() {
        when(customerRepository.findByCpf(any())).thenReturn(null);

        assertThrows(CustomerNotFoundException.class, () -> customerService.findByCpf("123"));
        verify(customerRepository).findByCpf("123");
    }

}



