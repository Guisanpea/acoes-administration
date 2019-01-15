

package models.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Embeddable
public class EnvioPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "apadrinamiento_id")
    private int apadrinamientoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "apadrinamiento_apadrinado")
    private int apadrinamientoApadrinado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "apadrinamiento_padrino")
    private int apadrinamientoPadrino;

    public EnvioPK() {
    }

    public EnvioPK(int id, int apadrinamientoId, int apadrinamientoApadrinado, int apadrinamientoPadrino) {
        this.id = id;
        this.apadrinamientoId = apadrinamientoId;
        this.apadrinamientoApadrinado = apadrinamientoApadrinado;
        this.apadrinamientoPadrino = apadrinamientoPadrino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApadrinamientoId() {
        return apadrinamientoId;
    }

    public void setApadrinamientoId(int apadrinamientoId) {
        this.apadrinamientoId = apadrinamientoId;
    }

    public int getApadrinamientoApadrinado() {
        return apadrinamientoApadrinado;
    }

    public void setApadrinamientoApadrinado(int apadrinamientoApadrinado) {
        this.apadrinamientoApadrinado = apadrinamientoApadrinado;
    }

    public int getApadrinamientoPadrino() {
        return apadrinamientoPadrino;
    }

    public void setApadrinamientoPadrino(int apadrinamientoPadrino) {
        this.apadrinamientoPadrino = apadrinamientoPadrino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) apadrinamientoId;
        hash += (int) apadrinamientoApadrinado;
        hash += (int) apadrinamientoPadrino;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnvioPK)) {
            return false;
        }
        EnvioPK other = (EnvioPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.apadrinamientoId != other.apadrinamientoId) {
            return false;
        }
        if (this.apadrinamientoApadrinado != other.apadrinamientoApadrinado) {
            return false;
        }
        if (this.apadrinamientoPadrino != other.apadrinamientoPadrino) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.entities.EnvioPK[ id=" + id + ", apadrinamientoId=" + apadrinamientoId + ", apadrinamientoApadrinado=" + apadrinamientoApadrinado + ", apadrinamientoPadrino=" + apadrinamientoPadrino + " ]";
    }

}
