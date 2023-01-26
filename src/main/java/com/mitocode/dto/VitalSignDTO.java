package com.mitocode.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VitalSignDTO {

    @EqualsAndHashCode.Include
    private Integer idVitalSign;

    @NotNull
    private PatientDTO patient;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private String temperature;

    @NotNull
    private String heartbeat;

    @NotNull
    private String respiratoryRate;

}
