package com.example.aftasspringboot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class RankId implements Serializable {
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "competition_id")
    private Long competitionId;
}
