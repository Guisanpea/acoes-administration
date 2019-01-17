

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
@Table(name = "proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p")
    , @NamedQuery(name = "Proyecto.findById", query = "SELECT p FROM Proyecto p WHERE p.id = :id")
    , @NamedQuery(name = "Proyecto.findByNombre", query = "SELECT p FROM Proyecto p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Proyecto.findByDescripcion", query = "SELECT p FROM Proyecto p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Proyecto.findByRepartoCombustible", query = "SELECT p FROM Proyecto p WHERE p.repartoCombustible = :repartoCombustible")
    , @NamedQuery(name = "Proyecto.findByRepartoMantenimiento", query = "SELECT p FROM Proyecto p WHERE p.repartoMantenimiento = :repartoMantenimiento")
    , @NamedQuery(name = "Proyecto.findByRepartoCtaContenedor", query = "SELECT p FROM Proyecto p WHERE p.repartoCtaContenedor = :repartoCtaContenedor")
    , @NamedQuery(name = "Proyecto.findByRegionAyuda", query = "SELECT p FROM Proyecto p WHERE p.regionAyuda = :regionAyuda")})
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Proyecto implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "reparto_combustible")
    private double repartoCombustible;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reparto_mantenimiento")
    private double repartoMantenimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reparto_cta_contenedor")
    private double repartoCtaContenedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "region_ayuda")
    private String regionAyuda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private Collection<RegistroEconomico> registroEconomicoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private Collection<Centro> centroCollection;
}
