package com.theredeemed.myactivityexpensetrackerservice.model.repository;

import com.theredeemed.myactivityexpensetrackerservice.model.dto.TransactionDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TransactionJdbcDAO implements DAO<TransactionDTO> {
    @Override
    public void create(TransactionDTO transactionDTO) {

    }

    @Override
    public Optional<TransactionDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<TransactionDTO> findAll() {
        return null;
    }

    @Override
    public void update(TransactionDTO transactionDTO, Long id) {

    }

    @Override
    public void delete(Long id) {

    }
}
