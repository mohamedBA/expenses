package com.pfe.expense.web.rest;

import com.pfe.expense.ExpensesApp;

import com.pfe.expense.domain.Employee;
import com.pfe.expense.repository.EmployeeRepository;
import com.pfe.expense.service.EmployeeService;
import com.pfe.expense.service.dto.EmployeeDTO;
import com.pfe.expense.service.mapper.EmployeeMapper;
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
import org.springframework.util.Base64Utils;

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
 * Test class for the EmployeeResource REST controller.
 *
 * @see EmployeeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExpensesApp.class)
public class EmployeeResourceIntTest {

    private static final Integer DEFAULT_T_EMPLOYE = 1;
    private static final Integer UPDATED_T_EMPLOYE = 2;

    private static final Integer DEFAULT_T_SOCAB = 1;
    private static final Integer UPDATED_T_SOCAB = 2;

    private static final String DEFAULT_MAT_EMPL = "AAAAAAAAAA";
    private static final String UPDATED_MAT_EMPL = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_CARTE = 1;
    private static final Integer UPDATED_ID_CARTE = 2;

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final Integer DEFAULT_N_CIN = 1;
    private static final Integer UPDATED_N_CIN = 2;

    private static final byte[] DEFAULT_PHOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_CONTENT_TYPE = "image/png";

    private static final LocalDate DEFAULT_DATE_DELIV_CIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DELIV_CIN = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private EmployeeRepository employeeRepository;


    @Autowired
    private EmployeeMapper employeeMapper;
    

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEmployeeMockMvc;

    private Employee employee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmployeeResource employeeResource = new EmployeeResource(employeeService);
        this.restEmployeeMockMvc = MockMvcBuilders.standaloneSetup(employeeResource)
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
    public static Employee createEntity(EntityManager em) {
        Employee employee = new Employee()
            .tEmploye(DEFAULT_T_EMPLOYE)
            .tSocab(DEFAULT_T_SOCAB)
            .matEmpl(DEFAULT_MAT_EMPL)
            .idCarte(DEFAULT_ID_CARTE)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .nCin(DEFAULT_N_CIN)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .dateDelivCin(DEFAULT_DATE_DELIV_CIN);
        return employee;
    }

    @Before
    public void initTest() {
        employee = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmployee() throws Exception {
        int databaseSizeBeforeCreate = employeeRepository.findAll().size();

        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);
        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isCreated());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeCreate + 1);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.gettEmploye()).isEqualTo(DEFAULT_T_EMPLOYE);
        assertThat(testEmployee.gettSocab()).isEqualTo(DEFAULT_T_SOCAB);
        assertThat(testEmployee.getMatEmpl()).isEqualTo(DEFAULT_MAT_EMPL);
        assertThat(testEmployee.getIdCarte()).isEqualTo(DEFAULT_ID_CARTE);
        assertThat(testEmployee.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testEmployee.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testEmployee.getnCin()).isEqualTo(DEFAULT_N_CIN);
        assertThat(testEmployee.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testEmployee.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testEmployee.getDateDelivCin()).isEqualTo(DEFAULT_DATE_DELIV_CIN);
    }

    @Test
    @Transactional
    public void createEmployeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = employeeRepository.findAll().size();

        // Create the Employee with an existing ID
        employee.setId(1L);
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEmployees() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get all the employeeList
        restEmployeeMockMvc.perform(get("/api/employees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employee.getId().intValue())))
            .andExpect(jsonPath("$.[*].tEmploye").value(hasItem(DEFAULT_T_EMPLOYE)))
            .andExpect(jsonPath("$.[*].tSocab").value(hasItem(DEFAULT_T_SOCAB)))
            .andExpect(jsonPath("$.[*].matEmpl").value(hasItem(DEFAULT_MAT_EMPL.toString())))
            .andExpect(jsonPath("$.[*].idCarte").value(hasItem(DEFAULT_ID_CARTE)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].nCin").value(hasItem(DEFAULT_N_CIN)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].dateDelivCin").value(hasItem(DEFAULT_DATE_DELIV_CIN.toString())));
    }
    

    @Test
    @Transactional
    public void getEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        // Get the employee
        restEmployeeMockMvc.perform(get("/api/employees/{id}", employee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(employee.getId().intValue()))
            .andExpect(jsonPath("$.tEmploye").value(DEFAULT_T_EMPLOYE))
            .andExpect(jsonPath("$.tSocab").value(DEFAULT_T_SOCAB))
            .andExpect(jsonPath("$.matEmpl").value(DEFAULT_MAT_EMPL.toString()))
            .andExpect(jsonPath("$.idCarte").value(DEFAULT_ID_CARTE))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM.toString()))
            .andExpect(jsonPath("$.nCin").value(DEFAULT_N_CIN))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.dateDelivCin").value(DEFAULT_DATE_DELIV_CIN.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEmployee() throws Exception {
        // Get the employee
        restEmployeeMockMvc.perform(get("/api/employees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // Update the employee
        Employee updatedEmployee = employeeRepository.findById(employee.getId()).get();
        // Disconnect from session so that the updates on updatedEmployee are not directly saved in db
        em.detach(updatedEmployee);
        updatedEmployee
            .tEmploye(UPDATED_T_EMPLOYE)
            .tSocab(UPDATED_T_SOCAB)
            .matEmpl(UPDATED_MAT_EMPL)
            .idCarte(UPDATED_ID_CARTE)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .nCin(UPDATED_N_CIN)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .dateDelivCin(UPDATED_DATE_DELIV_CIN);
        EmployeeDTO employeeDTO = employeeMapper.toDto(updatedEmployee);

        restEmployeeMockMvc.perform(put("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isOk());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.gettEmploye()).isEqualTo(UPDATED_T_EMPLOYE);
        assertThat(testEmployee.gettSocab()).isEqualTo(UPDATED_T_SOCAB);
        assertThat(testEmployee.getMatEmpl()).isEqualTo(UPDATED_MAT_EMPL);
        assertThat(testEmployee.getIdCarte()).isEqualTo(UPDATED_ID_CARTE);
        assertThat(testEmployee.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testEmployee.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testEmployee.getnCin()).isEqualTo(UPDATED_N_CIN);
        assertThat(testEmployee.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testEmployee.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testEmployee.getDateDelivCin()).isEqualTo(UPDATED_DATE_DELIV_CIN);
    }

    @Test
    @Transactional
    public void updateNonExistingEmployee() throws Exception {
        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEmployeeMockMvc.perform(put("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmployee() throws Exception {
        // Initialize the database
        employeeRepository.saveAndFlush(employee);

        int databaseSizeBeforeDelete = employeeRepository.findAll().size();

        // Get the employee
        restEmployeeMockMvc.perform(delete("/api/employees/{id}", employee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Employee.class);
        Employee employee1 = new Employee();
        employee1.setId(1L);
        Employee employee2 = new Employee();
        employee2.setId(employee1.getId());
        assertThat(employee1).isEqualTo(employee2);
        employee2.setId(2L);
        assertThat(employee1).isNotEqualTo(employee2);
        employee1.setId(null);
        assertThat(employee1).isNotEqualTo(employee2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeDTO.class);
        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        employeeDTO1.setId(1L);
        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
        employeeDTO2.setId(employeeDTO1.getId());
        assertThat(employeeDTO1).isEqualTo(employeeDTO2);
        employeeDTO2.setId(2L);
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
        employeeDTO1.setId(null);
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(employeeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(employeeMapper.fromId(null)).isNull();
    }
}
