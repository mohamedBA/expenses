package com.pfe.expense.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Depense.
 */
@Entity
@Table(name = "depense")
public class Depense implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_f_depense")
    private Integer tFDepense;

    @Column(name = "t_doc_frais")
    private Integer tDocFrais;

    @Column(name = "c_type_depense")
    private String cTypeDepense;

    @Column(name = "dt_fdepense")
    private LocalDate dtFdepense;

    @Column(name = "remarque")
    private String remarque;

    @Column(name = "montant")
    private Float montant;

    @Column(name = "mt_accp")
    private Float mtAccp;

    @Column(name = "mt_depass")
    private Float mtDepass;

    @Column(name = "plafond")
    private Float plafond;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer gettFDepense() {
        return tFDepense;
    }

    public Depense tFDepense(Integer tFDepense) {
        this.tFDepense = tFDepense;
        return this;
    }

    public void settFDepense(Integer tFDepense) {
        this.tFDepense = tFDepense;
    }

    public Integer gettDocFrais() {
        return tDocFrais;
    }

    public Depense tDocFrais(Integer tDocFrais) {
        this.tDocFrais = tDocFrais;
        return this;
    }

    public void settDocFrais(Integer tDocFrais) {
        this.tDocFrais = tDocFrais;
    }

    public String getcTypeDepense() {
        return cTypeDepense;
    }

    public Depense cTypeDepense(String cTypeDepense) {
        this.cTypeDepense = cTypeDepense;
        return this;
    }

    public void setcTypeDepense(String cTypeDepense) {
        this.cTypeDepense = cTypeDepense;
    }

    public LocalDate getDtFdepense() {
        return dtFdepense;
    }

    public Depense dtFdepense(LocalDate dtFdepense) {
        this.dtFdepense = dtFdepense;
        return this;
    }

    public void setDtFdepense(LocalDate dtFdepense) {
        this.dtFdepense = dtFdepense;
    }

    public String getRemarque() {
        return remarque;
    }

    public Depense remarque(String remarque) {
        this.remarque = remarque;
        return this;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public Float getMontant() {
        return montant;
    }

    public Depense montant(Float montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public Float getMtAccp() {
        return mtAccp;
    }

    public Depense mtAccp(Float mtAccp) {
        this.mtAccp = mtAccp;
        return this;
    }

    public void setMtAccp(Float mtAccp) {
        this.mtAccp = mtAccp;
    }

    public Float getMtDepass() {
        return mtDepass;
    }

    public Depense mtDepass(Float mtDepass) {
        this.mtDepass = mtDepass;
        return this;
    }

    public void setMtDepass(Float mtDepass) {
        this.mtDepass = mtDepass;
    }

    public Float getPlafond() {
        return plafond;
    }

    public Depense plafond(Float plafond) {
        this.plafond = plafond;
        return this;
    }

    public void setPlafond(Float plafond) {
        this.plafond = plafond;
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
        Depense depense = (Depense) o;
        if (depense.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), depense.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Depense{" +
            "id=" + getId() +
            ", tFDepense=" + gettFDepense() +
            ", tDocFrais=" + gettDocFrais() +
            ", cTypeDepense='" + getcTypeDepense() + "'" +
            ", dtFdepense='" + getDtFdepense() + "'" +
            ", remarque='" + getRemarque() + "'" +
            ", montant=" + getMontant() +
            ", mtAccp=" + getMtAccp() +
            ", mtDepass=" + getMtDepass() +
            ", plafond=" + getPlafond() +
            "}";
    }
}
