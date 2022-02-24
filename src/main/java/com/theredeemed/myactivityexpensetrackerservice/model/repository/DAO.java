package com.theredeemed.myactivityexpensetrackerservice.model.repository;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    void create(T t);
    Optional<T> findById(Long id) throws Exception;
    List<T> findAll();
    void update(T t, Long id);
    void delete(Long id);
}
