package com.example.jar.Service;

import com.example.jar.model.ExchangeRateResponse;
import com.example.jar.model.Transaction;
import com.example.jar.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final RestTemplate restTemplate;
    private final String exchangeRateApiUrl;


    @Autowired
    public TransactionServiceImpl(
            TransactionRepository transactionRepository,
            RestTemplate restTemplate,
            @Value("${exchange.rate.api.url}") String exchangeRateApiUrl) {
        this.transactionRepository = transactionRepository;
        this.restTemplate = restTemplate;
        this.exchangeRateApiUrl = exchangeRateApiUrl;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction recordTransaction(Transaction transaction) {
        BigDecimal convertedAmount = convertToINR(transaction.getAmount(), transaction.getCurrency());
        transaction.setAmount(convertedAmount);
        transaction.setCurrency("INR");
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getDailyReports() {
        List<Transaction> allTransactions = transactionRepository.findAll();
        Map<LocalDate, List<Transaction>> groupedByDate = allTransactions.stream()
                .collect(Collectors.groupingBy(Transaction::getDate));
        return allTransactions;
    }

    private BigDecimal convertToINR(BigDecimal amount, String sourceCurrency) {
        String apiUrl = "https://api.fxratesapi.com/latest";
        ExchangeRateResponse response = restTemplate.getForObject(apiUrl, ExchangeRateResponse.class);

        if (response != null && response.getRates() != null) {
            Map<String, BigDecimal> rates = response.getRates();
            
            if (rates.containsKey(sourceCurrency)) {
                BigDecimal sourceToUSDConversionRate = rates.get(sourceCurrency);
                BigDecimal inrConversionRate = rates.get("INR");
                
                if (inrConversionRate != null) {
                    
                    BigDecimal amountInUSD = amount.divide(sourceToUSDConversionRate, 4, RoundingMode.HALF_UP);
                    
                    // Convert the amount from USD to INR
                    return amountInUSD.multiply(inrConversionRate);
                }
            }
        }

        throw new RuntimeException("Unable to fetch exchange rate from the external API.");
    }

}
