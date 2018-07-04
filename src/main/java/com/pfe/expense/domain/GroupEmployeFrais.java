package com.pfe.expense.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A GroupEmployeFrais.
 */
@Entity
@Table(name = "group_employe_frais")
public class GroupEmployeFrais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plafond")
    private Float plafond;

    @Column(name = "dt_deb")
    private LocalDate dtDeb;

    @Column(name = "dt_fin")
    private LocalDate dtFin;

    @Column(name = "t_user_cre")
    private Integer tUserCre;

    @Column(name = "t_user_mod")
    private Integer tUserMod;

    @Column(name = "dt_cre")
    private LocalDate dtCre;

    @Column(name = "de_mod")
    private LocalDate deMod;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPlafond() {
        return plafond;
    }

    public GroupEmployeFrais plafond(Float plafond) {
        this.plafond = plafond;
        return this;
    }

    public void setPlafond(Float plafond) {
        this.plafond = plafond;
    }

    public LocalDate getDtDeb() {
        return dtDeb;
    }

    public GroupEmployeFrais dtDeb(LocalDate dtDeb) {
        this.dtDeb = dtDeb;
        return this;
    }

    public void setDtDeb(LocalDate dtDeb) {
        this.dtDeb = dtDeb;
    }

    public LocalDate getDtFin() {
        return dtFin;
    }

    public GroupEmployeFrais dtFin(LocalDate dtFin) {
        this.dtFin = dtFin;
        return this;
    }

    public void setDtFin(LocalDate dtFin) {
        this.dtFin = dtFin;
    }

    public Integer gettUserCre() {
        return tUserCre;
    }

    public GroupEmployeFrais tUserCre(Integer tUserCre) {
        this.tUserCre = tUserCre;
        return this;
    }

    public void settUserCre(Integer tUserCre) {
        this.tUserCre = tUserCre;
    }

    public Integer gettUserMod() {
        return tUserMod;
    }

    public GroupEmployeFrais tUserMod(Integer tUserMod) {
        this.tUserMod = tUserMod;
        return this;
    }

    public void settUserMod(Integer tUserMod) {
        this.tUserMod = tUserMod;
    }

    public LocalDate getDtCre() {
        return dtCre;
    }

    public GroupEmployeFrais dtCre(LocalDate dtCre) {
        this.dtCre = dtCre;
        return this;
    }

    public void setDtCre(LocalDate dtCre) {
        this.dtCre = dtCre;
    }

    public LocalDate getDeMod() {
        return deMod;
    }

    public GroupEmployeFrais deMod(LocalDate deMod) {
        this.deMod = deMod;
        return this;
    }

    public void setDeMod(LocalDate deMod) {
        this.deMod = deMod;
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
        GroupEmployeFrais groupEmployeFrais = (GroupEmployeFrais) o;
        if (groupEmployeFrais.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), groupEmployeFrais.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GroupEmployeFrais{" +
            "id=" + getId() +
            ", plafond=" + getPlafond() +
            ", dtDeb='" + getDtDeb() + "'" +
            ", dtFin='" + getDtFin() + "'" +
            ", tUserCre=" + gettUserCre() +
            ", tUserMod=" + gettUserMod() +
            ", dtCre='" + getDtCre() + "'" +
            ", deMod='" + getDeMod() + "'" +
            "}";
    }
}
