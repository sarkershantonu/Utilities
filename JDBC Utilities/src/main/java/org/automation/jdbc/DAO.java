package org.automation.jdbc;

import java.util.List;

public interface DAO<T> {
    void insert(T t);
    void insertBatch(List<T> t);
    T findByCustomerId(int custId);
    List<T> findAll();
    int findTotalCustomer();
}
