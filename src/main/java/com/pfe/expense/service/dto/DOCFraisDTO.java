package com.pfe.expense.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DOCFrais entity.
 */
public class DOCFraisDTO implements Serializable {

    private Long id;

    private Long tDocFrais;

    private Float totalFrais;

    private LocalDate dtSoumission;

    private LocalDate dtValidationPartiele;

    private LocalDate dtValidation;

    private LocalDate dtPayement;

    private LocalDate dtRefus;

    private String motifRefus;

    private Integer totalFroute;

    private Float totalDepense;

    private Integer tUserCre;

    private Integer tUserModif;

    private Integer tPUser;

    private Integer totalNbreKm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long gettDocFrais() {
        return tDocFrais;
    }

    public void settDocFrais(Long tDocFrais) {
        this.tDocFrais = tDocFrais;
    }

    public Float getTotalFrais() {
        return totalFrais;
    }

    public void setTotalFrais(Float totalFrais) {
        this.totalFrais = totalFrais;
    }

    public LocalDate getDtSoumission() {
        return dtSoumission;
    }

    public void setDtSoumission(LocalDate dtSoumission) {
        this.dtSoumission = dtSoumission;
    }

    public LocalDate getDtValidationPartiele() {
        return dtValidationPartiele;
    }

    public void setDtValidationPartiele(LocalDate dtValidationPartiele) {
        this.dtValidationPartiele = dtValidationPartiele;
    }

    public LocalDate getDtValidation() {
        return dtValidation;
    }

    public void setDtValidation(LocalDate dtValidation) {
        this.dtValidation = dtValidation;
    }

    public LocalDate getDtPayement() {
        return dtPayement;
    }

    public void setDtPayement(LocalDate dtPayement) {
        this.dtPayement = dtPayement;
    }

    public LocalDate getDtRefus() {
        return dtRefus;
    }

    public void setDtRefus(LocalDate dtRefus) {
        this.dtRefus = dtRefus;
    }

    public String getMotifRefus() {
        return motifRefus;
    }

    public void setMotifRefus(String motifRefus) {
        this.motifRefus = motifRefus;
    }

    public Integer getTotalFroute() {
        return totalFroute;
    }

    public void setTotalFroute(Integer totalFroute) {
        this.totalFroute = totalFroute;
    }

    public Float getTotalDepense() {
        return totalDepense;
    }

    public void setTotalDepense(Float totalDepense) {
        this.totalDepense = totalDepense;
    }

    public Integer gettUserCre() {
        return tUserCre;
    }

    public void settUserCre(Integer tUserCre) {
        this.tUserCre = tUserCre;
    }

    public Integer gettUserModif() {
        return tUserModif;
    }

    public void settUserModif(Integer tUserModif) {
        this.tUserModif = tUserModif;
    }

    public Integer gettPUser() {
        return tPUser;
    }

    public void settPUser(Integer tPUser) {
        this.tPUser = tPUser;
    }

    public Integer getTotalNbreKm() {
        return totalNbreKm;
    }

    public void setTotalNbreKm(Integer totalNbreKm) {
        this.totalNbreKm = totalNbreKm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DOCFraisDTO dOCFraisDTO = (DOCFraisDTO) o;
        if (dOCFraisDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dOCFraisDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DOCFraisDTO{" +
            "id=" + getId() +
            ", tDocFrais=" + gettDocFrais() +
            ", totalFrais=" + getTotalFrais() +
            ", dtSoumission='" + getDtSoumission() + "'" +
            ", dtValidationPartiele='" + getDtValidationPartiele() + "'" +
            ", dtValidation='" + getDtValidation() + "'" +
            ", dtPayement='" + getDtPayement() + "'" +
            ", dtRefus='" + getDtRefus() + "'" +
            ", motifRefus='" + getMotifRefus() + "'" +
            ", totalFroute=" + getTotalFroute() +
            ", totalDepense=" + getTotalDepense() +
            ", tUserCre=" + gettUserCre() +
            ", tUserModif=" + gettUserModif() +
            ", tPUser=" + gettPUser() +
            ", totalNbreKm=" + getTotalNbreKm() +
            "}";
    }
}
