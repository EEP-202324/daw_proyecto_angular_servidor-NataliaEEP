package com.example.cuerpo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cuerpos")
class CuerpoController {
   
   @GetMapping("/{requestedId}")
   private ResponseEntity<Cuerpo> findById(@PathVariable int requestedId) {
       if (requestedId == 99) {
    	   Cuerpo cuerpo = new Cuerpo(99L, "Navy", "Titulacion Universitaria", "37 años maximo", "USA",
    	    		  "https://media.defense.gov/2021/Jan/14/2002564966/1460/1280/0/210111-N-IE405-1204.JPG", 
    	    		  "https://www.secnav.navy.mil/doni/US%20Navy%20Regulations/index.pdf");
           return ResponseEntity.ok(cuerpo);
       } else {
           return ResponseEntity.notFound().build();
       }
   }
}
