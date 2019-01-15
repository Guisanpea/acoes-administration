

package models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@Entity
@Table(name = "envio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Envio.findAll", query = "SELECT e FROM Envio e")
    , @NamedQuery(name = "Envio.findById", query = "SELECT e FROM Envio e WHERE e.envioPK.id = :id")
    , @NamedQuery(name = "Envio.findByEstado", query = "SELECT e FROM Envio e WHERE e.estado = :estado")
    , @NamedQuery(name = "Envio.findByApadrinamientoId", query = "SELECT e FROM Envio e WHERE e.envioPK.apadrinamientoId = :apadrinamientoId")
    , @NamedQuery(name = "Envio.findByApadrinamientoApadrinado", query = "SELECT e FROM Envio e WHERE e.envioPK.apadrinamientoApadrinado = :apadrinamientoApadrinado")
    , @NamedQuery(name = "Envio.findByApadrinamientoPadrino", query = "SELECT e FROM Envio e WHERE e.envioPK.apadrinamientoPadrino = :apadrinamientoPadrino")})
public class Envio implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EnvioPK envioPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "estado")
    private String estado;
    @JoinColumns({
        @JoinColumn(name = "apadrinamiento_id", referencedColumnName = "id", insertable = false, updatable = false)
        , @JoinColumn(name = "apadrinamiento_apadrinado", referencedColumnName = "apadrinado", insertable = false, updatable = false)
        , @JoinColumn(name = "apadrinamiento_padrino", referencedColumnName = "padrino", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Apadrinamiento apadrinamiento;

    public Envio() {
    }

    public Envio(EnvioPK envioPK) {
        this.envioPK = envioPK;
    }

    public Envio(EnvioPK envioPK, String estado) {
        this.envioPK = envioPK;
        this.estado = estado;
    }

    public Envio(int id, int apadrinamientoId, int apadrinamientoApadrinado, int apadrinamientoPadrino) {
        this.envioPK = new EnvioPK(id, apadrinamientoId, apadrinamientoApadrinado, apadrinamientoPadrino);
    }

    public EnvioPK getEnvioPK() {
        return envioPK;
    }

    public void setEnvioPK(EnvioPK envioPK) {
        this.envioPK = envioPK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Apadrinamiento getApadrinamiento() {
        return apadrinamiento;
    }

    public void setApadrinamiento(Apadrinamiento apadrinamiento) {
        this.apadrinamiento = apadrinamiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (envioPK != null ? envioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Envio)) {
            return false;
        }
        Envio other = (Envio) object;
        if ((this.envioPK == null && other.envioPK != null) || (this.envioPK != null && !this.envioPK.equals(other.envioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.entities.Envio[ envioPK=" + envioPK + " ]";
    }

}
