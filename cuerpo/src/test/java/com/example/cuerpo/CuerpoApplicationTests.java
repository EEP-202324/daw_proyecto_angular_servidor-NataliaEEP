package com.example.cuerpo;

import static org.assertj.core.api.Assertions.assertThat;
import java.net.URI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
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
	
	@Autowired
    private CuerpoRepository cuerpoRepository;

	@Test
	void shouldReturnACuerpoWhenDataIsSaved() {
		ResponseEntity<String> response = restTemplate.getForEntity("/cuerpos/999999", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		Number id = documentContext.read("$.id");
		assertThat(id).isEqualTo(999999);

		String cuerpo = documentContext.read("$.cuerpo");
		assertThat(cuerpo).isEqualTo("Air Force");

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
		assertThat(photo)
				.isEqualTo("https://media.defense.gov/2021/Jan/14/2002564966/1460/1280/0/210111-N-IE405-1204.JPG");
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
		assertThat(titulacion).containsExactlyInAnyOrder("Titulacion Universitaria", "Otra Titulacion",
				"Licenciatura en Aeronáutica");

		JSONArray requisitos_edad = documentContext.read("$..requisitos_edad");
		assertThat(requisitos_edad).containsExactlyInAnyOrder("37 años maximo", "40 años maximo", "35 años máximo");

		JSONArray pais = documentContext.read("$..pais");
		assertThat(pais).containsExactlyInAnyOrder("USA", "UK", "USA");

		JSONArray photo = documentContext.read("$..photo");
		assertThat(photo).containsExactlyInAnyOrder(
				"https://media.defense.gov/2021/Jan/14/2002564966/1460/1280/0/210111-N-IE405-1204.JPG",
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
		ResponseEntity<String> response = restTemplate.getForEntity("/cuerpos?page=0&size=1&sort=cuerpo,asc",
				String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		JSONArray read = documentContext.read("$[*]");
		assertThat(read.size()).isEqualTo(1);

		String cuerpo = documentContext.read("$[0].cuerpo");
		assertThat(cuerpo).isEqualTo("Air Force");
	}

	@Test
	void shouldReturnASortedPageOfCuerposWithNoParametersAndUseDefaultValues() {
		ResponseEntity<String> response = restTemplate.getForEntity("/cuerpos", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		JSONArray page = documentContext.read("$[*]");
		assertThat(page.size()).isEqualTo(3);

		JSONArray cuerpos = documentContext.read("$..cuerpo");
		assertThat(cuerpos).containsExactly("Air Force", "Army", "Navy");
	}

//	@Test
//	@DirtiesContext
//	void shouldUpdateAnExistingCuerpo() {
//		Cuerpo cuerpoUpdate = new Cuerpo(null, "Parachutes", "Titulacion universitaria", "30 años maximo", "USA",
//				"https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/SCIE_T10_image1.jpg/450px-SCIE_T10_image1.jpg", 
//				"https://www.moore.army.mil/Infantry/ARTB/1-507th/content/pdf/TC%203-21.220,%20Parachutes%2021%20Dec%202017.pdf");
//		HttpEntity<Cuerpo> request = new HttpEntity<>(cuerpoUpdate);
//		ResponseEntity<Void> response = restTemplate.exchange("/cuerpos/702", HttpMethod.PUT, request, Void.class);
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//		ResponseEntity<Cuerpo> getResponse = restTemplate.getForEntity("/cuerpos/702", Cuerpo.class);
//		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//		Cuerpo updatedCuerpo = getResponse.getBody();
//		assertThat(updatedCuerpo).isNotNull();
//		assertThat(updatedCuerpo.getId()).isEqualTo(702L);
//		assertThat(updatedCuerpo.getCuerpo()).isEqualTo("Parachutes");
//		assertThat(updatedCuerpo.getTitulacion()).isEqualTo("Titulacion universitaria");
//		assertThat(updatedCuerpo.getRequisitos_edad()).isEqualTo("30 años maximo");
//		assertThat(updatedCuerpo.getPais()).isEqualTo("USA");
//		assertThat(updatedCuerpo.getPhoto()).isEqualTo("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/SCIE_T10_image1.jpg/450px-SCIE_T10_image1.jpg");
//		assertThat(updatedCuerpo.getPdf()).isEqualTo("https://www.moore.army.mil/Infantry/ARTB/1-507th/content/pdf/TC%203-21.220,%20Parachutes%2021%20Dec%202017.pdf");
//	}
    @Test
    @DirtiesContext
    void shouldUpdateAnExistingCuerpo() {
        Iterable<Cuerpo> cuerpos = cuerpoRepository.findAll();
        Long idToUpdate;

        if (cuerpos.iterator().hasNext()) {
            idToUpdate = cuerpos.iterator().next().getId();
        } else {
            throw new RuntimeException("No hay cuerpos existentes en la base de datos para actualizar");
        }

        Cuerpo cuerpoUpdate = new Cuerpo(null, "Parachutes", "Titulacion universitaria", "30 años maximo", "USA",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/SCIE_T10_image1.jpg/450px-SCIE_T10_image1.jpg", 
				"https://www.moore.army.mil/Infantry/ARTB/1-507th/content/pdf/TC%203-21.220,%20Parachutes%2021%20Dec%202017.pdf");
        
        ResponseEntity<Void> response = restTemplate.exchange("/cuerpos/" + idToUpdate, HttpMethod.PUT, new HttpEntity<>(cuerpoUpdate), Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<Cuerpo> getResponse = restTemplate.getForEntity("/cuerpos/" + idToUpdate, Cuerpo.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Cuerpo updatedCuerpo = getResponse.getBody();
        assertThat(updatedCuerpo).isNotNull();
        assertThat(updatedCuerpo.getId()).isEqualTo(idToUpdate);
        assertThat(updatedCuerpo.getCuerpo()).isEqualTo("Parachutes");
        assertThat(updatedCuerpo.getTitulacion()).isEqualTo("Titulacion universitaria");
        assertThat(updatedCuerpo.getRequisitos_edad()).isEqualTo("30 años maximo");
        assertThat(updatedCuerpo.getPais()).isEqualTo("USA");
        assertThat(updatedCuerpo.getPhoto()).isEqualTo("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/SCIE_T10_image1.jpg/450px-SCIE_T10_image1.jpg");
        assertThat(updatedCuerpo.getPdf()).isEqualTo("https://www.moore.army.mil/Infantry/ARTB/1-507th/content/pdf/TC%203-21.220,%20Parachutes%2021%20Dec%202017.pdf");
    }
	
    @Test
    @DirtiesContext
    void shouldDeleteAnExistingCuerpo() {
        Iterable<Cuerpo> cuerpos = cuerpoRepository.findAll();
        Long idToDelete;

        if (cuerpos.iterator().hasNext()) {
            idToDelete = cuerpos.iterator().next().getId();
        } else {
            Cuerpo nuevoCuerpo = new Cuerpo();
            cuerpoRepository.save(nuevoCuerpo);
            idToDelete = nuevoCuerpo.getId();
        }

        ResponseEntity<Void> response = restTemplate.exchange("/cuerpos/" + idToDelete, HttpMethod.DELETE, null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<String> getResponse = restTemplate.getForEntity("/cuerpos/" + idToDelete, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }


	@Test
	void shouldNotDeleteACuerpoThatDoesNotExist() {
		ResponseEntity<Void> deleteResponse = restTemplate.exchange("/cuerpos/99999", HttpMethod.DELETE, null,
				Void.class);
		assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
}
