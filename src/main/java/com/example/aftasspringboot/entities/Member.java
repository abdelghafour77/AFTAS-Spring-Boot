package com.example.aftasspringboot.entities;

import com.example.aftasspringboot.entities.enums.IdentityDocumentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "First name cannot be null")
    @NotBlank(message = "first name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must only contain letters")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @NotBlank(message = "Last name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must only contain letters")
    private String lastName;

    @PastOrPresent(message = "Date of accession must be in the past or present")
    @NotNull(message = "Date of accession cannot be null")
    private LocalDate accessionDate;

    @NotNull(message = "Nationality cannot be null")
    @NotBlank(message = "Nationality cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Nationality must only contain letters")
    private String nationality;

    @NotNull(message = "Identity document type cannot be null")
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;

    @NotNull(message = "Identity number cannot be null")
    @NotBlank(message = "Identity number cannot be blank")
    private String identityNumber;

    @OneToMany(mappedBy = "member")
    @Column(nullable = true)
    @JsonIgnoreProperties("member")
    private List<Ranking> rankings;

}
