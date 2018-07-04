package com.pfe.expense.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the GroupEmployeFrais entity.
 */
public class GroupEmployeFraisDTO implements Serializable {

    private Long id;

    private Float plafond;

    private LocalDate dtDeb;

    private LocalDate dtFin;

    private Integer tUserCre;

    private Integer tUserMod;

    private LocalDate dtCre;

    private LocalDate deMod;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPlafond() {
        return plafond;
    }

    public void setPlafond(Float plafond) {
        this.plafond = plafond;
    }

    public LocalDate getDtDeb() {
        return dtDeb;
    }

    public void setDtDeb(LocalDate dtDeb) {
        this.dtDeb = dtDeb;
    }

    public LocalDate getDtFin() {
        return dtFin;
    }

    public void setDtFin(LocalDate dtFin) {
        this.dtFin = dtFin;
    }

    public Integer gettUserCre() {
        return tUserCre;
    }

    public void settUserCre(Integer tUserCre) {
        this.tUserCre = tUserCre;
    }

    public Integer gettUserMod() {
        return tUserMod;
    }

    public void settUserMod(Integer tUserMod) {
        this.tUserMod = tUserMod;
    }

    public LocalDate getDtCre() {
        return dtCre;
    }

    public void setDtCre(LocalDate dtCre) {
        this.dtCre = dtCre;
    }

    public LocalDate getDeMod() {
        return deMod;
    }

    public void setDeMod(LocalDate deMod) {
        this.deMod = deMod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GroupEmployeFraisDTO groupEmployeFraisDTO = (GroupEmployeFraisDTO) o;
        if (groupEmployeFraisDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), groupEmployeFraisDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GroupEmployeFraisDTO{" +
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
