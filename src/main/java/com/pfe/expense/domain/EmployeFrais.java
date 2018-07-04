package com.pfe.expense.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A EmployeFrais.
 */
@Entity
@Table(name = "employe_frais")
public class EmployeFrais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "st")
    private String st;

    @Column(name = "mat_veh")
    private String matVeh;

    @Column(name = "marque_veh")
    private String marqueVeh;

    @Column(name = "km")
    private Integer km;

    @Column(name = "dern_km")
    private Integer dernKm;

    @Column(name = "forfait_km")
    private Float forfaitKm;

    @Column(name = "c_forfait")
    private String cForfait;

    @Column(name = "type_voiture")
    private Integer typeVoiture;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSt() {
        return st;
    }

    public EmployeFrais st(String st) {
        this.st = st;
        return this;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getMatVeh() {
        return matVeh;
    }

    public EmployeFrais matVeh(String matVeh) {
        this.matVeh = matVeh;
        return this;
    }

    public void setMatVeh(String matVeh) {
        this.matVeh = matVeh;
    }

    public String getMarqueVeh() {
        return marqueVeh;
    }

    public EmployeFrais marqueVeh(String marqueVeh) {
        this.marqueVeh = marqueVeh;
        return this;
    }

    public void setMarqueVeh(String marqueVeh) {
        this.marqueVeh = marqueVeh;
    }

    public Integer getKm() {
        return km;
    }

    public EmployeFrais km(Integer km) {
        this.km = km;
        return this;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public Integer getDernKm() {
        return dernKm;
    }

    public EmployeFrais dernKm(Integer dernKm) {
        this.dernKm = dernKm;
        return this;
    }

    public void setDernKm(Integer dernKm) {
        this.dernKm = dernKm;
    }

    public Float getForfaitKm() {
        return forfaitKm;
    }

    public EmployeFrais forfaitKm(Float forfaitKm) {
        this.forfaitKm = forfaitKm;
        return this;
    }

    public void setForfaitKm(Float forfaitKm) {
        this.forfaitKm = forfaitKm;
    }

    public String getcForfait() {
        return cForfait;
    }

    public EmployeFrais cForfait(String cForfait) {
        this.cForfait = cForfait;
        return this;
    }

    public void setcForfait(String cForfait) {
        this.cForfait = cForfait;
    }

    public Integer getTypeVoiture() {
        return typeVoiture;
    }

    public EmployeFrais typeVoiture(Integer typeVoiture) {
        this.typeVoiture = typeVoiture;
        return this;
    }

    public void setTypeVoiture(Integer typeVoiture) {
        this.typeVoiture = typeVoiture;
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
        EmployeFrais employeFrais = (EmployeFrais) o;
        if (employeFrais.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employeFrais.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmployeFrais{" +
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
