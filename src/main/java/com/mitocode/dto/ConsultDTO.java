package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mitocode.model.Patient;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConsultDTO {

    @EqualsAndHashCode.Include
    private Integer idConsult;

    @NotNull
    private PatientDTO patient;

    @NotNull
    private MedicDTO medic;

    @NotNull
    private SpecialtyDTO specialty;

    @NotNull
    private String numConsult;

    @NotNull
    private LocalDateTime consultDate;

    @JsonManagedReference
    @NotNull
    private List<ConsultDetailDTO> details;
}
