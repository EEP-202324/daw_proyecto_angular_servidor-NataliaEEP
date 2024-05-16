package com.example.cuerpo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CuerpoRepository extends CrudRepository<Cuerpo, Long>, PagingAndSortingRepository<Cuerpo, Long> {
	List<Cuerpo> findByCuerpoContainingIgnoreCase(String searchTerm);
	
    @Query("SELECT DISTINCT c.pais FROM Cuerpo c")
    List<String> findAllDistinctPaises();
}
