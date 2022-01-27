package com.supertal.countryapi.integration;

import com.supertal.countryapi.controller.CountryController;
import com.supertal.countryapi.model.Country;
import com.supertal.countryapi.service.impl.CountryServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 27/01/2022 - 15:48
 */

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CountryController.class)
public class CountryIntegrationTests {

    private static final String BASE_URL = "/api/country/";
    private static final String COUNTRY_CODE = "TH";
    private static final String COUNTRY_NAME = "Republic of North Macedonia";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryServiceImpl countryService;

    private Country getCountry() {
        return Country.builder()
                .name(COUNTRY_NAME)
                .build();
    }

    //@Test
    public void givenCountry_whenGetCountryByName_thenReturnCountry() throws Exception {

        given(countryService.getByName(ArgumentMatchers.any(String.class))).willReturn(getCountry());

        mockMvc.perform(MockMvcRequestBuilders
                        .get(BASE_URL + COUNTRY_CODE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(COUNTRY_NAME)));
    }
}
