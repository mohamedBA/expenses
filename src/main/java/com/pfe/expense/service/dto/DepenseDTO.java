package com.pfe.expense.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Depense entity.
 */
public class DepenseDTO implements Serializable {

    private Long id;

    private Integer tFDepense;

    private Integer tDocFrais;

    private String cTypeDepense;

    private LocalDate dtFdepense;

    private String remarque;

    private Float montant;

    private Float mtAccp;

    private Float mtDepass;

    private Float plafond;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer gettFDepense() {
        return tFDepense;
    }

    public void settFDepense(Integer tFDepense) {
        this.tFDepense = tFDepense;
    }

    public Integer gettDocFrais() {
        return tDocFrais;
    }

    public void settDocFrais(Integer tDocFrais) {
        this.tDocFrais = tDocFrais;
    }

    public String getcTypeDepense() {
        return cTypeDepense;
    }

    public void setcTypeDepense(String cTypeDepense) {
        this.cTypeDepense = cTypeDepense;
    }

    public LocalDate getDtFdepense() {
        return dtFdepense;
    }

    public void setDtFdepense(LocalDate dtFdepense) {
        this.dtFdepense = dtFdepense;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public Float getMtAccp() {
        return mtAccp;
    }

    public void setMtAccp(Float mtAccp) {
        this.mtAccp = mtAccp;
    }

    public Float getMtDepass() {
        return mtDepass;
    }

    public void setMtDepass(Float mtDepass) {
        this.mtDepass = mtDepass;
    }

    public Float getPlafond() {
        return plafond;
    }

    public void setPlafond(Float plafond) {
        this.plafond = plafond;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DepenseDTO depenseDTO = (DepenseDTO) o;
        if (depenseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), depenseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DepenseDTO{" +
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
