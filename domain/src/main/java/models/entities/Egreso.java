

package models.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "egreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Egreso.findAll", query = "SELECT e FROM Egreso e")
    , @NamedQuery(name = "Egreso.findById", query = "SELECT e FROM Egreso e WHERE e.id = :id")
    , @NamedQuery(name = "Egreso.findByFecha", query = "SELECT e FROM Egreso e WHERE e.fecha = :fecha")
    , @NamedQuery(name = "Egreso.findByConcepto", query = "SELECT e FROM Egreso e WHERE e.concepto = :concepto")
    , @NamedQuery(name = "Egreso.findByImporte", query = "SELECT e FROM Egreso e WHERE e.importe = :importe")
    , @NamedQuery(name = "Egreso.findByTipoBeneficiario", query = "SELECT e FROM Egreso e WHERE e.tipoBeneficiario = :tipoBeneficiario")
    , @NamedQuery(name = "Egreso.findByObservaciones", query = "SELECT e FROM Egreso e WHERE e.observaciones = :observaciones")
    , @NamedQuery(name = "Egreso.findByPartida", query = "SELECT e FROM Egreso e WHERE e.partida = :partida")
    , @NamedQuery(name = "Egreso.findByValidado", query = "SELECT e FROM Egreso e WHERE e.validado = :validado")})
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Egreso implements Serializable {
    public enum TipoBeneficiario {
        Alumno,
        Colaborador,
        Tercero,
        Socio
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
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_beneficiario")
    private TipoBeneficiario tipoBeneficiario;
    @Size(max = 100)
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "partida")
    private int partida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "validado")
    private boolean validado;
    @JoinColumn(name = "beneficiario_alumno", referencedColumnName = "codigo")
    @ManyToOne
    private Alumno beneficiarioAlumno;
    @JoinColumn(name = "beneficiario_colaborador", referencedColumnName = "id")
    @ManyToOne
    private Colaborador beneficiarioColaborador;
    @JoinColumn(name = "creador", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario creador;
    @JoinColumn(name = "proyecto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proyecto proyecto;
    @JoinColumn(name = "responsable", referencedColumnName = "id")
    @ManyToOne
    private Usuario responsable;
    @JoinColumn(name = "beneficiario_socio", referencedColumnName = "numero_de_socio")
    @ManyToOne
    private Socio beneficiarioSocio;
    @JoinColumn(name = "beneficiario_tercero", referencedColumnName = "id")
    @ManyToOne
    private Tercero beneficiarioTercero;

}
