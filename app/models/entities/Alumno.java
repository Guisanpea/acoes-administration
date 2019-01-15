

package models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
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
    , @NamedQuery(name = "Alumno.findByObservaciones", query = "SELECT a FROM Alumno a WHERE a.observaciones = :observaciones")})
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private Collection<Apadrinamiento> apadrinamientoCollection;
    @OneToMany(mappedBy = "codigoBeneficiario")
    private Collection<RegistroEconomico> registroEconomicoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno1")
    private Collection<EstaEn> estaEnCollection;

    public Alumno() {
    }

    public Alumno(Integer codigo) {
        this.codigo = codigo;
    }

    public Alumno(Integer codigo, String nombre, String apellidos, String sexo, Date fechaNacimiento, Date fechaEntradaAcoes, Date fechaAlta, int gradoCurso, String coloniaProcedencia, String coloniaActual) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaEntradaAcoes = fechaEntradaAcoes;
        this.fechaAlta = fechaAlta;
        this.gradoCurso = gradoCurso;
        this.coloniaProcedencia = coloniaProcedencia;
        this.coloniaActual = coloniaActual;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaEntradaAcoes() {
        return fechaEntradaAcoes;
    }

    public void setFechaEntradaAcoes(Date fechaEntradaAcoes) {
        this.fechaEntradaAcoes = fechaEntradaAcoes;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaSalidaAcoes() {
        return fechaSalidaAcoes;
    }

    public void setFechaSalidaAcoes(Date fechaSalidaAcoes) {
        this.fechaSalidaAcoes = fechaSalidaAcoes;
    }

    public int getGradoCurso() {
        return gradoCurso;
    }

    public void setGradoCurso(int gradoCurso) {
        this.gradoCurso = gradoCurso;
    }

    public String getColoniaProcedencia() {
        return coloniaProcedencia;
    }

    public void setColoniaProcedencia(String coloniaProcedencia) {
        this.coloniaProcedencia = coloniaProcedencia;
    }

    public String getColoniaActual() {
        return coloniaActual;
    }

    public void setColoniaActual(String coloniaActual) {
        this.coloniaActual = coloniaActual;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public Collection<Apadrinamiento> getApadrinamientoCollection() {
        return apadrinamientoCollection;
    }

    public void setApadrinamientoCollection(Collection<Apadrinamiento> apadrinamientoCollection) {
        this.apadrinamientoCollection = apadrinamientoCollection;
    }

    @XmlTransient
    public Collection<RegistroEconomico> getRegistroEconomicoCollection() {
        return registroEconomicoCollection;
    }

    public void setRegistroEconomicoCollection(Collection<RegistroEconomico> registroEconomicoCollection) {
        this.registroEconomicoCollection = registroEconomicoCollection;
    }

    @XmlTransient
    public Collection<EstaEn> getEstaEnCollection() {
        return estaEnCollection;
    }

    public void setEstaEnCollection(Collection<EstaEn> estaEnCollection) {
        this.estaEnCollection = estaEnCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.entities.Alumno[ codigo=" + codigo + " ]";
    }

}
