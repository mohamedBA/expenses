package com.pfe.expense.web.rest;

import com.pfe.expense.ExpensesApp;

import com.pfe.expense.domain.DOCFrais;
import com.pfe.expense.repository.DOCFraisRepository;
import com.pfe.expense.service.DOCFraisService;
import com.pfe.expense.service.dto.DOCFraisDTO;
import com.pfe.expense.service.mapper.DOCFraisMapper;
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
 * Test class for the DOCFraisResource REST controller.
 *
 * @see DOCFraisResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExpensesApp.class)
public class DOCFraisResourceIntTest {

    private static final Long DEFAULT_T_DOC_FRAIS = 1L;
    private static final Long UPDATED_T_DOC_FRAIS = 2L;

    private static final Float DEFAULT_TOTAL_FRAIS = 1F;
    private static final Float UPDATED_TOTAL_FRAIS = 2F;

    private static final LocalDate DEFAULT_DT_SOUMISSION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_SOUMISSION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_VALIDATION_PARTIELE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_VALIDATION_PARTIELE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_VALIDATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_VALIDATION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_PAYEMENT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_PAYEMENT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_REFUS = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_REFUS = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MOTIF_REFUS = "AAAAAAAAAA";
    private static final String UPDATED_MOTIF_REFUS = "BBBBBBBBBB";

    private static final Integer DEFAULT_TOTAL_FROUTE = 1;
    private static final Integer UPDATED_TOTAL_FROUTE = 2;

    private static final Float DEFAULT_TOTAL_DEPENSE = 1F;
    private static final Float UPDATED_TOTAL_DEPENSE = 2F;

    private static final Integer DEFAULT_T_USER_CRE = 1;
    private static final Integer UPDATED_T_USER_CRE = 2;

    private static final Integer DEFAULT_T_USER_MODIF = 1;
    private static final Integer UPDATED_T_USER_MODIF = 2;

    private static final Integer DEFAULT_T_P_USER = 1;
    private static final Integer UPDATED_T_P_USER = 2;

    private static final Integer DEFAULT_TOTAL_NBRE_KM = 1;
    private static final Integer UPDATED_TOTAL_NBRE_KM = 2;

    @Autowired
    private DOCFraisRepository dOCFraisRepository;


    @Autowired
    private DOCFraisMapper dOCFraisMapper;
    

    @Autowired
    private DOCFraisService dOCFraisService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDOCFraisMockMvc;

    private DOCFrais dOCFrais;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DOCFraisResource dOCFraisResource = new DOCFraisResource(dOCFraisService);
        this.restDOCFraisMockMvc = MockMvcBuilders.standaloneSetup(dOCFraisResource)
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
    public static DOCFrais createEntity(EntityManager em) {
        DOCFrais dOCFrais = new DOCFrais()
            .tDocFrais(DEFAULT_T_DOC_FRAIS)
            .totalFrais(DEFAULT_TOTAL_FRAIS)
            .dtSoumission(DEFAULT_DT_SOUMISSION)
            .dtValidationPartiele(DEFAULT_DT_VALIDATION_PARTIELE)
            .dtValidation(DEFAULT_DT_VALIDATION)
            .dtPayement(DEFAULT_DT_PAYEMENT)
            .dtRefus(DEFAULT_DT_REFUS)
            .motifRefus(DEFAULT_MOTIF_REFUS)
            .totalFroute(DEFAULT_TOTAL_FROUTE)
            .totalDepense(DEFAULT_TOTAL_DEPENSE)
            .tUserCre(DEFAULT_T_USER_CRE)
            .tUserModif(DEFAULT_T_USER_MODIF)
            .tPUser(DEFAULT_T_P_USER)
            .totalNbreKm(DEFAULT_TOTAL_NBRE_KM);
        return dOCFrais;
    }

    @Before
    public void initTest() {
        dOCFrais = createEntity(em);
    }

    @Test
    @Transactional
    public void createDOCFrais() throws Exception {
        int databaseSizeBeforeCreate = dOCFraisRepository.findAll().size();

        // Create the DOCFrais
        DOCFraisDTO dOCFraisDTO = dOCFraisMapper.toDto(dOCFrais);
        restDOCFraisMockMvc.perform(post("/api/doc-frais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dOCFraisDTO)))
            .andExpect(status().isCreated());

        // Validate the DOCFrais in the database
        List<DOCFrais> dOCFraisList = dOCFraisRepository.findAll();
        assertThat(dOCFraisList).hasSize(databaseSizeBeforeCreate + 1);
        DOCFrais testDOCFrais = dOCFraisList.get(dOCFraisList.size() - 1);
        assertThat(testDOCFrais.gettDocFrais()).isEqualTo(DEFAULT_T_DOC_FRAIS);
        assertThat(testDOCFrais.getTotalFrais()).isEqualTo(DEFAULT_TOTAL_FRAIS);
        assertThat(testDOCFrais.getDtSoumission()).isEqualTo(DEFAULT_DT_SOUMISSION);
        assertThat(testDOCFrais.getDtValidationPartiele()).isEqualTo(DEFAULT_DT_VALIDATION_PARTIELE);
        assertThat(testDOCFrais.getDtValidation()).isEqualTo(DEFAULT_DT_VALIDATION);
        assertThat(testDOCFrais.getDtPayement()).isEqualTo(DEFAULT_DT_PAYEMENT);
        assertThat(testDOCFrais.getDtRefus()).isEqualTo(DEFAULT_DT_REFUS);
        assertThat(testDOCFrais.getMotifRefus()).isEqualTo(DEFAULT_MOTIF_REFUS);
        assertThat(testDOCFrais.getTotalFroute()).isEqualTo(DEFAULT_TOTAL_FROUTE);
        assertThat(testDOCFrais.getTotalDepense()).isEqualTo(DEFAULT_TOTAL_DEPENSE);
        assertThat(testDOCFrais.gettUserCre()).isEqualTo(DEFAULT_T_USER_CRE);
        assertThat(testDOCFrais.gettUserModif()).isEqualTo(DEFAULT_T_USER_MODIF);
        assertThat(testDOCFrais.gettPUser()).isEqualTo(DEFAULT_T_P_USER);
        assertThat(testDOCFrais.getTotalNbreKm()).isEqualTo(DEFAULT_TOTAL_NBRE_KM);
    }

    @Test
    @Transactional
    public void createDOCFraisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dOCFraisRepository.findAll().size();

        // Create the DOCFrais with an existing ID
        dOCFrais.setId(1L);
        DOCFraisDTO dOCFraisDTO = dOCFraisMapper.toDto(dOCFrais);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDOCFraisMockMvc.perform(post("/api/doc-frais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dOCFraisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DOCFrais in the database
        List<DOCFrais> dOCFraisList = dOCFraisRepository.findAll();
        assertThat(dOCFraisList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDOCFrais() throws Exception {
        // Initialize the database
        dOCFraisRepository.saveAndFlush(dOCFrais);

        // Get all the dOCFraisList
        restDOCFraisMockMvc.perform(get("/api/doc-frais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dOCFrais.getId().intValue())))
            .andExpect(jsonPath("$.[*].tDocFrais").value(hasItem(DEFAULT_T_DOC_FRAIS.intValue())))
            .andExpect(jsonPath("$.[*].totalFrais").value(hasItem(DEFAULT_TOTAL_FRAIS.doubleValue())))
            .andExpect(jsonPath("$.[*].dtSoumission").value(hasItem(DEFAULT_DT_SOUMISSION.toString())))
            .andExpect(jsonPath("$.[*].dtValidationPartiele").value(hasItem(DEFAULT_DT_VALIDATION_PARTIELE.toString())))
            .andExpect(jsonPath("$.[*].dtValidation").value(hasItem(DEFAULT_DT_VALIDATION.toString())))
            .andExpect(jsonPath("$.[*].dtPayement").value(hasItem(DEFAULT_DT_PAYEMENT.toString())))
            .andExpect(jsonPath("$.[*].dtRefus").value(hasItem(DEFAULT_DT_REFUS.toString())))
            .andExpect(jsonPath("$.[*].motifRefus").value(hasItem(DEFAULT_MOTIF_REFUS.toString())))
            .andExpect(jsonPath("$.[*].totalFroute").value(hasItem(DEFAULT_TOTAL_FROUTE)))
            .andExpect(jsonPath("$.[*].totalDepense").value(hasItem(DEFAULT_TOTAL_DEPENSE.doubleValue())))
            .andExpect(jsonPath("$.[*].tUserCre").value(hasItem(DEFAULT_T_USER_CRE)))
            .andExpect(jsonPath("$.[*].tUserModif").value(hasItem(DEFAULT_T_USER_MODIF)))
            .andExpect(jsonPath("$.[*].tPUser").value(hasItem(DEFAULT_T_P_USER)))
            .andExpect(jsonPath("$.[*].totalNbreKm").value(hasItem(DEFAULT_TOTAL_NBRE_KM)));
    }
    

    @Test
    @Transactional
    public void getDOCFrais() throws Exception {
        // Initialize the database
        dOCFraisRepository.saveAndFlush(dOCFrais);

        // Get the dOCFrais
        restDOCFraisMockMvc.perform(get("/api/doc-frais/{id}", dOCFrais.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dOCFrais.getId().intValue()))
            .andExpect(jsonPath("$.tDocFrais").value(DEFAULT_T_DOC_FRAIS.intValue()))
            .andExpect(jsonPath("$.totalFrais").value(DEFAULT_TOTAL_FRAIS.doubleValue()))
            .andExpect(jsonPath("$.dtSoumission").value(DEFAULT_DT_SOUMISSION.toString()))
            .andExpect(jsonPath("$.dtValidationPartiele").value(DEFAULT_DT_VALIDATION_PARTIELE.toString()))
            .andExpect(jsonPath("$.dtValidation").value(DEFAULT_DT_VALIDATION.toString()))
            .andExpect(jsonPath("$.dtPayement").value(DEFAULT_DT_PAYEMENT.toString()))
            .andExpect(jsonPath("$.dtRefus").value(DEFAULT_DT_REFUS.toString()))
            .andExpect(jsonPath("$.motifRefus").value(DEFAULT_MOTIF_REFUS.toString()))
            .andExpect(jsonPath("$.totalFroute").value(DEFAULT_TOTAL_FROUTE))
            .andExpect(jsonPath("$.totalDepense").value(DEFAULT_TOTAL_DEPENSE.doubleValue()))
            .andExpect(jsonPath("$.tUserCre").value(DEFAULT_T_USER_CRE))
            .andExpect(jsonPath("$.tUserModif").value(DEFAULT_T_USER_MODIF))
            .andExpect(jsonPath("$.tPUser").value(DEFAULT_T_P_USER))
            .andExpect(jsonPath("$.totalNbreKm").value(DEFAULT_TOTAL_NBRE_KM));
    }
    @Test
    @Transactional
    public void getNonExistingDOCFrais() throws Exception {
        // Get the dOCFrais
        restDOCFraisMockMvc.perform(get("/api/doc-frais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDOCFrais() throws Exception {
        // Initialize the database
        dOCFraisRepository.saveAndFlush(dOCFrais);

        int databaseSizeBeforeUpdate = dOCFraisRepository.findAll().size();

        // Update the dOCFrais
        DOCFrais updatedDOCFrais = dOCFraisRepository.findById(dOCFrais.getId()).get();
        // Disconnect from session so that the updates on updatedDOCFrais are not directly saved in db
        em.detach(updatedDOCFrais);
        updatedDOCFrais
            .tDocFrais(UPDATED_T_DOC_FRAIS)
            .totalFrais(UPDATED_TOTAL_FRAIS)
            .dtSoumission(UPDATED_DT_SOUMISSION)
            .dtValidationPartiele(UPDATED_DT_VALIDATION_PARTIELE)
            .dtValidation(UPDATED_DT_VALIDATION)
            .dtPayement(UPDATED_DT_PAYEMENT)
            .dtRefus(UPDATED_DT_REFUS)
            .motifRefus(UPDATED_MOTIF_REFUS)
            .totalFroute(UPDATED_TOTAL_FROUTE)
            .totalDepense(UPDATED_TOTAL_DEPENSE)
            .tUserCre(UPDATED_T_USER_CRE)
            .tUserModif(UPDATED_T_USER_MODIF)
            .tPUser(UPDATED_T_P_USER)
            .totalNbreKm(UPDATED_TOTAL_NBRE_KM);
        DOCFraisDTO dOCFraisDTO = dOCFraisMapper.toDto(updatedDOCFrais);

        restDOCFraisMockMvc.perform(put("/api/doc-frais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dOCFraisDTO)))
            .andExpect(status().isOk());

        // Validate the DOCFrais in the database
        List<DOCFrais> dOCFraisList = dOCFraisRepository.findAll();
        assertThat(dOCFraisList).hasSize(databaseSizeBeforeUpdate);
        DOCFrais testDOCFrais = dOCFraisList.get(dOCFraisList.size() - 1);
        assertThat(testDOCFrais.gettDocFrais()).isEqualTo(UPDATED_T_DOC_FRAIS);
        assertThat(testDOCFrais.getTotalFrais()).isEqualTo(UPDATED_TOTAL_FRAIS);
        assertThat(testDOCFrais.getDtSoumission()).isEqualTo(UPDATED_DT_SOUMISSION);
        assertThat(testDOCFrais.getDtValidationPartiele()).isEqualTo(UPDATED_DT_VALIDATION_PARTIELE);
        assertThat(testDOCFrais.getDtValidation()).isEqualTo(UPDATED_DT_VALIDATION);
        assertThat(testDOCFrais.getDtPayement()).isEqualTo(UPDATED_DT_PAYEMENT);
        assertThat(testDOCFrais.getDtRefus()).isEqualTo(UPDATED_DT_REFUS);
        assertThat(testDOCFrais.getMotifRefus()).isEqualTo(UPDATED_MOTIF_REFUS);
        assertThat(testDOCFrais.getTotalFroute()).isEqualTo(UPDATED_TOTAL_FROUTE);
        assertThat(testDOCFrais.getTotalDepense()).isEqualTo(UPDATED_TOTAL_DEPENSE);
        assertThat(testDOCFrais.gettUserCre()).isEqualTo(UPDATED_T_USER_CRE);
        assertThat(testDOCFrais.gettUserModif()).isEqualTo(UPDATED_T_USER_MODIF);
        assertThat(testDOCFrais.gettPUser()).isEqualTo(UPDATED_T_P_USER);
        assertThat(testDOCFrais.getTotalNbreKm()).isEqualTo(UPDATED_TOTAL_NBRE_KM);
    }

    @Test
    @Transactional
    public void updateNonExistingDOCFrais() throws Exception {
        int databaseSizeBeforeUpdate = dOCFraisRepository.findAll().size();

        // Create the DOCFrais
        DOCFraisDTO dOCFraisDTO = dOCFraisMapper.toDto(dOCFrais);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDOCFraisMockMvc.perform(put("/api/doc-frais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dOCFraisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DOCFrais in the database
        List<DOCFrais> dOCFraisList = dOCFraisRepository.findAll();
        assertThat(dOCFraisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDOCFrais() throws Exception {
        // Initialize the database
        dOCFraisRepository.saveAndFlush(dOCFrais);

        int databaseSizeBeforeDelete = dOCFraisRepository.findAll().size();

        // Get the dOCFrais
        restDOCFraisMockMvc.perform(delete("/api/doc-frais/{id}", dOCFrais.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DOCFrais> dOCFraisList = dOCFraisRepository.findAll();
        assertThat(dOCFraisList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DOCFrais.class);
        DOCFrais dOCFrais1 = new DOCFrais();
        dOCFrais1.setId(1L);
        DOCFrais dOCFrais2 = new DOCFrais();
        dOCFrais2.setId(dOCFrais1.getId());
        assertThat(dOCFrais1).isEqualTo(dOCFrais2);
        dOCFrais2.setId(2L);
        assertThat(dOCFrais1).isNotEqualTo(dOCFrais2);
        dOCFrais1.setId(null);
        assertThat(dOCFrais1).isNotEqualTo(dOCFrais2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DOCFraisDTO.class);
        DOCFraisDTO dOCFraisDTO1 = new DOCFraisDTO();
        dOCFraisDTO1.setId(1L);
        DOCFraisDTO dOCFraisDTO2 = new DOCFraisDTO();
        assertThat(dOCFraisDTO1).isNotEqualTo(dOCFraisDTO2);
        dOCFraisDTO2.setId(dOCFraisDTO1.getId());
        assertThat(dOCFraisDTO1).isEqualTo(dOCFraisDTO2);
        dOCFraisDTO2.setId(2L);
        assertThat(dOCFraisDTO1).isNotEqualTo(dOCFraisDTO2);
        dOCFraisDTO1.setId(null);
        assertThat(dOCFraisDTO1).isNotEqualTo(dOCFraisDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(dOCFraisMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(dOCFraisMapper.fromId(null)).isNull();
    }
}
