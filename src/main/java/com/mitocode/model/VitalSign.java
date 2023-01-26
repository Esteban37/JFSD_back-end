package com.mitocode.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class VitalSign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idVitalSign;

    @ManyToOne
    @JoinColumn(name = "id_patient", nullable = false, foreignKey = @ForeignKey(name= "FK_VITALSIGN_PATIENT"))
    private Patient patient; //JPQL Java Persistence Query Language FROM Consult c WHERE c.patient.name = ?

    @Column(nullable = false) //yyyy-mm-ddTHH:mm:ss
    private LocalDateTime date;

    @Column(nullable = false, length = 500)
    private String temperature;

    @Column(nullable = false, length = 500)
    private String heartbeat;

    @Column(nullable = false, length = 500)
    private String respiratoryRate;

}
