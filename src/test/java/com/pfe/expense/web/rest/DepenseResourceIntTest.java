package com.pfe.expense.web.rest;

import com.pfe.expense.ExpensesApp;

import com.pfe.expense.domain.Depense;
import com.pfe.expense.repository.DepenseRepository;
import com.pfe.expense.service.DepenseService;
import com.pfe.expense.service.dto.DepenseDTO;
import com.pfe.expense.service.mapper.DepenseMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.pfe.expense.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DepenseResource REST controller.
 *
 * @see DepenseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExpensesApp.class)
public class DepenseResourceIntTest {

    private static final Integer DEFAULT_T_F_DEPENSE = 1;
    private static final Integer UPDATED_T_F_DEPENSE = 2;

    private static final Integer DEFAULT_T_DOC_FRAIS = 1;
    private static final Integer UPDATED_T_DOC_FRAIS = 2;

    private static final String DEFAULT_C_TYPE_DEPENSE = "AAAAAAAAAA";
    private static final String UPDATED_C_TYPE_DEPENSE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DT_FDEPENSE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_FDEPENSE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REMARQUE = "AAAAAAAAAA";
    private static final String UPDATED_REMARQUE = "BBBBBBBBBB";

    private static final Float DEFAULT_MONTANT = 1F;
    private static final Float UPDATED_MONTANT = 2F;

    private static final Float DEFAULT_MT_ACCP = 1F;
    private static final Float UPDATED_MT_ACCP = 2F;

    private static final Float DEFAULT_MT_DEPASS = 1F;
    private static final Float UPDATED_MT_DEPASS = 2F;

    private static final Float DEFAULT_PLAFOND = 1F;
    private static final Float UPDATED_PLAFOND = 2F;

    @Autowired
    private DepenseRepository depenseRepository;


    @Autowired
    private DepenseMapper depenseMapper;
    

    @Autowired
    private DepenseService depenseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDepenseMockMvc;

    private Depense depense;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DepenseResource depenseResource = new DepenseResource(depenseService);
        this.restDepenseMockMvc = MockMvcBuilders.standaloneSetup(depenseResource)
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
    public static Depense createEntity(EntityManager em) {
        Depense depense = new Depense()
            .tFDepense(DEFAULT_T_F_DEPENSE)
            .tDocFrais(DEFAULT_T_DOC_FRAIS)
            .cTypeDepense(DEFAULT_C_TYPE_DEPENSE)
            .dtFdepense(DEFAULT_DT_FDEPENSE)
            .remarque(DEFAULT_REMARQUE)
            .montant(DEFAULT_MONTANT)
            .mtAccp(DEFAULT_MT_ACCP)
            .mtDepass(DEFAULT_MT_DEPASS)
            .plafond(DEFAULT_PLAFOND);
        return depense;
    }

    @Before
    public void initTest() {
        depense = createEntity(em);
    }

    @Test
    @Transactional
    public void createDepense() throws Exception {
        int databaseSizeBeforeCreate = depenseRepository.findAll().size();

        // Create the Depense
        DepenseDTO depenseDTO = depenseMapper.toDto(depense);
        restDepenseMockMvc.perform(post("/api/depenses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(depenseDTO)))
            .andExpect(status().isCreated());

        // Validate the Depense in the database
        List<Depense> depenseList = depenseRepository.findAll();
        assertThat(depenseList).hasSize(databaseSizeBeforeCreate + 1);
        Depense testDepense = depenseList.get(depenseList.size() - 1);
        assertThat(testDepense.gettFDepense()).isEqualTo(DEFAULT_T_F_DEPENSE);
        assertThat(testDepense.gettDocFrais()).isEqualTo(DEFAULT_T_DOC_FRAIS);
        assertThat(testDepense.getcTypeDepense()).isEqualTo(DEFAULT_C_TYPE_DEPENSE);
        assertThat(testDepense.getDtFdepense()).isEqualTo(DEFAULT_DT_FDEPENSE);
        assertThat(testDepense.getRemarque()).isEqualTo(DEFAULT_REMARQUE);
        assertThat(testDepense.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testDepense.getMtAccp()).isEqualTo(DEFAULT_MT_ACCP);
        assertThat(testDepense.getMtDepass()).isEqualTo(DEFAULT_MT_DEPASS);
        assertThat(testDepense.getPlafond()).isEqualTo(DEFAULT_PLAFOND);
    }

    @Test
    @Transactional
    public void createDepenseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = depenseRepository.findAll().size();

        // Create the Depense with an existing ID
        depense.setId(1L);
        DepenseDTO depenseDTO = depenseMapper.toDto(depense);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepenseMockMvc.perform(post("/api/depenses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(depenseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Depense in the database
        List<Depense> depenseList = depenseRepository.findAll();
        assertThat(depenseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDepenses() throws Exception {
        // Initialize the database
        depenseRepository.saveAndFlush(depense);

        // Get all the depenseList
        restDepenseMockMvc.perform(get("/api/depenses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(depense.getId().intValue())))
            .andExpect(jsonPath("$.[*].tFDepense").value(hasItem(DEFAULT_T_F_DEPENSE)))
            .andExpect(jsonPath("$.[*].tDocFrais").value(hasItem(DEFAULT_T_DOC_FRAIS)))
            .andExpect(jsonPath("$.[*].cTypeDepense").value(hasItem(DEFAULT_C_TYPE_DEPENSE.toString())))
            .andExpect(jsonPath("$.[*].dtFdepense").value(hasItem(DEFAULT_DT_FDEPENSE.toString())))
            .andExpect(jsonPath("$.[*].remarque").value(hasItem(DEFAULT_REMARQUE.toString())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.doubleValue())))
            .andExpect(jsonPath("$.[*].mtAccp").value(hasItem(DEFAULT_MT_ACCP.doubleValue())))
            .andExpect(jsonPath("$.[*].mtDepass").value(hasItem(DEFAULT_MT_DEPASS.doubleValue())))
            .andExpect(jsonPath("$.[*].plafond").value(hasItem(DEFAULT_PLAFOND.doubleValue())));
    }
    

    @Test
    @Transactional
    public void getDepense() throws Exception {
        // Initialize the database
        depenseRepository.saveAndFlush(depense);

        // Get the depense
        restDepenseMockMvc.perform(get("/api/depenses/{id}", depense.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(depense.getId().intValue()))
            .andExpect(jsonPath("$.tFDepense").value(DEFAULT_T_F_DEPENSE))
            .andExpect(jsonPath("$.tDocFrais").value(DEFAULT_T_DOC_FRAIS))
            .andExpect(jsonPath("$.cTypeDepense").value(DEFAULT_C_TYPE_DEPENSE.toString()))
            .andExpect(jsonPath("$.dtFdepense").value(DEFAULT_DT_FDEPENSE.toString()))
            .andExpect(jsonPath("$.remarque").value(DEFAULT_REMARQUE.toString()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.doubleValue()))
            .andExpect(jsonPath("$.mtAccp").value(DEFAULT_MT_ACCP.doubleValue()))
            .andExpect(jsonPath("$.mtDepass").value(DEFAULT_MT_DEPASS.doubleValue()))
            .andExpect(jsonPath("$.plafond").value(DEFAULT_PLAFOND.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDepense() throws Exception {
        // Get the depense
        restDepenseMockMvc.perform(get("/api/depenses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDepense() throws Exception {
        // Initialize the database
        depenseRepository.saveAndFlush(depense);

        int databaseSizeBeforeUpdate = depenseRepository.findAll().size();

        // Update the depense
        Depense updatedDepense = depenseRepository.findById(depense.getId()).get();
        // Disconnect from session so that the updates on updatedDepense are not directly saved in db
        em.detach(updatedDepense);
        updatedDepense
            .tFDepense(UPDATED_T_F_DEPENSE)
            .tDocFrais(UPDATED_T_DOC_FRAIS)
            .cTypeDepense(UPDATED_C_TYPE_DEPENSE)
            .dtFdepense(UPDATED_DT_FDEPENSE)
            .remarque(UPDATED_REMARQUE)
            .montant(UPDATED_MONTANT)
            .mtAccp(UPDATED_MT_ACCP)
            .mtDepass(UPDATED_MT_DEPASS)
            .plafond(UPDATED_PLAFOND);
        DepenseDTO depenseDTO = depenseMapper.toDto(updatedDepense);

        restDepenseMockMvc.perform(put("/api/depenses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(depenseDTO)))
            .andExpect(status().isOk());

        // Validate the Depense in the database
        List<Depense> depenseList = depenseRepository.findAll();
        assertThat(depenseList).hasSize(databaseSizeBeforeUpdate);
        Depense testDepense = depenseList.get(depenseList.size() - 1);
        assertThat(testDepense.gettFDepense()).isEqualTo(UPDATED_T_F_DEPENSE);
        assertThat(testDepense.gettDocFrais()).isEqualTo(UPDATED_T_DOC_FRAIS);
        assertThat(testDepense.getcTypeDepense()).isEqualTo(UPDATED_C_TYPE_DEPENSE);
        assertThat(testDepense.getDtFdepense()).isEqualTo(UPDATED_DT_FDEPENSE);
        assertThat(testDepense.getRemarque()).isEqualTo(UPDATED_REMARQUE);
        assertThat(testDepense.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testDepense.getMtAccp()).isEqualTo(UPDATED_MT_ACCP);
        assertThat(testDepense.getMtDepass()).isEqualTo(UPDATED_MT_DEPASS);
        assertThat(testDepense.getPlafond()).isEqualTo(UPDATED_PLAFOND);
    }

    @Test
    @Transactional
    public void updateNonExistingDepense() throws Exception {
        int databaseSizeBeforeUpdate = depenseRepository.findAll().size();

        // Create the Depense
        DepenseDTO depenseDTO = depenseMapper.toDto(depense);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDepenseMockMvc.perform(put("/api/depenses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(depenseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Depense in the database
        List<Depense> depenseList = depenseRepository.findAll();
        assertThat(depenseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDepense() throws Exception {
        // Initialize the database
        depenseRepository.saveAndFlush(depense);

        int databaseSizeBeforeDelete = depenseRepository.findAll().size();

        // Get the depense
        restDepenseMockMvc.perform(delete("/api/depenses/{id}", depense.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Depense> depenseList = depenseRepository.findAll();
        assertThat(depenseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Depense.class);
        Depense depense1 = new Depense();
        depense1.setId(1L);
        Depense depense2 = new Depense();
        depense2.setId(depense1.getId());
        assertThat(depense1).isEqualTo(depense2);
        depense2.setId(2L);
        assertThat(depense1).isNotEqualTo(depense2);
        depense1.setId(null);
        assertThat(depense1).isNotEqualTo(depense2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepenseDTO.class);
        DepenseDTO depenseDTO1 = new DepenseDTO();
        depenseDTO1.setId(1L);
        DepenseDTO depenseDTO2 = new DepenseDTO();
        assertThat(depenseDTO1).isNotEqualTo(depenseDTO2);
        depenseDTO2.setId(depenseDTO1.getId());
        assertThat(depenseDTO1).isEqualTo(depenseDTO2);
        depenseDTO2.setId(2L);
        assertThat(depenseDTO1).isNotEqualTo(depenseDTO2);
        depenseDTO1.setId(null);
        assertThat(depenseDTO1).isNotEqualTo(depenseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(depenseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(depenseMapper.fromId(null)).isNull();
    }
}
