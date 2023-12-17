package com.example.aftasspringboot.seeder.dbSeeder;

import com.example.aftasspringboot.entities.Member;
import com.example.aftasspringboot.entities.enums.IdentityDocumentType;
import com.example.aftasspringboot.repositories.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class MemberSeeder {
    private final MemberRepository memberRepository;

    public MemberSeeder(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private final Member[] members={
            Member.builder()
                    .firstName("Mohamed")
                    .lastName("Elidrissi")
                    .accessionDate(java.time.LocalDate.of(2021, 1, 1))
                    .identityNumber("KK123458")
                    .identityDocumentType(IdentityDocumentType.IDENTITY_CARD)
                    .nationality("Moroccan")
                    .build(),
            Member.builder()
                    .firstName("Abdelghafour")
                    .lastName("Auad")
                    .accessionDate(java.time.LocalDate.of(2023, 11 ,28))
                    .identityNumber("HA218926")
                    .identityDocumentType(IdentityDocumentType.IDENTITY_CARD)
                    .nationality("Moroccan")
                    .build(),
            Member.builder()
                    .firstName("Asmae")
                    .lastName("ElKhiraoui")
                    .accessionDate(java.time.LocalDate.of(2023, 11, 19))
                    .identityNumber("HAT1691632820")
                    .identityDocumentType(IdentityDocumentType.PASSPORT)
                    .nationality("Algerienne")
                    .build()
    };

    private void log(){
        System.out.println("----------------------Member Seeder----------------------");
    }

    public void seed() {
        this.log();
        if(memberRepository.findAll().isEmpty())
            memberRepository.saveAll(java.util.Arrays.asList(members));
    }

}
