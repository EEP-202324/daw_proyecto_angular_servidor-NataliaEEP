package com.example.cuerpo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping
	private ResponseEntity<List<Cuerpo>> findAll(Pageable pageable) {
	    Page<Cuerpo> page = cuerpoRepository.findAll(
	            PageRequest.of(
	                    pageable.getPageNumber(),
	                    pageable.getPageSize(),
	                    pageable.getSortOr(Sort.by(Sort.Direction.ASC, "cuerpo"))
	    ));
	    return ResponseEntity.ok(page.getContent());
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
	
	@PutMapping("/{requestedId}")
	private ResponseEntity<Void> putCuerpo(@PathVariable Long requestedId, @RequestBody Cuerpo cuerpoUpdate) {
	    Optional<Cuerpo> cuerpoOptional = cuerpoRepository.findById(requestedId);
	    if (cuerpoOptional.isPresent()) {
	        Cuerpo existingCuerpo = cuerpoOptional.get();
	        existingCuerpo.setCuerpo(cuerpoUpdate.getCuerpo());
	        existingCuerpo.setTitulacion(cuerpoUpdate.getTitulacion());
	        existingCuerpo.setRequisitos_edad(cuerpoUpdate.getRequisitos_edad());
	        existingCuerpo.setPais(cuerpoUpdate.getPais());
	        existingCuerpo.setPhoto(cuerpoUpdate.getPhoto());
	        existingCuerpo.setPdf(cuerpoUpdate.getPdf());
	        cuerpoRepository.save(existingCuerpo);
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<Void> deleteCuerpo(@PathVariable Long id) {
	    if (cuerpoRepository.existsById(id)) {
	        cuerpoRepository.deleteById(id);
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
