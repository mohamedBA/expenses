package com.pfe.expense.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EmployeFrais entity.
 */
public class EmployeFraisDTO implements Serializable {

    private Long id;

    private String st;

    private String matVeh;

    private String marqueVeh;

    private Integer km;

    private Integer dernKm;

    private Float forfaitKm;

    private String cForfait;

    private Integer typeVoiture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getMatVeh() {
        return matVeh;
    }

    public void setMatVeh(String matVeh) {
        this.matVeh = matVeh;
    }

    public String getMarqueVeh() {
        return marqueVeh;
    }

    public void setMarqueVeh(String marqueVeh) {
        this.marqueVeh = marqueVeh;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public Integer getDernKm() {
        return dernKm;
    }

    public void setDernKm(Integer dernKm) {
        this.dernKm = dernKm;
    }

    public Float getForfaitKm() {
        return forfaitKm;
    }

    public void setForfaitKm(Float forfaitKm) {
        this.forfaitKm = forfaitKm;
    }

    public String getcForfait() {
        return cForfait;
    }

    public void setcForfait(String cForfait) {
        this.cForfait = cForfait;
    }

    public Integer getTypeVoiture() {
        return typeVoiture;
    }

    public void setTypeVoiture(Integer typeVoiture) {
        this.typeVoiture = typeVoiture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeFraisDTO employeFraisDTO = (EmployeFraisDTO) o;
        if (employeFraisDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employeFraisDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmployeFraisDTO{" +
            "id=" + getId() +
            ", st='" + getSt() + "'" +
            ", matVeh='" + getMatVeh() + "'" +
            ", marqueVeh='" + getMarqueVeh() + "'" +
            ", km=" + getKm() +
            ", dernKm=" + getDernKm() +
            ", forfaitKm=" + getForfaitKm() +
            ", cForfait='" + getcForfait() + "'" +
            ", typeVoiture=" + getTypeVoiture() +
            "}";
    }
}
