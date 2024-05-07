package com.example.cuerpo;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
class CuerpoJsonTest {

	@Autowired
	private JacksonTester<Cuerpo> json;

	@Autowired
	private JacksonTester<Cuerpo[]> jsonList;

	private Cuerpo[] cuerpos;

	@BeforeEach
	void setUp() {
		cuerpos = Arrays.array(
				new Cuerpo(99L, "Navy", "Titulacion Universitaria", "37 años maximo", "USA",
						"https://media.defense.gov/2021/Jan/14/2002564966/1460/1280/0/210111-N-IE405-1204.JPG",
						"https://www.secnav.navy.mil/doni/US%20Navy%20Regulations/index.pdf"),
				new Cuerpo(100L, "Army", "Otra Titulacion", "40 años maximo", "UK",
						"https://media.defense.gov/2021/Jan/14/2002564966/1460/1280/0/210111-N-IE405-1204.JPG",
						"https://www.army.mil/pdf/Army_Regulations.pdf"),
				new Cuerpo(101L, "Air Force", "Licenciatura en Aeronáutica", "35 años máximo", "USA",
						"https://www.airforce.com.br/images/noticias/origem-da-forca-aerea-brasileira.jpg",
						"https://www.airforce.com.br/pdf/airforce_regulations.pdf"));
	}

	@Test
	void cuerpoSerializationTest() throws IOException {
		Cuerpo cuerpo = new Cuerpo(99L, "Navy", "Titulacion Universitaria", "37 años maximo", "USA",
				"https://media.defense.gov/2021/Jan/14/2002564966/1460/1280/0/210111-N-IE405-1204.JPG",
				"https://www.secnav.navy.mil/doni/US%20Navy%20Regulations/index.pdf");
		assertThat(json.write(cuerpo)).isStrictlyEqualToJson("/example/cuerpo/single.json");
		assertThat(json.write(cuerpo)).hasJsonPathNumberValue("@.id");
		assertThat(json.write(cuerpo)).extractingJsonPathNumberValue("@.id").isEqualTo(99);
		assertThat(json.write(cuerpo)).hasJsonPathStringValue("@.cuerpo");
		assertThat(json.write(cuerpo)).extractingJsonPathStringValue("@.cuerpo").isEqualTo("Navy");
	}

	@Test
	void cuerpoDeserializationTest() throws IOException {
		String expected = """
				     {
					"id": 99,
					"cuerpo": "Navy",
					"titulacion": "Titulacion Universitaria",
					"requisitos_edad": "37 años maximo",
					"pais": "USA",
					"photo": "https://media.defense.gov/2021/Jan/14/2002564966/1460/1280/0/210111-N-IE405-1204.JPG",
					"pdf": "https://www.secnav.navy.mil/doni/US%20Navy%20Regulations/index.pdf"
				}
				     """;
		Cuerpo cuerpo = json.parseObject(expected);
		assertThat(json.parseObject(expected).getId()).isEqualTo(99L);
		assertThat(json.parseObject(expected).getCuerpo()).isEqualTo("Navy");
		assertThat(json.parseObject(expected).getTitulacion()).isEqualTo("Titulacion Universitaria");
		assertThat(json.parseObject(expected).getRequisitos_edad()).isEqualTo("37 años maximo");
		assertThat(json.parseObject(expected).getPais()).isEqualTo("USA");
		assertThat(json.parseObject(expected).getPhoto())
				.isEqualTo("https://media.defense.gov/2021/Jan/14/2002564966/1460/1280/0/210111-N-IE405-1204.JPG");
		assertThat(json.parseObject(expected).getPdf())
				.isEqualTo("https://www.secnav.navy.mil/doni/US%20Navy%20Regulations/index.pdf");
	}

	@Test
	void cashCardListSerializationTest() throws IOException {
		assertThat(jsonList.write(cuerpos)).isStrictlyEqualToJson("/example/cuerpo/list.json");
	}

	@Test
	void cashCardListDeserializationTest() throws IOException {
		String expected = """
				[
				    { "id": 99, "cuerpo": "Navy", "titulacion": "Titulacion Universitaria", "requisitos_edad": "37 años maximo", "pais": "USA", "photo": "https://media.defense.gov/2021/Jan/14/2002564966/1460/1280/0/210111-N-IE405-1204.JPG", "pdf": "https://www.secnav.navy.mil/doni/US%20Navy%20Regulations/index.pdf" },
				    { "id": 100, "cuerpo": "Army", "titulacion": "Otra Titulacion", "requisitos_edad": "40 años maximo", "pais": "UK", "photo": "https://media.defense.gov/2021/Jan/14/2002564966/1460/1280/0/210111-N-IE405-1204.JPG", "pdf": "https://www.army.mil/pdf/Army_Regulations.pdf" },
					{ "id": 101, "cuerpo": "Air Force", "titulacion": "Licenciatura en Aeronáutica", "requisitos_edad": "35 años máximo", "pais": "USA", "photo": "https://www.airforce.com.br/images/noticias/origem-da-forca-aerea-brasileira.jpg", "pdf": "https://www.airforce.com.br/pdf/airforce_regulations.pdf" }
				]
				    """;
		assertThat(jsonList.parse(expected)).usingRecursiveComparison().isEqualTo(cuerpos);
	}
}