

package models.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")
    , @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")
    , @NamedQuery(name = "Usuario.findByRol", query = "SELECT u FROM Usuario u WHERE u.rol = :rol")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "contrasena")
    private String contrasena;
    @Basic(optional = false)
    @Size(min = 1, max = 20)
    @Column(name = "rol")
    private String rol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<AdministracionApadrinamiento> administracionApadrinamientoCollection;
    @OneToMany(mappedBy = "responsable")
    private Collection<RegistroEconomico> registroEconomicoCollection;
    @JoinColumn(name = "centro_", referencedColumnName = "id")
    @ManyToOne
    private Centro centro;
    @JoinColumn(name = "sede_usuario_local", referencedColumnName = "id")
    @ManyToOne
    private Sede sedeUsuarioLocal;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String nombre, String email, String contrasena, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @XmlTransient
    public Collection<AdministracionApadrinamiento> getAdministracionApadrinamientoCollection() {
        return administracionApadrinamientoCollection;
    }

    public void setAdministracionApadrinamientoCollection(Collection<AdministracionApadrinamiento> administracionApadrinamientoCollection) {
        this.administracionApadrinamientoCollection = administracionApadrinamientoCollection;
    }

    @XmlTransient
    public Collection<RegistroEconomico> getRegistroEconomicoCollection() {
        return registroEconomicoCollection;
    }

    public void setRegistroEconomicoCollection(Collection<RegistroEconomico> registroEconomicoCollection) {
        this.registroEconomicoCollection = registroEconomicoCollection;
    }

    public Centro getCentro() {
        return centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    public Sede getSedeUsuarioLocal() {
        return sedeUsuarioLocal;
    }

    public void setSedeUsuarioLocal(Sede sedeUsuarioLocal) {
        this.sedeUsuarioLocal = sedeUsuarioLocal;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.entities.Usuario[ id=" + id + " ]";
    }

}
