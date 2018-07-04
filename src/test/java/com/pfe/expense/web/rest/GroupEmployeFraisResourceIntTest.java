package com.pfe.expense.web.rest;

import com.pfe.expense.ExpensesApp;

import com.pfe.expense.domain.GroupEmployeFrais;
import com.pfe.expense.repository.GroupEmployeFraisRepository;
import com.pfe.expense.service.GroupEmployeFraisService;
import com.pfe.expense.service.dto.GroupEmployeFraisDTO;
import com.pfe.expense.service.mapper.GroupEmployeFraisMapper;
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
 * Test class for the GroupEmployeFraisResource REST controller.
 *
 * @see GroupEmployeFraisResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExpensesApp.class)
public class GroupEmployeFraisResourceIntTest {

    private static final Float DEFAULT_PLAFOND = 1F;
    private static final Float UPDATED_PLAFOND = 2F;

    private static final LocalDate DEFAULT_DT_DEB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_DEB = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DT_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_T_USER_CRE = 1;
    private static final Integer UPDATED_T_USER_CRE = 2;

    private static final Integer DEFAULT_T_USER_MOD = 1;
    private static final Integer UPDATED_T_USER_MOD = 2;

    private static final LocalDate DEFAULT_DT_CRE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_CRE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DE_MOD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DE_MOD = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private GroupEmployeFraisRepository groupEmployeFraisRepository;


    @Autowired
    private GroupEmployeFraisMapper groupEmployeFraisMapper;
    

    @Autowired
    private GroupEmployeFraisService groupEmployeFraisService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGroupEmployeFraisMockMvc;

    private GroupEmployeFrais groupEmployeFrais;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GroupEmployeFraisResource groupEmployeFraisResource = new GroupEmployeFraisResource(groupEmployeFraisService);
        this.restGroupEmployeFraisMockMvc = MockMvcBuilders.standaloneSetup(groupEmployeFraisResource)
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
    public static GroupEmployeFrais createEntity(EntityManager em) {
        GroupEmployeFrais groupEmployeFrais = new GroupEmployeFrais()
            .plafond(DEFAULT_PLAFOND)
            .dtDeb(DEFAULT_DT_DEB)
            .dtFin(DEFAULT_DT_FIN)
            .tUserCre(DEFAULT_T_USER_CRE)
            .tUserMod(DEFAULT_T_USER_MOD)
            .dtCre(DEFAULT_DT_CRE)
            .deMod(DEFAULT_DE_MOD);
        return groupEmployeFrais;
    }

    @Before
    public void initTest() {
        groupEmployeFrais = createEntity(em);
    }

    @Test
    @Transactional
    public void createGroupEmployeFrais() throws Exception {
        int databaseSizeBeforeCreate = groupEmployeFraisRepository.findAll().size();

        // Create the GroupEmployeFrais
        GroupEmployeFraisDTO groupEmployeFraisDTO = groupEmployeFraisMapper.toDto(groupEmployeFrais);
        restGroupEmployeFraisMockMvc.perform(post("/api/group-employe-frais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupEmployeFraisDTO)))
            .andExpect(status().isCreated());

        // Validate the GroupEmployeFrais in the database
        List<GroupEmployeFrais> groupEmployeFraisList = groupEmployeFraisRepository.findAll();
        assertThat(groupEmployeFraisList).hasSize(databaseSizeBeforeCreate + 1);
        GroupEmployeFrais testGroupEmployeFrais = groupEmployeFraisList.get(groupEmployeFraisList.size() - 1);
        assertThat(testGroupEmployeFrais.getPlafond()).isEqualTo(DEFAULT_PLAFOND);
        assertThat(testGroupEmployeFrais.getDtDeb()).isEqualTo(DEFAULT_DT_DEB);
        assertThat(testGroupEmployeFrais.getDtFin()).isEqualTo(DEFAULT_DT_FIN);
        assertThat(testGroupEmployeFrais.gettUserCre()).isEqualTo(DEFAULT_T_USER_CRE);
        assertThat(testGroupEmployeFrais.gettUserMod()).isEqualTo(DEFAULT_T_USER_MOD);
        assertThat(testGroupEmployeFrais.getDtCre()).isEqualTo(DEFAULT_DT_CRE);
        assertThat(testGroupEmployeFrais.getDeMod()).isEqualTo(DEFAULT_DE_MOD);
    }

    @Test
    @Transactional
    public void createGroupEmployeFraisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = groupEmployeFraisRepository.findAll().size();

        // Create the GroupEmployeFrais with an existing ID
        groupEmployeFrais.setId(1L);
        GroupEmployeFraisDTO groupEmployeFraisDTO = groupEmployeFraisMapper.toDto(groupEmployeFrais);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroupEmployeFraisMockMvc.perform(post("/api/group-employe-frais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupEmployeFraisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GroupEmployeFrais in the database
        List<GroupEmployeFrais> groupEmployeFraisList = groupEmployeFraisRepository.findAll();
        assertThat(groupEmployeFraisList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGroupEmployeFrais() throws Exception {
        // Initialize the database
        groupEmployeFraisRepository.saveAndFlush(groupEmployeFrais);

        // Get all the groupEmployeFraisList
        restGroupEmployeFraisMockMvc.perform(get("/api/group-employe-frais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groupEmployeFrais.getId().intValue())))
            .andExpect(jsonPath("$.[*].plafond").value(hasItem(DEFAULT_PLAFOND.doubleValue())))
            .andExpect(jsonPath("$.[*].dtDeb").value(hasItem(DEFAULT_DT_DEB.toString())))
            .andExpect(jsonPath("$.[*].dtFin").value(hasItem(DEFAULT_DT_FIN.toString())))
            .andExpect(jsonPath("$.[*].tUserCre").value(hasItem(DEFAULT_T_USER_CRE)))
            .andExpect(jsonPath("$.[*].tUserMod").value(hasItem(DEFAULT_T_USER_MOD)))
            .andExpect(jsonPath("$.[*].dtCre").value(hasItem(DEFAULT_DT_CRE.toString())))
            .andExpect(jsonPath("$.[*].deMod").value(hasItem(DEFAULT_DE_MOD.toString())));
    }
    

    @Test
    @Transactional
    public void getGroupEmployeFrais() throws Exception {
        // Initialize the database
        groupEmployeFraisRepository.saveAndFlush(groupEmployeFrais);

        // Get the groupEmployeFrais
        restGroupEmployeFraisMockMvc.perform(get("/api/group-employe-frais/{id}", groupEmployeFrais.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(groupEmployeFrais.getId().intValue()))
            .andExpect(jsonPath("$.plafond").value(DEFAULT_PLAFOND.doubleValue()))
            .andExpect(jsonPath("$.dtDeb").value(DEFAULT_DT_DEB.toString()))
            .andExpect(jsonPath("$.dtFin").value(DEFAULT_DT_FIN.toString()))
            .andExpect(jsonPath("$.tUserCre").value(DEFAULT_T_USER_CRE))
            .andExpect(jsonPath("$.tUserMod").value(DEFAULT_T_USER_MOD))
            .andExpect(jsonPath("$.dtCre").value(DEFAULT_DT_CRE.toString()))
            .andExpect(jsonPath("$.deMod").value(DEFAULT_DE_MOD.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingGroupEmployeFrais() throws Exception {
        // Get the groupEmployeFrais
        restGroupEmployeFraisMockMvc.perform(get("/api/group-employe-frais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGroupEmployeFrais() throws Exception {
        // Initialize the database
        groupEmployeFraisRepository.saveAndFlush(groupEmployeFrais);

        int databaseSizeBeforeUpdate = groupEmployeFraisRepository.findAll().size();

        // Update the groupEmployeFrais
        GroupEmployeFrais updatedGroupEmployeFrais = groupEmployeFraisRepository.findById(groupEmployeFrais.getId()).get();
        // Disconnect from session so that the updates on updatedGroupEmployeFrais are not directly saved in db
        em.detach(updatedGroupEmployeFrais);
        updatedGroupEmployeFrais
            .plafond(UPDATED_PLAFOND)
            .dtDeb(UPDATED_DT_DEB)
            .dtFin(UPDATED_DT_FIN)
            .tUserCre(UPDATED_T_USER_CRE)
            .tUserMod(UPDATED_T_USER_MOD)
            .dtCre(UPDATED_DT_CRE)
            .deMod(UPDATED_DE_MOD);
        GroupEmployeFraisDTO groupEmployeFraisDTO = groupEmployeFraisMapper.toDto(updatedGroupEmployeFrais);

        restGroupEmployeFraisMockMvc.perform(put("/api/group-employe-frais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupEmployeFraisDTO)))
            .andExpect(status().isOk());

        // Validate the GroupEmployeFrais in the database
        List<GroupEmployeFrais> groupEmployeFraisList = groupEmployeFraisRepository.findAll();
        assertThat(groupEmployeFraisList).hasSize(databaseSizeBeforeUpdate);
        GroupEmployeFrais testGroupEmployeFrais = groupEmployeFraisList.get(groupEmployeFraisList.size() - 1);
        assertThat(testGroupEmployeFrais.getPlafond()).isEqualTo(UPDATED_PLAFOND);
        assertThat(testGroupEmployeFrais.getDtDeb()).isEqualTo(UPDATED_DT_DEB);
        assertThat(testGroupEmployeFrais.getDtFin()).isEqualTo(UPDATED_DT_FIN);
        assertThat(testGroupEmployeFrais.gettUserCre()).isEqualTo(UPDATED_T_USER_CRE);
        assertThat(testGroupEmployeFrais.gettUserMod()).isEqualTo(UPDATED_T_USER_MOD);
        assertThat(testGroupEmployeFrais.getDtCre()).isEqualTo(UPDATED_DT_CRE);
        assertThat(testGroupEmployeFrais.getDeMod()).isEqualTo(UPDATED_DE_MOD);
    }

    @Test
    @Transactional
    public void updateNonExistingGroupEmployeFrais() throws Exception {
        int databaseSizeBeforeUpdate = groupEmployeFraisRepository.findAll().size();

        // Create the GroupEmployeFrais
        GroupEmployeFraisDTO groupEmployeFraisDTO = groupEmployeFraisMapper.toDto(groupEmployeFrais);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGroupEmployeFraisMockMvc.perform(put("/api/group-employe-frais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupEmployeFraisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GroupEmployeFrais in the database
        List<GroupEmployeFrais> groupEmployeFraisList = groupEmployeFraisRepository.findAll();
        assertThat(groupEmployeFraisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGroupEmployeFrais() throws Exception {
        // Initialize the database
        groupEmployeFraisRepository.saveAndFlush(groupEmployeFrais);

        int databaseSizeBeforeDelete = groupEmployeFraisRepository.findAll().size();

        // Get the groupEmployeFrais
        restGroupEmployeFraisMockMvc.perform(delete("/api/group-employe-frais/{id}", groupEmployeFrais.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GroupEmployeFrais> groupEmployeFraisList = groupEmployeFraisRepository.findAll();
        assertThat(groupEmployeFraisList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupEmployeFrais.class);
        GroupEmployeFrais groupEmployeFrais1 = new GroupEmployeFrais();
        groupEmployeFrais1.setId(1L);
        GroupEmployeFrais groupEmployeFrais2 = new GroupEmployeFrais();
        groupEmployeFrais2.setId(groupEmployeFrais1.getId());
        assertThat(groupEmployeFrais1).isEqualTo(groupEmployeFrais2);
        groupEmployeFrais2.setId(2L);
        assertThat(groupEmployeFrais1).isNotEqualTo(groupEmployeFrais2);
        groupEmployeFrais1.setId(null);
        assertThat(groupEmployeFrais1).isNotEqualTo(groupEmployeFrais2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupEmployeFraisDTO.class);
        GroupEmployeFraisDTO groupEmployeFraisDTO1 = new GroupEmployeFraisDTO();
        groupEmployeFraisDTO1.setId(1L);
        GroupEmployeFraisDTO groupEmployeFraisDTO2 = new GroupEmployeFraisDTO();
        assertThat(groupEmployeFraisDTO1).isNotEqualTo(groupEmployeFraisDTO2);
        groupEmployeFraisDTO2.setId(groupEmployeFraisDTO1.getId());
        assertThat(groupEmployeFraisDTO1).isEqualTo(groupEmployeFraisDTO2);
        groupEmployeFraisDTO2.setId(2L);
        assertThat(groupEmployeFraisDTO1).isNotEqualTo(groupEmployeFraisDTO2);
        groupEmployeFraisDTO1.setId(null);
        assertThat(groupEmployeFraisDTO1).isNotEqualTo(groupEmployeFraisDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(groupEmployeFraisMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(groupEmployeFraisMapper.fromId(null)).isNull();
    }
}
