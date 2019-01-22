

package models.entities;

import lombok.*;

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
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AdministracionApadrinamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
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

}
