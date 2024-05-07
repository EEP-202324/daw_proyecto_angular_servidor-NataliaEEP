package com.example.cuerpo;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class CuerpoApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldReturnACuerpoWhenDataIsSaved() {
		ResponseEntity<String> response = restTemplate.getForEntity("/cuerpos/99", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		Number id = documentContext.read("$.id");
		assertThat(id).isEqualTo(99);

		String cuerpo = documentContext.read("$.cuerpo");
		assertThat(cuerpo).isEqualTo("Navy");

		String titulacion = documentContext.read("$.titulacion");
		assertThat(titulacion).isEqualTo("Titulacion Universitaria");

		String requisitos_edad = documentContext.read("$.requisitos_edad");
		assertThat(requisitos_edad).isEqualTo("37 años maximo");

		String pais = documentContext.read("$.pais");
		assertThat(pais).isEqualTo("USA");

		String photo = documentContext.read("$.photo");
		assertThat(photo)
				.isEqualTo("https://media.defense.gov/2021/Jan/14/2002564966/1460/1280/0/210111-N-IE405-1204.JPG");

		String pdf = documentContext.read("$.pdf");
		assertThat(pdf).isEqualTo("https://www.secnav.navy.mil/doni/US%20Navy%20Regulations/index.pdf");
	}

	@Test
	void shouldNotReturnACuerpoWithAnUnknownId() {
		ResponseEntity<String> response = restTemplate.getForEntity("/cuerpos/1000", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}

	@Test
	@DirtiesContext	
	void shouldCreateANewCuerpo() {
		Cuerpo newCuerpo = new Cuerpo(null, "Navy", "Titulacion Universitaria", "37 años maximo", "USA",
				"https://media.defense.gov/2021/Jan/14/2002564966/1460/1280/0/210111-N-IE405-1204.JPG",
				"https://www.secnav.navy.mil/doni/US%20Navy%20Regulations/index.pdf");
		ResponseEntity<Void> createResponse = restTemplate.postForEntity("/cuerpos", newCuerpo, Void.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		URI locationOfNewCuerpo = createResponse.getHeaders().getLocation();
		ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewCuerpo, String.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(getResponse.getBody());

		Number id = documentContext.read("$.id");
		String cuerpo = documentContext.read("$.cuerpo");
		String titulacion = documentContext.read("$.titulacion");
		String requisitos_edad = documentContext.read("$.requisitos_edad");
		String pais = documentContext.read("$.pais");
		String photo = documentContext.read("$.photo");
		String pdf = documentContext.read("$.pdf");

	    assertThat(id).isNotNull();
	    assertThat(cuerpo).isEqualTo("Navy");
	    assertThat(titulacion).isEqualTo("Titulacion Universitaria");
	    assertThat(requisitos_edad).isEqualTo("37 años maximo");
	    assertThat(pais).isEqualTo("USA");
	    assertThat(photo).isEqualTo("https://media.defense.gov/2021/Jan/14/2002564966/1460/1280/0/210111-N-IE405-1204.JPG");
	    assertThat(pdf).isEqualTo("https://www.secnav.navy.mil/doni/US%20Navy%20Regulations/index.pdf");

	}
	
	 @Test
	 void shouldReturnAllCuerposWhenListIsRequested() {
	     ResponseEntity<String> response = restTemplate.getForEntity("/cuerpos", String.class);
	     assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	     
	     DocumentContext documentContext = JsonPath.parse(response.getBody());
	     int cuerpoCount = documentContext.read("$.length()");
	     assertThat(cuerpoCount).isEqualTo(3);

	     JSONArray ids = documentContext.read("$..id");
	     assertThat(ids).containsExactlyInAnyOrder(99, 100, 101);

	     JSONArray cuerpos = documentContext.read("$..cuerpo");
	     assertThat(cuerpos).containsExactlyInAnyOrder("Navy", "Army", "Air Force");
	     
	     JSONArray titulacion = documentContext.read("$..titulacion");
	     assertThat(titulacion).containsExactlyInAnyOrder("Titulacion Universitaria", "Otra Titulacion", "Licenciatura en Aeronáutica");
	     
	     JSONArray requisitos_edad = documentContext.read("$..requisitos_edad");
	     assertThat(requisitos_edad).containsExactlyInAnyOrder("37 años maximo", "40 años maximo", "35 años máximo");
	     
	     JSONArray pais = documentContext.read("$..pais");
	     assertThat(pais).containsExactlyInAnyOrder("USA", "UK", "USA");
	     
	     JSONArray photo = documentContext.read("$..photo");
	     assertThat(photo).containsExactlyInAnyOrder("https://media.defense.gov/2021/Jan/14/2002564966/1460/1280/0/210111-N-IE405-1204.JPG", 
	    		 "https://media.defense.gov/2021/Jan/14/2002564966/1460/1280/0/210111-N-IE405-1204.JPG", 
	    		 "https://www.airforce.com.br/images/noticias/origem-da-forca-aerea-brasileira.jpg");
	     
	     JSONArray pdf = documentContext.read("$..pdf");
	     assertThat(pdf).containsExactlyInAnyOrder("https://www.secnav.navy.mil/doni/US%20Navy%20Regulations/index.pdf", 
	    		 "https://www.army.mil/pdf/Army_Regulations.pdf", 
	    		 "https://www.airforce.com.br/pdf/airforce_regulations.pdf");     
	 }
	 
	 @Test
	 void shouldReturnAPageOfCuerpos() {
	     ResponseEntity<String> response = restTemplate.getForEntity("/cuerpos?page=0&size=1", String.class);
	     assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	     DocumentContext documentContext = JsonPath.parse(response.getBody());
	     JSONArray page = documentContext.read("$[*]");
	     assertThat(page.size()).isEqualTo(1);
	 }
	 
	 @Test
	 void shouldReturnASortedPageOfCuerpos() {
	     ResponseEntity<String> response = restTemplate.getForEntity("/cuerpos?page=0&size=1&sort=cuerpo,asc", String.class);
	     assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	     DocumentContext documentContext = JsonPath.parse(response.getBody());
	     JSONArray read = documentContext.read("$[*]");
	     assertThat(read.size()).isEqualTo(1);

	     String cuerpo = documentContext.read("$[0].cuerpo");
	     assertThat(cuerpo).isEqualTo("Air Force");
	 }
	 
	 
	 
	 @Test
	 @DirtiesContext
	 void shouldDeleteAnExistingCuerpo() {
	     ResponseEntity<Void> response = restTemplate.exchange("/cuerpos/99", HttpMethod.DELETE, null, Void.class);
	     assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

	     ResponseEntity<String> getResponse = restTemplate.getForEntity("/cuerpos/99", String.class);
	     assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	 }
	 
	 @Test
	 void shouldNotDeleteACuerpoThatDoesNotExist() {
	     ResponseEntity<Void> deleteResponse = restTemplate.exchange("/cuerpos/99999", HttpMethod.DELETE, null, Void.class);
	     assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	 }
}
