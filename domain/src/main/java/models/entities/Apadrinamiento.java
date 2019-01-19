

package models.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


@Entity
@Table(name = "apadrinamiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apadrinamiento.findAll", query = "SELECT a FROM Apadrinamiento a")
    , @NamedQuery(name = "Apadrinamiento.findById", query = "SELECT a FROM Apadrinamiento a WHERE a.apadrinamientoPK.id = :id")
    , @NamedQuery(name = "Apadrinamiento.findByApadrinado", query = "SELECT a FROM Apadrinamiento a WHERE a.apadrinamientoPK.apadrinado = :apadrinado")
    , @NamedQuery(name = "Apadrinamiento.findByPadrino", query = "SELECT a FROM Apadrinamiento a WHERE a.apadrinamientoPK.padrino = :padrino")
    , @NamedQuery(name = "Apadrinamiento.findByFechaInicio", query = "SELECT a FROM Apadrinamiento a WHERE a.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Apadrinamiento.findByFechaFin", query = "SELECT a FROM Apadrinamiento a WHERE a.fechaFin = :fechaFin")})
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Apadrinamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    protected ApadrinamientoPK apadrinamientoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @JoinColumn(name = "apadrinado", referencedColumnName = "codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alumno alumno;
    @JoinColumn(name = "padrino", referencedColumnName = "numero_de_socio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Socio socio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apadrinamiento")
    private Collection<Envio> envioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apadrinamiento")
    private Collection<AdministracionApadrinamiento> administracionApadrinamientoCollection;

}
