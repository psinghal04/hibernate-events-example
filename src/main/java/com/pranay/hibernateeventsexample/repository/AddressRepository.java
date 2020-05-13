package com.pranay.hibernateeventsexample.repository;

import com.pranay.hibernateeventsexample.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
