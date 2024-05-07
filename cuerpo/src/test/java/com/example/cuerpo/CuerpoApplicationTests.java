package com.example.cuerpo;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
	void shouldCreateANewCuerpo() {
		Cuerpo newCuerpo = new Cuerpo(44L, "Navy", "Titulacion Universitaria", "37 años maximo", "USA",
				"https://media.defense.gov/2021/Jan/14/2002564966/1460/1280/0/210111-N-IE405-1204.JPG",
				"https://www.secnav.navy.mil/doni/US%20Navy%20Regulations/index.pdf");
		ResponseEntity<Void> createResponse = restTemplate.postForEntity("/cuerpos", newCuerpo, Void.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		URI locationOfNewCuerpo = createResponse.getHeaders().getLocation();
		ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewCuerpo, String.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(getResponse.getBody());

		Long id = documentContext.read("$.id");
		String cuerpo = documentContext.read("$.cuerpo");
		String titulacion = documentContext.read("$.titulacion");
		String requisitos_edad = documentContext.read("$.requisitos_edad");
		String pais = documentContext.read("$.pais");
		String photo = documentContext.read("$.photo");
		String pdf = documentContext.read("$.pdf");

		assertThat(id).isNotNull();
		assertThat(cuerpo).isEqualTo("ValorEsperadoParaCuerpo");
		assertThat(titulacion).isEqualTo("ValorEsperadoParaTitulacion");
		assertThat(requisitos_edad).isEqualTo("ValorEsperadoParaRequisitosEdad");
		assertThat(pais).isEqualTo("ValorEsperadoParaPais");
		assertThat(photo).isEqualTo("ValorEsperadoParaPhoto");
		assertThat(pdf).isEqualTo("ValorEsperadoParaPdf");

	}
}
