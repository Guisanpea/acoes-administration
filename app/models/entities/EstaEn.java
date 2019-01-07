

package models.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "esta_en")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstaEn.findAll", query = "SELECT e FROM EstaEn e")
    , @NamedQuery(name = "EstaEn.findByAlumno", query = "SELECT e FROM EstaEn e WHERE e.estaEnPK.alumno = :alumno")
    , @NamedQuery(name = "EstaEn.findByCentro", query = "SELECT e FROM EstaEn e WHERE e.estaEnPK.centro = :centro")
    , @NamedQuery(name = "EstaEn.findByFechaInicio", query = "SELECT e FROM EstaEn e WHERE e.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "EstaEn.findByFechaFin", query = "SELECT e FROM EstaEn e WHERE e.fechaFin = :fechaFin")})
public class EstaEn implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstaEnPK estaEnPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @JoinColumn(name = "alumno", referencedColumnName = "codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alumno alumno1;
    @JoinColumn(name = "centro", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Centro centro1;

    public EstaEn() {
    }

    public EstaEn(EstaEnPK estaEnPK) {
        this.estaEnPK = estaEnPK;
    }

    public EstaEn(EstaEnPK estaEnPK, Date fechaInicio) {
        this.estaEnPK = estaEnPK;
        this.fechaInicio = fechaInicio;
    }

    public EstaEn(int alumno, int centro) {
        this.estaEnPK = new EstaEnPK(alumno, centro);
    }

    public EstaEnPK getEstaEnPK() {
        return estaEnPK;
    }

    public void setEstaEnPK(EstaEnPK estaEnPK) {
        this.estaEnPK = estaEnPK;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Alumno getAlumno1() {
        return alumno1;
    }

    public void setAlumno1(Alumno alumno1) {
        this.alumno1 = alumno1;
    }

    public Centro getCentro1() {
        return centro1;
    }

    public void setCentro1(Centro centro1) {
        this.centro1 = centro1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estaEnPK != null ? estaEnPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstaEn)) {
            return false;
        }
        EstaEn other = (EstaEn) object;
        if ((this.estaEnPK == null && other.estaEnPK != null) || (this.estaEnPK != null && !this.estaEnPK.equals(other.estaEnPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.entities.EstaEn[ estaEnPK=" + estaEnPK + " ]";
    }

}
