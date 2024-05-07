package com.example.cuerpo;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
	
	@PostMapping
	private ResponseEntity<Void> createCuerpo(@RequestBody Cuerpo newCuerpoRequest, UriComponentsBuilder ucb) {
	   Cuerpo savedCuerpo = cuerpoRepository.save(newCuerpoRequest);
	   URI locationOfNewCuerpo = ucb
	            .path("cuerpos/{id}")
	            .buildAndExpand(savedCuerpo.getId())
	            .toUri();
	   return ResponseEntity.created(locationOfNewCuerpo).build();
	}
	
	@GetMapping()
	private ResponseEntity<Iterable<Cuerpo>> findAll() {
	   return ResponseEntity.ok(cuerpoRepository.findAll());
	}
}
