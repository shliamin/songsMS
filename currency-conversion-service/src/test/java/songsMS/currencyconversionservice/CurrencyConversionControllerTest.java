package songsMS.currencyconversionservice;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CurrencyConversionControllerTest {

    @InjectMocks
    private CurrencyConversionController controller;

    @Mock
    private CurrencyExchangeProxy proxy;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateCurrencyConversion() {
        // Mock restTemplate to return a ResponseEntity with the desired conversionMultiple
        BigDecimal expectedConversionMultiple = BigDecimal.valueOf(1.2);
        CurrencyConversion expectedConversion = new CurrencyConversion();
        expectedConversion.setConversionMultiple(expectedConversionMultiple);
        ResponseEntity<CurrencyConversion> responseEntity = new ResponseEntity<>(expectedConversion, HttpStatus.OK);
        when(restTemplate.getForEntity(anyString(), eq(CurrencyConversion.class), anyMap()))
                .thenReturn(responseEntity);

        // Call the controller method
        CurrencyConversion result = controller.calculateCurrencyConversion("USD", "EUR", BigDecimal.valueOf(100));

        // Verify that the conversionMultiple matches the expected value
        assertEquals(expectedConversionMultiple, result.getConversionMultiple());
    }

    @Test
    public void testCalculateCurrencyConversionFeign() {
        // Mock proxy to return a CurrencyConversion object with the desired conversionMultiple
        BigDecimal expectedConversionMultiple = BigDecimal.valueOf(1.2);
        CurrencyConversion expectedConversion = new CurrencyConversion();
    }
}
