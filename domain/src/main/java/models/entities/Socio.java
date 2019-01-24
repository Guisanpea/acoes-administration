

package models.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


@Entity
@Table(name = "socio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Socio.findAll", query = "SELECT s FROM Socio s")
    , @NamedQuery(name = "Socio.findByNumeroDeSocio", query = "SELECT s FROM Socio s WHERE s.numeroDeSocio = :numeroDeSocio")
    , @NamedQuery(name = "Socio.findByNombre", query = "SELECT s FROM Socio s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "Socio.findByApellidos", query = "SELECT s FROM Socio s WHERE s.apellidos = :apellidos")
    , @NamedQuery(name = "Socio.findByEstado", query = "SELECT s FROM Socio s WHERE s.estado = :estado")
    , @NamedQuery(name = "Socio.findByNif", query = "SELECT s FROM Socio s WHERE s.nif = :nif")
    , @NamedQuery(name = "Socio.findByDireccion", query = "SELECT s FROM Socio s WHERE s.direccion = :direccion")
    , @NamedQuery(name = "Socio.findByPoblacion", query = "SELECT s FROM Socio s WHERE s.poblacion = :poblacion")
    , @NamedQuery(name = "Socio.findByCodigoPostal", query = "SELECT s FROM Socio s WHERE s.codigoPostal = :codigoPostal")
    , @NamedQuery(name = "Socio.findByProvincia", query = "SELECT s FROM Socio s WHERE s.provincia = :provincia")
    , @NamedQuery(name = "Socio.findByTelefonoFijo", query = "SELECT s FROM Socio s WHERE s.telefonoFijo = :telefonoFijo")
    , @NamedQuery(name = "Socio.findByTelefonoMovil", query = "SELECT s FROM Socio s WHERE s.telefonoMovil = :telefonoMovil")
    , @NamedQuery(name = "Socio.findByEmail", query = "SELECT s FROM Socio s WHERE s.email = :email")
    , @NamedQuery(name = "Socio.findByRelacion", query = "SELECT s FROM Socio s WHERE s.relacion = :relacion")
    , @NamedQuery(name = "Socio.findByCertificado", query = "SELECT s FROM Socio s WHERE s.certificado = :certificado")
    , @NamedQuery(name = "Socio.findBySector", query = "SELECT s FROM Socio s WHERE s.sector = :sector")
    , @NamedQuery(name = "Socio.findByFechaAlta", query = "SELECT s FROM Socio s WHERE s.fechaAlta = :fechaAlta")
    , @NamedQuery(name = "Socio.findByFechaBaja", query = "SELECT s FROM Socio s WHERE s.fechaBaja = :fechaBaja")
    , @NamedQuery(name = "Socio.findByObservaciones", query = "SELECT s FROM Socio s WHERE s.observaciones = :observaciones")
    , @NamedQuery(name = "Socio.findByContribucionEconomica", query = "SELECT s FROM Socio s WHERE s.contribucionEconomica = :contribucionEconomica")})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Socio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numero_de_socio")
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private Integer numeroDeSocio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 15)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "nif")
    private String nif;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "poblacion")
    private String poblacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo_postal")
    private String codigoPostal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "provincia")
    private String provincia;
    @Column(name = "telefono_fijo")
    private Integer telefonoFijo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "telefono_movil")
    private int telefonoMovil;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "relacion")
    private String relacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "certificado")
    private boolean certificado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "sector")
    private String sector;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Column(name = "fecha_baja")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @Size(max = 100)
    @Column(name = "observaciones")
    private String observaciones;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "contribucion_economica")
    private Double contribucionEconomica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "socio")
    private Collection<Apadrinamiento> apadrinamientoCollection;
    @JoinColumn(name = "responsable", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario responsable;
    @OneToMany(mappedBy = "beneficiarioSocio")
    private Collection<Egreso> egresoCollection;

}
