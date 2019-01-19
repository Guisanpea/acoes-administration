

package models.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "esta_en")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstaEn.findAll", query = "SELECT e FROM EstaEn e")
    , @NamedQuery(name = "EstaEn.findByAlumno", query = "SELECT e FROM EstaEn e WHERE e.estaEnPK.alumno = :alumno")
    , @NamedQuery(name = "EstaEn.findByCentro", query = "SELECT e FROM EstaEn e WHERE e.estaEnPK.centro = :centro")
    , @NamedQuery(name = "EstaEn.findByFechaInicio", query = "SELECT e FROM EstaEn e WHERE e.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "EstaEn.findByFechaFin", query = "SELECT e FROM EstaEn e WHERE e.fechaFin = :fechaFin")})
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EstaEn implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
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
    private Proyecto proyecto;

}
