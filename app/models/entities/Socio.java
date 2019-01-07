

package models.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


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
    , @NamedQuery(name = "Socio.findByObservaciones", query = "SELECT s FROM Socio s WHERE s.observaciones = :observaciones")})
public class Socio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numero_de_socio")
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "socio")
    private Collection<Apadrinamiento> apadrinamientoCollection;
    @OneToMany(mappedBy = "numeroSocio")
    private Collection<RegistroEconomico> registroEconomicoCollection;

    public Socio() {
    }

    public Socio(Integer numeroDeSocio) {
        this.numeroDeSocio = numeroDeSocio;
    }

    public Socio(Integer numeroDeSocio, String nombre, String apellidos, String nif, String direccion, String poblacion, String codigoPostal, String provincia, int telefonoMovil, String email, boolean certificado, String sector, Date fechaAlta) {
        this.numeroDeSocio = numeroDeSocio;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.telefonoMovil = telefonoMovil;
        this.email = email;
        this.certificado = certificado;
        this.sector = sector;
        this.fechaAlta = fechaAlta;
    }

    public Integer getNumeroDeSocio() {
        return numeroDeSocio;
    }

    public void setNumeroDeSocio(Integer numeroDeSocio) {
        this.numeroDeSocio = numeroDeSocio;
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

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Integer getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(Integer telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public int getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(int telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public boolean getCertificado() {
        return certificado;
    }

    public void setCertificado(boolean certificado) {
        this.certificado = certificado;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroDeSocio != null ? numeroDeSocio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Socio)) {
            return false;
        }
        Socio other = (Socio) object;
        if ((this.numeroDeSocio == null && other.numeroDeSocio != null) || (this.numeroDeSocio != null && !this.numeroDeSocio.equals(other.numeroDeSocio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.entities.Socio[ numeroDeSocio=" + numeroDeSocio + " ]";
    }

}
