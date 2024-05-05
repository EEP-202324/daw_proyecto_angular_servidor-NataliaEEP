package com.example.cuerpo;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
class CuerpoJsonTest {
	
    @Autowired
    private JacksonTester<Cuerpo> json;

    @Test
    void cuerpoSerializationTest() throws IOException {
        Cuerpo cuerpo = new Cuerpo(99, "Navy", "Titulacion Universitaria", "37 años maximo", "USA", 
        		"https://media.defense.gov/2021/Jan/14/2002564966/1460/1280/0/210111-N-IE405-1204.JPG",
        		"https://www.secnav.navy.mil/doni/US%20Navy%20Regulations/index.pdf");
        assertThat(json.write(cuerpo)).isStrictlyEqualToJson("/example/cuerpo/expected.json"); 
        assertThat(json.write(cuerpo)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(cuerpo)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);
        assertThat(json.write(cuerpo)).hasJsonPathStringValue("@.cuerpo");
        assertThat(json.write(cuerpo)).extractingJsonPathStringValue("@.cuerpo")
             .isEqualTo("Navy");
    }
}