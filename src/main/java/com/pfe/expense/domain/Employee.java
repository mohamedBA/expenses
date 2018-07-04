package com.pfe.expense.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_employe")
    private Integer tEmploye;

    @Column(name = "t_socab")
    private Integer tSocab;

    @Column(name = "mat_empl")
    private String matEmpl;

    @Column(name = "id_carte")
    private Integer idCarte;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "n_cin")
    private Integer nCin;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "photo_content_type")
    private String photoContentType;

    @Column(name = "date_deliv_cin")
    private LocalDate dateDelivCin;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer gettEmploye() {
        return tEmploye;
    }

    public Employee tEmploye(Integer tEmploye) {
        this.tEmploye = tEmploye;
        return this;
    }

    public void settEmploye(Integer tEmploye) {
        this.tEmploye = tEmploye;
    }

    public Integer gettSocab() {
        return tSocab;
    }

    public Employee tSocab(Integer tSocab) {
        this.tSocab = tSocab;
        return this;
    }

    public void settSocab(Integer tSocab) {
        this.tSocab = tSocab;
    }

    public String getMatEmpl() {
        return matEmpl;
    }

    public Employee matEmpl(String matEmpl) {
        this.matEmpl = matEmpl;
        return this;
    }

    public void setMatEmpl(String matEmpl) {
        this.matEmpl = matEmpl;
    }

    public Integer getIdCarte() {
        return idCarte;
    }

    public Employee idCarte(Integer idCarte) {
        this.idCarte = idCarte;
        return this;
    }

    public void setIdCarte(Integer idCarte) {
        this.idCarte = idCarte;
    }

    public String getNom() {
        return nom;
    }

    public Employee nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Employee prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getnCin() {
        return nCin;
    }

    public Employee nCin(Integer nCin) {
        this.nCin = nCin;
        return this;
    }

    public void setnCin(Integer nCin) {
        this.nCin = nCin;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public Employee photo(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public Employee photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public LocalDate getDateDelivCin() {
        return dateDelivCin;
    }

    public Employee dateDelivCin(LocalDate dateDelivCin) {
        this.dateDelivCin = dateDelivCin;
        return this;
    }

    public void setDateDelivCin(LocalDate dateDelivCin) {
        this.dateDelivCin = dateDelivCin;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        if (employee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", tEmploye=" + gettEmploye() +
            ", tSocab=" + gettSocab() +
            ", matEmpl='" + getMatEmpl() + "'" +
            ", idCarte=" + getIdCarte() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", nCin=" + getnCin() +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + getPhotoContentType() + "'" +
            ", dateDelivCin='" + getDateDelivCin() + "'" +
            "}";
    }
}
