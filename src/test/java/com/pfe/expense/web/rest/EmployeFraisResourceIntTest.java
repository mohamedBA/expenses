package com.pfe.expense.web.rest;

import com.pfe.expense.ExpensesApp;

import com.pfe.expense.domain.EmployeFrais;
import com.pfe.expense.repository.EmployeFraisRepository;
import com.pfe.expense.service.EmployeFraisService;
import com.pfe.expense.service.dto.EmployeFraisDTO;
import com.pfe.expense.service.mapper.EmployeFraisMapper;
import com.pfe.expense.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.pfe.expense.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EmployeFraisResource REST controller.
 *
 * @see EmployeFraisResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExpensesApp.class)
public class EmployeFraisResourceIntTest {

    private static final String DEFAULT_ST = "AAAAAAAAAA";
    private static final String UPDATED_ST = "BBBBBBBBBB";

    private static final String DEFAULT_MAT_VEH = "AAAAAAAAAA";
    private static final String UPDATED_MAT_VEH = "BBBBBBBBBB";

    private static final String DEFAULT_MARQUE_VEH = "AAAAAAAAAA";
    private static final String UPDATED_MARQUE_VEH = "BBBBBBBBBB";

    private static final Integer DEFAULT_KM = 1;
    private static final Integer UPDATED_KM = 2;

    private static final Integer DEFAULT_DERN_KM = 1;
    private static final Integer UPDATED_DERN_KM = 2;

    private static final Float DEFAULT_FORFAIT_KM = 1F;
    private static final Float UPDATED_FORFAIT_KM = 2F;

    private static final String DEFAULT_C_FORFAIT = "AAAAAAAAAA";
    private static final String UPDATED_C_FORFAIT = "BBBBBBBBBB";

    private static final Integer DEFAULT_TYPE_VOITURE = 1;
    private static final Integer UPDATED_TYPE_VOITURE = 2;

    @Autowired
    private EmployeFraisRepository employeFraisRepository;


    @Autowired
    private EmployeFraisMapper employeFraisMapper;
    

    @Autowired
    private EmployeFraisService employeFraisService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEmployeFraisMockMvc;

    private EmployeFrais employeFrais;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmployeFraisResource employeFraisResource = new EmployeFraisResource(employeFraisService);
        this.restEmployeFraisMockMvc = MockMvcBuilders.standaloneSetup(employeFraisResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmployeFrais createEntity(EntityManager em) {
        EmployeFrais employeFrais = new EmployeFrais()
            .st(DEFAULT_ST)
            .matVeh(DEFAULT_MAT_VEH)
            .marqueVeh(DEFAULT_MARQUE_VEH)
            .km(DEFAULT_KM)
            .dernKm(DEFAULT_DERN_KM)
            .forfaitKm(DEFAULT_FORFAIT_KM)
            .cForfait(DEFAULT_C_FORFAIT)
            .typeVoiture(DEFAULT_TYPE_VOITURE);
        return employeFrais;
    }

    @Before
    public void initTest() {
        employeFrais = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmployeFrais() throws Exception {
        int databaseSizeBeforeCreate = employeFraisRepository.findAll().size();

        // Create the EmployeFrais
        EmployeFraisDTO employeFraisDTO = employeFraisMapper.toDto(employeFrais);
        restEmployeFraisMockMvc.perform(post("/api/employe-frais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeFraisDTO)))
            .andExpect(status().isCreated());

        // Validate the EmployeFrais in the database
        List<EmployeFrais> employeFraisList = employeFraisRepository.findAll();
        assertThat(employeFraisList).hasSize(databaseSizeBeforeCreate + 1);
        EmployeFrais testEmployeFrais = employeFraisList.get(employeFraisList.size() - 1);
        assertThat(testEmployeFrais.getSt()).isEqualTo(DEFAULT_ST);
        assertThat(testEmployeFrais.getMatVeh()).isEqualTo(DEFAULT_MAT_VEH);
        assertThat(testEmployeFrais.getMarqueVeh()).isEqualTo(DEFAULT_MARQUE_VEH);
        assertThat(testEmployeFrais.getKm()).isEqualTo(DEFAULT_KM);
        assertThat(testEmployeFrais.getDernKm()).isEqualTo(DEFAULT_DERN_KM);
        assertThat(testEmployeFrais.getForfaitKm()).isEqualTo(DEFAULT_FORFAIT_KM);
        assertThat(testEmployeFrais.getcForfait()).isEqualTo(DEFAULT_C_FORFAIT);
        assertThat(testEmployeFrais.getTypeVoiture()).isEqualTo(DEFAULT_TYPE_VOITURE);
    }

    @Test
    @Transactional
    public void createEmployeFraisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = employeFraisRepository.findAll().size();

        // Create the EmployeFrais with an existing ID
        employeFrais.setId(1L);
        EmployeFraisDTO employeFraisDTO = employeFraisMapper.toDto(employeFrais);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeFraisMockMvc.perform(post("/api/employe-frais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeFraisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmployeFrais in the database
        List<EmployeFrais> employeFraisList = employeFraisRepository.findAll();
        assertThat(employeFraisList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEmployeFrais() throws Exception {
        // Initialize the database
        employeFraisRepository.saveAndFlush(employeFrais);

        // Get all the employeFraisList
        restEmployeFraisMockMvc.perform(get("/api/employe-frais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employeFrais.getId().intValue())))
            .andExpect(jsonPath("$.[*].st").value(hasItem(DEFAULT_ST.toString())))
            .andExpect(jsonPath("$.[*].matVeh").value(hasItem(DEFAULT_MAT_VEH.toString())))
            .andExpect(jsonPath("$.[*].marqueVeh").value(hasItem(DEFAULT_MARQUE_VEH.toString())))
            .andExpect(jsonPath("$.[*].km").value(hasItem(DEFAULT_KM)))
            .andExpect(jsonPath("$.[*].dernKm").value(hasItem(DEFAULT_DERN_KM)))
            .andExpect(jsonPath("$.[*].forfaitKm").value(hasItem(DEFAULT_FORFAIT_KM.doubleValue())))
            .andExpect(jsonPath("$.[*].cForfait").value(hasItem(DEFAULT_C_FORFAIT.toString())))
            .andExpect(jsonPath("$.[*].typeVoiture").value(hasItem(DEFAULT_TYPE_VOITURE)));
    }
    

    @Test
    @Transactional
    public void getEmployeFrais() throws Exception {
        // Initialize the database
        employeFraisRepository.saveAndFlush(employeFrais);

        // Get the employeFrais
        restEmployeFraisMockMvc.perform(get("/api/employe-frais/{id}", employeFrais.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(employeFrais.getId().intValue()))
            .andExpect(jsonPath("$.st").value(DEFAULT_ST.toString()))
            .andExpect(jsonPath("$.matVeh").value(DEFAULT_MAT_VEH.toString()))
            .andExpect(jsonPath("$.marqueVeh").value(DEFAULT_MARQUE_VEH.toString()))
            .andExpect(jsonPath("$.km").value(DEFAULT_KM))
            .andExpect(jsonPath("$.dernKm").value(DEFAULT_DERN_KM))
            .andExpect(jsonPath("$.forfaitKm").value(DEFAULT_FORFAIT_KM.doubleValue()))
            .andExpect(jsonPath("$.cForfait").value(DEFAULT_C_FORFAIT.toString()))
            .andExpect(jsonPath("$.typeVoiture").value(DEFAULT_TYPE_VOITURE));
    }
    @Test
    @Transactional
    public void getNonExistingEmployeFrais() throws Exception {
        // Get the employeFrais
        restEmployeFraisMockMvc.perform(get("/api/employe-frais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmployeFrais() throws Exception {
        // Initialize the database
        employeFraisRepository.saveAndFlush(employeFrais);

        int databaseSizeBeforeUpdate = employeFraisRepository.findAll().size();

        // Update the employeFrais
        EmployeFrais updatedEmployeFrais = employeFraisRepository.findById(employeFrais.getId()).get();
        // Disconnect from session so that the updates on updatedEmployeFrais are not directly saved in db
        em.detach(updatedEmployeFrais);
        updatedEmployeFrais
            .st(UPDATED_ST)
            .matVeh(UPDATED_MAT_VEH)
            .marqueVeh(UPDATED_MARQUE_VEH)
            .km(UPDATED_KM)
            .dernKm(UPDATED_DERN_KM)
            .forfaitKm(UPDATED_FORFAIT_KM)
            .cForfait(UPDATED_C_FORFAIT)
            .typeVoiture(UPDATED_TYPE_VOITURE);
        EmployeFraisDTO employeFraisDTO = employeFraisMapper.toDto(updatedEmployeFrais);

        restEmployeFraisMockMvc.perform(put("/api/employe-frais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeFraisDTO)))
            .andExpect(status().isOk());

        // Validate the EmployeFrais in the database
        List<EmployeFrais> employeFraisList = employeFraisRepository.findAll();
        assertThat(employeFraisList).hasSize(databaseSizeBeforeUpdate);
        EmployeFrais testEmployeFrais = employeFraisList.get(employeFraisList.size() - 1);
        assertThat(testEmployeFrais.getSt()).isEqualTo(UPDATED_ST);
        assertThat(testEmployeFrais.getMatVeh()).isEqualTo(UPDATED_MAT_VEH);
        assertThat(testEmployeFrais.getMarqueVeh()).isEqualTo(UPDATED_MARQUE_VEH);
        assertThat(testEmployeFrais.getKm()).isEqualTo(UPDATED_KM);
        assertThat(testEmployeFrais.getDernKm()).isEqualTo(UPDATED_DERN_KM);
        assertThat(testEmployeFrais.getForfaitKm()).isEqualTo(UPDATED_FORFAIT_KM);
        assertThat(testEmployeFrais.getcForfait()).isEqualTo(UPDATED_C_FORFAIT);
        assertThat(testEmployeFrais.getTypeVoiture()).isEqualTo(UPDATED_TYPE_VOITURE);
    }

    @Test
    @Transactional
    public void updateNonExistingEmployeFrais() throws Exception {
        int databaseSizeBeforeUpdate = employeFraisRepository.findAll().size();

        // Create the EmployeFrais
        EmployeFraisDTO employeFraisDTO = employeFraisMapper.toDto(employeFrais);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEmployeFraisMockMvc.perform(put("/api/employe-frais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeFraisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmployeFrais in the database
        List<EmployeFrais> employeFraisList = employeFraisRepository.findAll();
        assertThat(employeFraisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmployeFrais() throws Exception {
        // Initialize the database
        employeFraisRepository.saveAndFlush(employeFrais);

        int databaseSizeBeforeDelete = employeFraisRepository.findAll().size();

        // Get the employeFrais
        restEmployeFraisMockMvc.perform(delete("/api/employe-frais/{id}", employeFrais.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EmployeFrais> employeFraisList = employeFraisRepository.findAll();
        assertThat(employeFraisList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeFrais.class);
        EmployeFrais employeFrais1 = new EmployeFrais();
        employeFrais1.setId(1L);
        EmployeFrais employeFrais2 = new EmployeFrais();
        employeFrais2.setId(employeFrais1.getId());
        assertThat(employeFrais1).isEqualTo(employeFrais2);
        employeFrais2.setId(2L);
        assertThat(employeFrais1).isNotEqualTo(employeFrais2);
        employeFrais1.setId(null);
        assertThat(employeFrais1).isNotEqualTo(employeFrais2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeFraisDTO.class);
        EmployeFraisDTO employeFraisDTO1 = new EmployeFraisDTO();
        employeFraisDTO1.setId(1L);
        EmployeFraisDTO employeFraisDTO2 = new EmployeFraisDTO();
        assertThat(employeFraisDTO1).isNotEqualTo(employeFraisDTO2);
        employeFraisDTO2.setId(employeFraisDTO1.getId());
        assertThat(employeFraisDTO1).isEqualTo(employeFraisDTO2);
        employeFraisDTO2.setId(2L);
        assertThat(employeFraisDTO1).isNotEqualTo(employeFraisDTO2);
        employeFraisDTO1.setId(null);
        assertThat(employeFraisDTO1).isNotEqualTo(employeFraisDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(employeFraisMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(employeFraisMapper.fromId(null)).isNull();
    }
}
