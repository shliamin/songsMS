package songsMS.currencyexchangeservice;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExhcnageValue(
			@PathVariable String from,
			@PathVariable String to) {
		
		logger.info("retrieveExchangeValue called with {} to {}", from, to);
		
		CurrencyExchange currencyExchange 
					= repository.findByFromAndTo(from, to); 
		if(currencyExchange == null) {
			throw new RuntimeException
			("Unable to find Data for "+ from + "to" + to);
		}
		
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		
		return currencyExchange;
	}
}
