package ru.itis.hateoas;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hateoas.models.Attraction;
import ru.itis.hateoas.models.Park;
import ru.itis.hateoas.services.AttractionService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class AttractionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AttractionService attractionService;

    @BeforeEach
    public void setUp() {
        when(attractionService.updateStatus(25L, 1)).thenReturn(getAttraction());
    }

    @Test
    public void updateAttractionStatusTest() throws Exception {
        mockMvc.perform(get("/attractions/25/update/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(getAttraction().getName()))
                .andExpect(jsonPath("$.countOfSeats").value(getAttraction().getCountOfSeats()))
                .andExpect(jsonPath("$.status").value(getAttraction().getStatus().toString()))
                .andDo(document("update_attraction_status",
                        responseFields(fieldWithPath("name").description("Название аттракциона"),
                        fieldWithPath("countOfSeats").description("Количество посадочных мест"),
                        fieldWithPath("status").description("Состояние аттракциона")
                )));
    }

    public Attraction getAttraction() {
        Park park = Park.builder()
                .id(25L)
                .address("Баумана")
                .name("Зоопарк")
                .rank(10).build();

        return Attraction.builder()
                .id(25L)
                .name("Покорми льва")
                .countOfSeats(100)
                .status(Attraction.Status.AVAILABLE)
                .park(park)
                .build();

    }
}
