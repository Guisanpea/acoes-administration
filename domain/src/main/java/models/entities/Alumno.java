

package models.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


@Entity
@Table(name = "alumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a")
    , @NamedQuery(name = "Alumno.findByCodigo", query = "SELECT a FROM Alumno a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "Alumno.findByNombre", query = "SELECT a FROM Alumno a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Alumno.findByApellidos", query = "SELECT a FROM Alumno a WHERE a.apellidos = :apellidos")
    , @NamedQuery(name = "Alumno.findByEstado", query = "SELECT a FROM Alumno a WHERE a.estado = :estado")
    , @NamedQuery(name = "Alumno.findBySexo", query = "SELECT a FROM Alumno a WHERE a.sexo = :sexo")
    , @NamedQuery(name = "Alumno.findByFoto", query = "SELECT a FROM Alumno a WHERE a.foto = :foto")
    , @NamedQuery(name = "Alumno.findByFechaNacimiento", query = "SELECT a FROM Alumno a WHERE a.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Alumno.findByFechaEntradaAcoes", query = "SELECT a FROM Alumno a WHERE a.fechaEntradaAcoes = :fechaEntradaAcoes")
    , @NamedQuery(name = "Alumno.findByFechaAlta", query = "SELECT a FROM Alumno a WHERE a.fechaAlta = :fechaAlta")
    , @NamedQuery(name = "Alumno.findByFechaSalidaAcoes", query = "SELECT a FROM Alumno a WHERE a.fechaSalidaAcoes = :fechaSalidaAcoes")
    , @NamedQuery(name = "Alumno.findByGradoCurso", query = "SELECT a FROM Alumno a WHERE a.gradoCurso = :gradoCurso")
    , @NamedQuery(name = "Alumno.findByColoniaProcedencia", query = "SELECT a FROM Alumno a WHERE a.coloniaProcedencia = :coloniaProcedencia")
    , @NamedQuery(name = "Alumno.findByColoniaActual", query = "SELECT a FROM Alumno a WHERE a.coloniaActual = :coloniaActual")
    , @NamedQuery(name = "Alumno.findByObservaciones", query = "SELECT a FROM Alumno a WHERE a.observaciones = :observaciones")
    , @NamedQuery(name = "Alumno.findByApadrinable", query = "SELECT a FROM Alumno a WHERE a.apadrinable = :apadrinable")})
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
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
    @Column(name = "sexo")
    private String sexo;
    @Size(max = 100)
    @Column(name = "foto")
    private String foto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_entrada_acoes")
    @Temporal(TemporalType.DATE)
    private Date fechaEntradaAcoes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Column(name = "fecha_salida_acoes")
    @Temporal(TemporalType.DATE)
    private Date fechaSalidaAcoes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "grado_curso")
    private int gradoCurso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "colonia_procedencia")
    private String coloniaProcedencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "colonia_actual")
    private String coloniaActual;
    @Size(max = 100)
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "apadrinable")
    private boolean apadrinable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private Collection<Apadrinamiento> apadrinamientoCollection;
    @OneToMany(mappedBy = "beneficiarioAlumno")
    private Collection<Egreso> egresoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno1")
    private Collection<EstaEn> estaEnCollection;

}
