package com.example.paymentservice.component;

import com.example.paymentservice.dto.TransactionsDto;
import com.example.paymentservice.model.PaymentTransaction;
import com.example.paymentservice.repo.PaymentRepo;
import com.example.paymentservice.service.impl.PaymentServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.zookeeper.Op;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.paymentservice.util.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class PaymentComponentTest {
    @Mock
    private PaymentRepo paymentRepo;
    @InjectMocks
    private PaymentServiceImpl paymentService;
    private Exception exception;
    private TransactionsDto paymentResponse;

    @Given("Database contains transactions of driver with id {int}")
    public void containsTransactionOfDriver(int id) {
        List<PaymentTransaction> list = new ArrayList<>(Arrays.asList(getDefaultPaymentTransaction(), getDefaultPaymentTransaction()));
        doReturn(Optional.of(list)).when(paymentRepo).findAllByDriverId(id);
    }

    @When("Get request with id {int} passed to getDriverTransactionsById method")
    public void getRequestWithIdPassedToGetDriverTransactionsByIdMethod(int id) {
        try {
            paymentResponse = paymentService.getDriverTransactionsById(id);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("The response should contain transaction with id {int}")
    public void theResponseShouldContainTransactionWithId(int id) {
        List<PaymentTransaction> list = new ArrayList<>(Arrays.asList(getDefaultPaymentTransaction(), getDefaultPaymentTransaction()));
        assertEquals(new TransactionsDto(list), paymentResponse);
    }

    @Given("Database does not contain transactions of driver with id {int}")
    public void databaseDoesNotContainTransactionsOfDriverWithId(int id) {
        doReturn(Optional.empty()).when(paymentRepo).findAllByDriverId(id);
    }

    @Then("The TransactionNotFoundException for driver should be thrown")
    public void theTransactionNotFoundExceptionForDriverShouldBeThrown() {
        assertEquals(exception.getMessage(), "No payment transactions of such driver was found");
    }

    @Given("Database contains transactions of passenger with id {int}")
    public void databaseContainsTransactionsOfPassengerWithId(int id) {
        List<PaymentTransaction> list = new ArrayList<>(Arrays.asList(getDefaultPaymentTransaction(), getDefaultPaymentTransaction()));
        doReturn(Optional.of(list)).when(paymentRepo).findAllByPassengerId(id);

    }

    @When("Get request with id {int} passed to getPassengerTransactionsById method")
    public void getRequestWithIdPassedToGetPassengerTransactionsByIdMethod(int id) {
        try {
            paymentResponse = paymentService.getPassengerTransactionsById(id);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Given("Database does not contain transactions of passenger with id {int}")
    public void databaseDoesNotContainTransactionsOfPassengerWithId(int id) {
        doReturn(Optional.empty()).when(paymentRepo).findAllByPassengerId(id);
    }

    @Then("The TransactionNotFoundException for passenger should be thrown")
    public void theTransactionNotFoundExceptionForPassengerShouldBeThrown() {
        assertEquals(exception.getMessage(), "No payment transactions of such passenger was found");
    }
}
