

package models.entities;

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
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
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

    public Proyecto() {
    }

    public Proyecto(Integer id) {
        this.id = id;
    }

    public Proyecto(Integer id, String nombre, String descripcion, double repartoCombustible, double repartoMantenimiento, double repartoCtaContenedor, String regionAyuda) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.repartoCombustible = repartoCombustible;
        this.repartoMantenimiento = repartoMantenimiento;
        this.repartoCtaContenedor = repartoCtaContenedor;
        this.regionAyuda = regionAyuda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getRepartoCombustible() {
        return repartoCombustible;
    }

    public void setRepartoCombustible(double repartoCombustible) {
        this.repartoCombustible = repartoCombustible;
    }

    public double getRepartoMantenimiento() {
        return repartoMantenimiento;
    }

    public void setRepartoMantenimiento(double repartoMantenimiento) {
        this.repartoMantenimiento = repartoMantenimiento;
    }

    public double getRepartoCtaContenedor() {
        return repartoCtaContenedor;
    }

    public void setRepartoCtaContenedor(double repartoCtaContenedor) {
        this.repartoCtaContenedor = repartoCtaContenedor;
    }

    public String getRegionAyuda() {
        return regionAyuda;
    }

    public void setRegionAyuda(String regionAyuda) {
        this.regionAyuda = regionAyuda;
    }

    @XmlTransient
    public Collection<RegistroEconomico> getRegistroEconomicoCollection() {
        return registroEconomicoCollection;
    }

    public void setRegistroEconomicoCollection(Collection<RegistroEconomico> registroEconomicoCollection) {
        this.registroEconomicoCollection = registroEconomicoCollection;
    }

    @XmlTransient
    public Collection<Centro> getCentroCollection() {
        return centroCollection;
    }

    public void setCentroCollection(Collection<Centro> centroCollection) {
        this.centroCollection = centroCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.entities.Proyecto[ id=" + id + " ]";
    }

}
