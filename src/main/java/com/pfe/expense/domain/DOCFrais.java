package com.pfe.expense.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DOCFrais.
 */
@Entity
@Table(name = "doc_frais")
public class DOCFrais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_doc_frais")
    private Long tDocFrais;

    @Column(name = "total_frais")
    private Float totalFrais;

    @Column(name = "dt_soumission")
    private LocalDate dtSoumission;

    @Column(name = "dt_validation_partiele")
    private LocalDate dtValidationPartiele;

    @Column(name = "dt_validation")
    private LocalDate dtValidation;

    @Column(name = "dt_payement")
    private LocalDate dtPayement;

    @Column(name = "dt_refus")
    private LocalDate dtRefus;

    @Column(name = "motif_refus")
    private String motifRefus;

    @Column(name = "total_froute")
    private Integer totalFroute;

    @Column(name = "total_depense")
    private Float totalDepense;

    @Column(name = "t_user_cre")
    private Integer tUserCre;

    @Column(name = "t_user_modif")
    private Integer tUserModif;

    @Column(name = "t_p_user")
    private Integer tPUser;

    @Column(name = "total_nbre_km")
    private Integer totalNbreKm;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long gettDocFrais() {
        return tDocFrais;
    }

    public DOCFrais tDocFrais(Long tDocFrais) {
        this.tDocFrais = tDocFrais;
        return this;
    }

    public void settDocFrais(Long tDocFrais) {
        this.tDocFrais = tDocFrais;
    }

    public Float getTotalFrais() {
        return totalFrais;
    }

    public DOCFrais totalFrais(Float totalFrais) {
        this.totalFrais = totalFrais;
        return this;
    }

    public void setTotalFrais(Float totalFrais) {
        this.totalFrais = totalFrais;
    }

    public LocalDate getDtSoumission() {
        return dtSoumission;
    }

    public DOCFrais dtSoumission(LocalDate dtSoumission) {
        this.dtSoumission = dtSoumission;
        return this;
    }

    public void setDtSoumission(LocalDate dtSoumission) {
        this.dtSoumission = dtSoumission;
    }

    public LocalDate getDtValidationPartiele() {
        return dtValidationPartiele;
    }

    public DOCFrais dtValidationPartiele(LocalDate dtValidationPartiele) {
        this.dtValidationPartiele = dtValidationPartiele;
        return this;
    }

    public void setDtValidationPartiele(LocalDate dtValidationPartiele) {
        this.dtValidationPartiele = dtValidationPartiele;
    }

    public LocalDate getDtValidation() {
        return dtValidation;
    }

    public DOCFrais dtValidation(LocalDate dtValidation) {
        this.dtValidation = dtValidation;
        return this;
    }

    public void setDtValidation(LocalDate dtValidation) {
        this.dtValidation = dtValidation;
    }

    public LocalDate getDtPayement() {
        return dtPayement;
    }

    public DOCFrais dtPayement(LocalDate dtPayement) {
        this.dtPayement = dtPayement;
        return this;
    }

    public void setDtPayement(LocalDate dtPayement) {
        this.dtPayement = dtPayement;
    }

    public LocalDate getDtRefus() {
        return dtRefus;
    }

    public DOCFrais dtRefus(LocalDate dtRefus) {
        this.dtRefus = dtRefus;
        return this;
    }

    public void setDtRefus(LocalDate dtRefus) {
        this.dtRefus = dtRefus;
    }

    public String getMotifRefus() {
        return motifRefus;
    }

    public DOCFrais motifRefus(String motifRefus) {
        this.motifRefus = motifRefus;
        return this;
    }

    public void setMotifRefus(String motifRefus) {
        this.motifRefus = motifRefus;
    }

    public Integer getTotalFroute() {
        return totalFroute;
    }

    public DOCFrais totalFroute(Integer totalFroute) {
        this.totalFroute = totalFroute;
        return this;
    }

    public void setTotalFroute(Integer totalFroute) {
        this.totalFroute = totalFroute;
    }

    public Float getTotalDepense() {
        return totalDepense;
    }

    public DOCFrais totalDepense(Float totalDepense) {
        this.totalDepense = totalDepense;
        return this;
    }

    public void setTotalDepense(Float totalDepense) {
        this.totalDepense = totalDepense;
    }

    public Integer gettUserCre() {
        return tUserCre;
    }

    public DOCFrais tUserCre(Integer tUserCre) {
        this.tUserCre = tUserCre;
        return this;
    }

    public void settUserCre(Integer tUserCre) {
        this.tUserCre = tUserCre;
    }

    public Integer gettUserModif() {
        return tUserModif;
    }

    public DOCFrais tUserModif(Integer tUserModif) {
        this.tUserModif = tUserModif;
        return this;
    }

    public void settUserModif(Integer tUserModif) {
        this.tUserModif = tUserModif;
    }

    public Integer gettPUser() {
        return tPUser;
    }

    public DOCFrais tPUser(Integer tPUser) {
        this.tPUser = tPUser;
        return this;
    }

    public void settPUser(Integer tPUser) {
        this.tPUser = tPUser;
    }

    public Integer getTotalNbreKm() {
        return totalNbreKm;
    }

    public DOCFrais totalNbreKm(Integer totalNbreKm) {
        this.totalNbreKm = totalNbreKm;
        return this;
    }

    public void setTotalNbreKm(Integer totalNbreKm) {
        this.totalNbreKm = totalNbreKm;
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
        DOCFrais dOCFrais = (DOCFrais) o;
        if (dOCFrais.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dOCFrais.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DOCFrais{" +
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
