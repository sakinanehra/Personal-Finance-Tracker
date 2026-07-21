
package com.finance.finance_tracker_api.service.impl;

import com.finance.finance_tracker_api.dto.request.TransactionRequest;
import com.finance.finance_tracker_api.dto.response.TransactionResponse;
import com.finance.finance_tracker_api.entity.*;
import com.finance.finance_tracker_api.mapper.TransactionMapper;
import com.finance.finance_tracker_api.repository.*;
import com.finance.finance_tracker_api.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public TransactionResponse createTransaction(TransactionRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Transaction transaction = Transaction.builder()
                .user(user)
                .account(account)
                .category(category)
                .amount(request.getAmount())
                .transactionType(TransactionType.valueOf(request.getTransactionType()))
                .description(request.getDescription())
                .transactionDate(LocalDateTime.now())
                .build();

        return TransactionMapper.toResponse(transactionRepository.save(transaction));
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(TransactionMapper::toResponse)
                .toList();
    }

    @Override
    public TransactionResponse getTransactionById(Integer id) {

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        return TransactionMapper.toResponse(transaction);
    }

    @Override
    public TransactionResponse updateTransaction(Integer id, TransactionRequest request) {

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        transaction.setUser(user);
        transaction.setAccount(account);
        transaction.setCategory(category);
        transaction.setAmount(request.getAmount());
        transaction.setTransactionType(TransactionType.valueOf(request.getTransactionType()));
        transaction.setDescription(request.getDescription());

        return TransactionMapper.toResponse(transactionRepository.save(transaction));
    }

    @Override
    public void deleteTransaction(Integer id) {

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        transactionRepository.delete(transaction);
    }
}