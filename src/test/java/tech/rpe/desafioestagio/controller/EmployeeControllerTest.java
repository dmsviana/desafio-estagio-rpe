package tech.rpe.desafioestagio.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.rpe.desafioestagio.controllers.EmployeeController;
import tech.rpe.desafioestagio.controllers.dtos.EmployeeDto;
import tech.rpe.desafioestagio.exceptions.EmployeeNotFoundException;
import tech.rpe.desafioestagio.services.implementation.EmployeeServiceImpl;
import tech.rpe.desafioestagio.utils.EmployeeUtil;
import tech.rpe.desafioestagio.utils.Utils;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {

    public static final String BASE_URL = "/api/v1/employees";
    public static final String EMPLOYEE_ID = BASE_URL+"/"+UUID.randomUUID();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServiceImpl employeeService;

    @Test
    void findEmployee_WithValidId_ShouldReturnEmployee() throws Exception {
        when(employeeService.findById(anyString())).thenReturn(EmployeeUtil.createExpectedResponseDefault());

        var result =
                mockMvc.perform(get(EMPLOYEE_ID)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        var response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    void findEmployee_WithInvalidId_ShouldReturnEmployeeNotFoundException() throws Exception {
        when(employeeService.findById(anyString())).thenThrow(new EmployeeNotFoundException("Funcionário não encontrado."));

        var result =
                mockMvc.perform(get(EMPLOYEE_ID)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        var response = result.getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void createEmployee_WithValidData_ShouldReturnEmployee() throws Exception {
        var request = EmployeeUtil.createEmployeeDefault();
        var response = EmployeeUtil.createExpectedResponseDefault();
        when(employeeService.create(any(EmployeeDto.class))).thenReturn(response);

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
    void createEmployee_WithInvalidData_ShouldReturnMethodArgumentNotValidException() throws Exception {
        var request = new EmployeeDto(
                UUID.randomUUID().toString(),
                "Diogo",
                "10380036444",
                "83996586204",
                null,
                null,
                null,
                null
        );
        var response = EmployeeUtil.createExpectedResponseDefault();
        when(employeeService.create(any(EmployeeDto.class))).thenReturn(response);

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
    void updateEmployee_WithValidId_ShouldReturnEmployee() throws Exception {
        var request = EmployeeUtil.createEmployeeDefault();
        var response = EmployeeUtil.createExpectedResponseDefault();
        when(employeeService.update(anyString(), any(EmployeeDto.class))).thenReturn(response);

        String requestBody = Utils.mapToString(request);
        var result =
                mockMvc.perform(put(EMPLOYEE_ID)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                        .andReturn();
        var resultResponse = result.getResponse();
        assertEquals(HttpStatus.OK.value(), resultResponse.getStatus());
    }

    @Test
    void updateEmployee_WithInvalidId_ShouldReturnEmployeeNotFoundException() throws Exception {
        var request = EmployeeUtil.createEmployeeDefault();
        when(employeeService.update(anyString(), any(EmployeeDto.class))).thenThrow(new EmployeeNotFoundException("Funcionário não encontrado."));

        String requestBody = Utils.mapToString(request);
        var result =
                mockMvc.perform(put(EMPLOYEE_ID)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                        .andReturn();
        var resultResponse = result.getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), resultResponse.getStatus());
    }

    @Test
    void deleteEmployee_WithValidId_ShouldReturnNoContent() throws Exception {
        doNothing().when(employeeService).delete(anyString());

        var result =
                mockMvc.perform(delete(EMPLOYEE_ID)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        var resultResponse = result.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), resultResponse.getStatus());
    }

    @Test
    void deleteEmployee_WithInvalidId_ShouldReturnEmployeeNotFoundException() throws Exception {
        doThrow(new EmployeeNotFoundException("Funcionário não encontrado.")).when(employeeService).delete(anyString());

        var result =
                mockMvc.perform(delete(EMPLOYEE_ID)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        var resultResponse = result.getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), resultResponse.getStatus());
    }
}