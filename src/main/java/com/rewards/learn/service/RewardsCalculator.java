package com.rewards.learn.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rewards.learn.model.Transaction;

@Service
public class RewardsCalculator {

 

    public Map<Integer, Map<String, Integer>> getRewards() {
        
        List<Transaction> transactions = buildInputData();
        
        Map<Integer, Map<String, Integer>> rewards = new HashMap<Integer, Map<String, Integer>>();
        
        for (int i = 0; i < transactions.size(); i ++) {
             Transaction transaction = transactions.get(i);
             // Filter transactions with in 3 months OR amount is greater than 50
            if (transaction.getTransactionDate().isAfter(LocalDate.now().minusDays(90))
                    && transaction.getAmount() > 50) {
                
                Map<String, Integer> pointsMap = rewards.get(transaction.getCustomerId());
                if (null == pointsMap) {
                    pointsMap = new HashMap<String, Integer>();
                }
                calculateTransactionRewards(transaction, pointsMap);
                rewards.put(transaction.getCustomerId(), pointsMap);    
                
            }
            
        }
        
        
        return rewards;
        
        
    }

    private List<Transaction> buildInputData() {
        Transaction tran1 = new Transaction();
        tran1.setCustomerId(1);
        tran1.setAmount(10);
        tran1.setTransactionDate(LocalDate.now().minusDays(1));
        
        Transaction tran2 = new Transaction();
        tran2.setCustomerId(1);
        tran2.setAmount(70);
        tran2.setTransactionDate(LocalDate.now().minusDays(10));
        
        Transaction tran6 = new Transaction();
        tran6.setCustomerId(1);
        tran6.setAmount(226);
        tran6.setTransactionDate(LocalDate.now().minusDays(20));
        
        Transaction tran3 = new Transaction();
        tran3.setCustomerId(2);
        tran3.setAmount(220);
        tran3.setTransactionDate(LocalDate.now().minusDays(40));
        
        Transaction tran4 = new Transaction();
        tran4.setCustomerId(2);
        tran4.setAmount(70);
        tran4.setTransactionDate(LocalDate.now().minusDays(86));
        
        Transaction tran5 = new Transaction();
        tran5.setCustomerId(3);
        tran5.setAmount(140);
        tran5.setTransactionDate(LocalDate.now().minusDays(100));
        
        
        List<Transaction> transactions = new ArrayList();
        transactions.add(tran1);
        transactions.add(tran2);
        transactions.add(tran3);
        transactions.add(tran4);
        transactions.add(tran5);
        transactions.add(tran6);
        return transactions;
    }

    private static void calculateTransactionRewards(Transaction transaction, Map<String, Integer> pointsMap) {
        
        Integer amount = transaction.getAmount();
        
        Integer points = 0;
        
        if (amount > 50 && amount < 100) {
            points += ((amount / 50) * 1);
        } else {
            points += (((amount / 50) * 1) + ((amount -100) * 2));
        }
        String monthName = transaction.getTransactionDate().getMonth().name();
        
        if (null != pointsMap.get(monthName)) {
            points += pointsMap.get(monthName); 
        }
        pointsMap.put(monthName, points);
        
    }

     
    
    
}
