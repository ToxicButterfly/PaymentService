package com.example.paymentservice.controller;

import com.example.paymentservice.repo.PaymentRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.paymentservice.util.TestUtils.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PaymentRepo paymentRepo;

    @Test
    void getDriverTransactionsById_shouldReturnTransactionsDto() throws Exception {
        List transactions = new ArrayList<>(Arrays.asList(getDefaultPaymentTransaction(), getDefaultPaymentTransaction()));
        doReturn(Optional.of(transactions)).when(paymentRepo).findAllByDriverId(DEFAULT_ID);

        mockMvc.perform(get("/api/v1/payments/driver/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"transactionList\":[" +
                        "{\"transactionId\":1," +
                        "\"rideId\":1," +
                        "\"passengerId\":1," +
                        "\"driverId\":1," +
                        "\"amount\":100.0," +
                        "\"successful\":true}," +
                        "{\"transactionId\":1," +
                        "\"rideId\":1," +
                        "\"passengerId\":1," +
                        "\"driverId\":1," +
                        "\"amount\":100.0," +
                        "\"successful\":true}]}\n"));
    }

    @Test
    void getPassengerTransactionsById_shouldReturnTransactionsDto() throws Exception {
        List transactions = new ArrayList<>(Arrays.asList(getDefaultPaymentTransaction(), getDefaultPaymentTransaction()));
        doReturn(Optional.of(transactions)).when(paymentRepo).findAllByPassengerId(DEFAULT_ID);

        mockMvc.perform(get("/api/v1/payments/passenger/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"transactionList\":[" +
                        "{\"transactionId\":1," +
                        "\"rideId\":1," +
                        "\"passengerId\":1," +
                        "\"driverId\":1," +
                        "\"amount\":100.0," +
                        "\"successful\":true}," +
                        "{\"transactionId\":1," +
                        "\"rideId\":1," +
                        "\"passengerId\":1," +
                        "\"driverId\":1," +
                        "\"amount\":100.0," +
                        "\"successful\":true}]}\n"));
    }

}
