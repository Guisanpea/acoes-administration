

package models.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "registro_economico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroEconomico.findAll", query = "SELECT r FROM RegistroEconomico r")
    , @NamedQuery(name = "RegistroEconomico.findById", query = "SELECT r FROM RegistroEconomico r WHERE r.id = :id")
    , @NamedQuery(name = "RegistroEconomico.findByFecha", query = "SELECT r FROM RegistroEconomico r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "RegistroEconomico.findByTipo", query = "SELECT r FROM RegistroEconomico r WHERE r.tipo = :tipo")
    , @NamedQuery(name = "RegistroEconomico.findByConcepto", query = "SELECT r FROM RegistroEconomico r WHERE r.concepto = :concepto")
    , @NamedQuery(name = "RegistroEconomico.findByImporte", query = "SELECT r FROM RegistroEconomico r WHERE r.importe = :importe")
    , @NamedQuery(name = "RegistroEconomico.findByObservaciones", query = "SELECT r FROM RegistroEconomico r WHERE r.observaciones = :observaciones")
    , @NamedQuery(name = "RegistroEconomico.findByCodigoServicio", query = "SELECT r FROM RegistroEconomico r WHERE r.codigoServicio = :codigoServicio")})
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RegistroEconomico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Size(min = 1, max = 7)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "concepto")
    private String concepto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe")
    private double importe;
    @Size(max = 100)
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "codigo_servicio")
    private Integer codigoServicio;
    @JoinColumn(name = "numero_socio", referencedColumnName = "numero_de_socio")
    @ManyToOne
    private Socio numeroSocio;
    @JoinColumn(name = "codigo_beneficiario", referencedColumnName = "codigo")
    @ManyToOne
    private Alumno codigoBeneficiario;
    @JoinColumn(name = "responsable", referencedColumnName = "id")
    @ManyToOne
    private Usuario responsable;
    @JoinColumn(name = "proyecto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proyecto proyecto;
}
