

package models.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "ingreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingreso.findAll", query = "SELECT i FROM Ingreso i")
    , @NamedQuery(name = "Ingreso.findById", query = "SELECT i FROM Ingreso i WHERE i.id = :id")
    , @NamedQuery(name = "Ingreso.findByFecha", query = "SELECT i FROM Ingreso i WHERE i.fecha = :fecha")
    , @NamedQuery(name = "Ingreso.findByConcepto", query = "SELECT i FROM Ingreso i WHERE i.concepto = :concepto")
    , @NamedQuery(name = "Ingreso.findByImporte", query = "SELECT i FROM Ingreso i WHERE i.importe = :importe")
    , @NamedQuery(name = "Ingreso.findByEmisor", query = "SELECT i FROM Ingreso i WHERE i.emisor = :emisor")
    , @NamedQuery(name = "Ingreso.findByObservaciones", query = "SELECT i FROM Ingreso i WHERE i.observaciones = :observaciones")
    , @NamedQuery(name = "Ingreso.findByValidado", query = "SELECT i FROM Ingreso i WHERE i.validado = :validado")})
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ingreso implements Serializable {

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
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "concepto")
    private String concepto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe")
    private double importe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "emisor")
    private String emisor;
    @Size(max = 100)
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "validado")
    private boolean validado;
    @JoinColumn(name = "creador", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario creador;
    @JoinColumn(name = "partida", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Partida partida;
    @JoinColumn(name = "proyecto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoProyecto proyecto;
    @JoinColumn(name = "responsable", referencedColumnName = "id")
    @ManyToOne
    private Usuario responsable;

}
