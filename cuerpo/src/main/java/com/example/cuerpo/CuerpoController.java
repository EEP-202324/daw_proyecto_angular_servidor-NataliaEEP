package com.example.cuerpo;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cuerpos")
class CuerpoController {
	private final CuerpoRepository cuerpoRepository;

	private CuerpoController(CuerpoRepository cuerpoRepository) {
	      this.cuerpoRepository = cuerpoRepository;
	   }

	@GetMapping("/{requestedId}")
	private ResponseEntity<Cuerpo> findById(@PathVariable Long requestedId) {
	    Optional<Cuerpo> cuerpoOptional = cuerpoRepository.findById(requestedId);
	    if (cuerpoOptional.isPresent()) {
	        return ResponseEntity.ok(cuerpoOptional.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
