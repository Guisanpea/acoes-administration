

package models.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Embeddable
public class ApadrinamientoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "apadrinado")
    private int apadrinado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "padrino")
    private int padrino;

    public ApadrinamientoPK() {
    }

    public ApadrinamientoPK(int id, int apadrinado, int padrino) {
        this.id = id;
        this.apadrinado = apadrinado;
        this.padrino = padrino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApadrinado() {
        return apadrinado;
    }

    public void setApadrinado(int apadrinado) {
        this.apadrinado = apadrinado;
    }

    public int getPadrino() {
        return padrino;
    }

    public void setPadrino(int padrino) {
        this.padrino = padrino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) apadrinado;
        hash += (int) padrino;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApadrinamientoPK)) {
            return false;
        }
        ApadrinamientoPK other = (ApadrinamientoPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.apadrinado != other.apadrinado) {
            return false;
        }
        if (this.padrino != other.padrino) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.entities.ApadrinamientoPK[ id=" + id + ", apadrinado=" + apadrinado + ", padrino=" + padrino + " ]";
    }

}
