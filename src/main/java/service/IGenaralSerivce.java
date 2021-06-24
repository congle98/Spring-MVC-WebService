package service;

import java.util.Optional;

public interface IGenaralSerivce <T>{
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    void remove(Long id);
}
