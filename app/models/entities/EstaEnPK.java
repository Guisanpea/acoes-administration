

package models.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;


@Embeddable
public class EstaEnPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "alumno")
    private int alumno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "centro")
    private int centro;

    public EstaEnPK() {
    }

    public EstaEnPK(int alumno, int centro) {
        this.alumno = alumno;
        this.centro = centro;
    }

    public int getAlumno() {
        return alumno;
    }

    public void setAlumno(int alumno) {
        this.alumno = alumno;
    }

    public int getCentro() {
        return centro;
    }

    public void setCentro(int centro) {
        this.centro = centro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) alumno;
        hash += (int) centro;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstaEnPK)) {
            return false;
        }
        EstaEnPK other = (EstaEnPK) object;
        if (this.alumno != other.alumno) {
            return false;
        }
        if (this.centro != other.centro) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.entities.EstaEnPK[ alumno=" + alumno + ", centro=" + centro + " ]";
    }

}
