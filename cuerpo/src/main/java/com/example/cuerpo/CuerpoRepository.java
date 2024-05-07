package com.example.cuerpo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CuerpoRepository extends CrudRepository<Cuerpo, Long>, PagingAndSortingRepository<Cuerpo, Long>{

}
