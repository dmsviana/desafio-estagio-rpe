package tech.rpe.desafioestagio.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.rpe.desafioestagio.controllers.CustomerController;
import tech.rpe.desafioestagio.controllers.dtos.CustomerDto;
import tech.rpe.desafioestagio.exceptions.CustomerNotFoundException;
import tech.rpe.desafioestagio.services.implementation.CustomerServiceImpl;
import tech.rpe.desafioestagio.utils.CustomerUtil;
import tech.rpe.desafioestagio.utils.Utils;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTest {

    public static final String BASE_URL = "/api/v1/customers";
    public static final String CUSTOMER_ID = BASE_URL+"/"+UUID.randomUUID();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceImpl customerService;

    @Test
    void findCustomer_WithValidId_ShouldReturnCustomer() throws Exception {
       when(customerService.findById(anyString())).thenReturn(CustomerUtil.createExpectedResponseDefault());

        var result =
                mockMvc.perform(get(CUSTOMER_ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        var response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    void findCustomer_WithInvalidId_ShouldReturnCustomerNotFoundException() throws Exception {
        when(customerService.findById(anyString())).thenThrow(new CustomerNotFoundException("Cliente não encontrado."));

        var result =
                mockMvc.perform(get(CUSTOMER_ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        var response = result.getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void createCustomer_WithValidData_ShouldReturnCustomer() throws Exception {
       var request = CustomerUtil.createCustomerDefault();
       var response = CustomerUtil.createExpectedResponseDefault();
       when(customerService.create(any(CustomerDto.class))).thenReturn(response);

       String requestBody = Utils.mapToString(request);
       var result =
                mockMvc.perform(post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                        .andReturn();
        var resultResponse = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), resultResponse.getStatus());

    }

    @Test
    void createCustomer_WithInvalidData_ShouldReturnMethodArgumentNotValidException() throws Exception {
        var request = new CustomerDto(
                UUID.randomUUID().toString(),
                "Diogo",
                "10380036444",
                "83996586204",
                null,
                null
        );
        var response = CustomerUtil.createExpectedResponseDefault();
        when(customerService.create(any(CustomerDto.class))).thenReturn(response);

        String requestBody = Utils.mapToString(request);
        var result =
                mockMvc.perform(post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                        .andReturn();
        var resultResponse = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), resultResponse.getStatus());
    }

    @Test
    void updateCustomer_WithValidId_ShouldReturnCustomer() throws Exception {
        var request = CustomerUtil.createCustomerDefault();
        var response = CustomerUtil.createExpectedResponseDefault();
        when(customerService.update(anyString(), any(CustomerDto.class))).thenReturn(response);

        String requestBody = Utils.mapToString(request);
        var result =
                mockMvc.perform(put(CUSTOMER_ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                        .andReturn();
        var resultResponse = result.getResponse();
        assertEquals(HttpStatus.OK.value(), resultResponse.getStatus());
    }

    @Test
    void updateCustomer_WithInvalidId_ShouldReturnCustomerNotFoundException() throws Exception {
        var request = CustomerUtil.createCustomerDefault();
        when(customerService.update(anyString(), any(CustomerDto.class))).thenThrow(new CustomerNotFoundException("Cliente não encontrado."));

        String requestBody = Utils.mapToString(request);
        var result =
                mockMvc.perform(put(CUSTOMER_ID)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                        .andReturn();
        var resultResponse = result.getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), resultResponse.getStatus());
    }

    @Test
    void deleteCustomer_WithValidId_ShouldReturnNoContent() throws Exception {
        doNothing().when(customerService).delete(anyString());

        var result =
                mockMvc.perform(delete(CUSTOMER_ID)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        var resultResponse = result.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), resultResponse.getStatus());
    }

    @Test
    void deleteCustomer_WithInvalidId_ShouldReturnCustomerNotFoundException() throws Exception {
        doThrow(new CustomerNotFoundException("Cliente não encontrado.")).when(customerService).delete(anyString());

        var result =
                mockMvc.perform(delete(CUSTOMER_ID)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        var resultResponse = result.getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), resultResponse.getStatus());
    }
}
