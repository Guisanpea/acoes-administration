

package models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Embeddable
public class AdministracionApadrinamientoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "agente")
    private int agente;

    public AdministracionApadrinamientoPK() {
    }

    public AdministracionApadrinamientoPK(Date fechaInicio, int agente) {
        this.fechaInicio = fechaInicio;
        this.agente = agente;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getAgente() {
        return agente;
    }

    public void setAgente(int agente) {
        this.agente = agente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fechaInicio != null ? fechaInicio.hashCode() : 0);
        hash += (int) agente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdministracionApadrinamientoPK)) {
            return false;
        }
        AdministracionApadrinamientoPK other = (AdministracionApadrinamientoPK) object;
        if ((this.fechaInicio == null && other.fechaInicio != null) || (this.fechaInicio != null && !this.fechaInicio.equals(other.fechaInicio))) {
            return false;
        }
        if (this.agente != other.agente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.entities.AdministracionApadrinamientoPK[ fechaInicio=" + fechaInicio + ", agente=" + agente + " ]";
    }

}
