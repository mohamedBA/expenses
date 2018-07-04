package com.pfe.expense.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Employee entity.
 */
public class EmployeeDTO implements Serializable {

    private Long id;

    private Integer tEmploye;

    private Integer tSocab;

    private String matEmpl;

    private Integer idCarte;

    private String nom;

    private String prenom;

    private Integer nCin;

    @Lob
    private byte[] photo;
    private String photoContentType;

    private LocalDate dateDelivCin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer gettEmploye() {
        return tEmploye;
    }

    public void settEmploye(Integer tEmploye) {
        this.tEmploye = tEmploye;
    }

    public Integer gettSocab() {
        return tSocab;
    }

    public void settSocab(Integer tSocab) {
        this.tSocab = tSocab;
    }

    public String getMatEmpl() {
        return matEmpl;
    }

    public void setMatEmpl(String matEmpl) {
        this.matEmpl = matEmpl;
    }

    public Integer getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(Integer idCarte) {
        this.idCarte = idCarte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getnCin() {
        return nCin;
    }

    public void setnCin(Integer nCin) {
        this.nCin = nCin;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public LocalDate getDateDelivCin() {
        return dateDelivCin;
    }

    public void setDateDelivCin(LocalDate dateDelivCin) {
        this.dateDelivCin = dateDelivCin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeeDTO employeeDTO = (EmployeeDTO) o;
        if (employeeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employeeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
            "id=" + getId() +
            ", tEmploye=" + gettEmploye() +
            ", tSocab=" + gettSocab() +
            ", matEmpl='" + getMatEmpl() + "'" +
            ", idCarte=" + getIdCarte() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", nCin=" + getnCin() +
            ", photo='" + getPhoto() + "'" +
            ", dateDelivCin='" + getDateDelivCin() + "'" +
            "}";
    }
}
