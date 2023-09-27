package songsMS.currencyexchangeservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CurrencyExchangeControllerTest {

    @InjectMocks
    private CurrencyExchangeController controller;

    @Mock
    private CurrencyExchangeRepository repository;

    @Mock
    private Environment environment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveExchangeValue() {
        // Mock repository to return a CurrencyExchange object
        CurrencyExchange expectedExchange = new CurrencyExchange();
        when(repository.findByFromAndTo("USD", "EUR")).thenReturn(expectedExchange);

        // Mock environment to return a port
        when(environment.getProperty("local.server.port")).thenReturn("8080");

        // Call the controller method
        CurrencyExchange response = controller.retrieveExhcnageValue("USD", "EUR");

        // Verify that the repository method was called with the correct parameters
        verify(repository, times(1)).findByFromAndTo("USD", "EUR");
    }

    @Test
    public void testRetrieveExchangeValueNotFound() {
        // Mock repository to return null (exchange not found)
        when(repository.findByFromAndTo("USD", "EUR")).thenReturn(null);

        // Call the controller method and expect a RuntimeException
        assertThrows(RuntimeException.class, () -> {
            controller.retrieveExhcnageValue("USD", "EUR");
        });

        // Verify that the repository method was called with the correct parameters
        verify(repository, times(1)).findByFromAndTo("USD", "EUR");
    }
}
