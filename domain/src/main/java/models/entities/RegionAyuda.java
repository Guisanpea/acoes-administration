

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
@Table(name = "region_ayuda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegionAyuda.findAll", query = "SELECT r FROM RegionAyuda r")
    , @NamedQuery(name = "RegionAyuda.findById", query = "SELECT r FROM RegionAyuda r WHERE r.id = :id")
    , @NamedQuery(name = "RegionAyuda.findByNombre", query = "SELECT r FROM RegionAyuda r WHERE r.nombre = :nombre")})
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RegionAyuda implements Serializable {

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
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regionAyuda")
    private Collection<Proyecto> proyectoCollection;

}
