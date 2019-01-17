

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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")
    , @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")
    , @NamedQuery(name = "Usuario.findByRol", query = "SELECT u FROM Usuario u WHERE u.rol = :rol")})
@Getter @Setter @ToString @Builder @NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable {
    public enum Rol {
        Agente,
        GerenteSede,
        GerenteRegional,
        CoordinadorLocal,
        CoordinadorGeneral,
        AdministradorLocal,
        AdministradorGeneral
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
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
    @Size(min = 1, max = 15)
    @Column(name = "contrasena")
    private String contrasena;
    @Size(min = 1, max = 20)
    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private Rol rol;
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Collection<AdministracionApadrinamiento> getAdministracionApadrinamientoCollection() {
        return administracionApadrinamientoCollection;
    }

    public void setAdministracionApadrinamientoCollection(Collection<AdministracionApadrinamiento> administracionApadrinamientoCollection) {
        this.administracionApadrinamientoCollection = administracionApadrinamientoCollection;
    }

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
}
