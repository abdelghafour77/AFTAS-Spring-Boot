package com.example.aftasspringboot.dtos.requests;

import com.example.aftasspringboot.entities.enums.IdentityDocumentType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {

    @NotNull(message = "First name cannot be null")
    @NotBlank(message = "First name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must only contain letters")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @NotBlank(message = "Last name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must only contain letters")
    private String lastName;

    @NotNull(message = "Nationality cannot be null")
    @NotBlank(message = "Nationality cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Nationality must only contain letters")
    private String nationality;

    private LocalDate accessionDate;

    @NotNull(message = "Identity document type cannot be null")
    private IdentityDocumentType identityDocumentType;

    @Column(unique = true)
    @NotNull(message = "Identity number cannot be null")
    @NotBlank(message = "Identity number cannot be blank")
    private String identityNumber;
}
