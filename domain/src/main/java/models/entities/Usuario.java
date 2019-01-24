

package models.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
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
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable {
    public enum Rol {
        Agente,
        GerenteSede,
        GerenteRegional,
        CoordinadorLocal,
        CoordinadorGeneral,
        AdministradorLocal,
        AdministradorGeneral,
        AdministradorUsuarios
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
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Size(min = 1, max = 15)
    @Column(name = "contrasena")
    private String contrasena;
    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Rol rol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "responsable")
    private Collection<Socio> socioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creador")
    private Collection<Egreso> esgresosCreados;
    @OneToMany(mappedBy = "responsable")
    private Collection<Egreso> egresosResponsables;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<AdministracionApadrinamiento> administracionApadrinamientoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creador")
    private Collection<Ingreso> ingresosCreados;
    @OneToMany(mappedBy = "responsable")
    private Collection<Ingreso> ingresosResponsables;
    @JoinColumn(name = "centro", referencedColumnName = "id")
    @ManyToOne
    private Proyecto centro;
    @JoinColumn(name = "sede_usuario_local", referencedColumnName = "id")
    @ManyToOne
    private Sede sedeUsuarioLocal;

}
