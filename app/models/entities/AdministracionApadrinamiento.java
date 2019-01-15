

package models.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "administracion_apadrinamiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdministracionApadrinamiento.findAll", query = "SELECT a FROM AdministracionApadrinamiento a")
    , @NamedQuery(name = "AdministracionApadrinamiento.findByFechaInicio", query = "SELECT a FROM AdministracionApadrinamiento a WHERE a.administracionApadrinamientoPK.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "AdministracionApadrinamiento.findByFechaFin", query = "SELECT a FROM AdministracionApadrinamiento a WHERE a.fechaFin = :fechaFin")
    , @NamedQuery(name = "AdministracionApadrinamiento.findByAgente", query = "SELECT a FROM AdministracionApadrinamiento a WHERE a.administracionApadrinamientoPK.agente = :agente")})
public class AdministracionApadrinamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AdministracionApadrinamientoPK administracionApadrinamientoPK;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @JoinColumns({
        @JoinColumn(name = "apadrinamiento_id", referencedColumnName = "id")
        , @JoinColumn(name = "apadrinamiento_apadrinado", referencedColumnName = "apadrinado")
        , @JoinColumn(name = "apadrinamiento_padrino", referencedColumnName = "padrino")})
    @ManyToOne(optional = false)
    private Apadrinamiento apadrinamiento;
    @JoinColumn(name = "agente", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public AdministracionApadrinamiento() {
    }

    public AdministracionApadrinamiento(AdministracionApadrinamientoPK administracionApadrinamientoPK) {
        this.administracionApadrinamientoPK = administracionApadrinamientoPK;
    }

    public AdministracionApadrinamiento(Date fechaInicio, int agente) {
        this.administracionApadrinamientoPK = new AdministracionApadrinamientoPK(fechaInicio, agente);
    }

    public AdministracionApadrinamientoPK getAdministracionApadrinamientoPK() {
        return administracionApadrinamientoPK;
    }

    public void setAdministracionApadrinamientoPK(AdministracionApadrinamientoPK administracionApadrinamientoPK) {
        this.administracionApadrinamientoPK = administracionApadrinamientoPK;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Apadrinamiento getApadrinamiento() {
        return apadrinamiento;
    }

    public void setApadrinamiento(Apadrinamiento apadrinamiento) {
        this.apadrinamiento = apadrinamiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (administracionApadrinamientoPK != null ? administracionApadrinamientoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdministracionApadrinamiento)) {
            return false;
        }
        AdministracionApadrinamiento other = (AdministracionApadrinamiento) object;
        if ((this.administracionApadrinamientoPK == null && other.administracionApadrinamientoPK != null) || (this.administracionApadrinamientoPK != null && !this.administracionApadrinamientoPK.equals(other.administracionApadrinamientoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.entities.AdministracionApadrinamiento[ administracionApadrinamientoPK=" + administracionApadrinamientoPK + " ]";
    }

}
