

package models.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;


@Entity
@Table(name = "tipo_proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoProyecto.findAll", query = "SELECT t FROM TipoProyecto t")
    , @NamedQuery(name = "TipoProyecto.findById", query = "SELECT t FROM TipoProyecto t WHERE t.id = :id")
    , @NamedQuery(name = "TipoProyecto.findByNombre", query = "SELECT t FROM TipoProyecto t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoProyecto.findByDescripcion", query = "SELECT t FROM TipoProyecto t WHERE t.descripcion = :descripcion")})
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "region_ayuda", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RegionAyuda regionAyuda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoProyecto")
    private Collection<Proyecto> proyectoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private Collection<Egreso> egresoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private Collection<Ingreso> ingresoCollection;

}
