package com.example.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.domain.Adress;

@Repository
public interface AdressRepository  extends JpaRepository<Adress,Long>{
	List<Adress> findAllByOrderByNameAsc();
}
