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
                    .nationality("Maroc")
                    .build(),
            Member.builder()
                    .firstName("Abdelghafour")
                    .lastName("Aouad")
                    .accessionDate(java.time.LocalDate.of(2023, 11 ,28))
                    .identityNumber("HA218926")
                    .identityDocumentType(IdentityDocumentType.IDENTITY_CARD)
                    .nationality("Maroc")
                    .build(),
            Member.builder()
                    .firstName("Asmae")
                    .lastName("ElKhiraoui")
                    .accessionDate(java.time.LocalDate.of(2023, 11, 19))
                    .identityNumber("HAT1691632820")
                    .identityDocumentType(IdentityDocumentType.PASSPORT)
                    .nationality("Algerie")
                    .build(),
            Member.builder()
                    .firstName("Fahd")
                    .lastName("Mahfoudi")
                    .accessionDate(java.time.LocalDate.of(2023, 12, 10))
                    .identityNumber("HA222626")
                    .identityDocumentType(IdentityDocumentType.IDENTITY_CARD)
                    .nationality("Maroc")
                    .build(),
            Member.builder()
                    .firstName("Mouad")
                    .lastName("ElAmraoui")
                    .accessionDate(java.time.LocalDate.of(2023, 12, 10))
                    .identityNumber("KK232262")
                    .identityDocumentType(IdentityDocumentType.IDENTITY_CARD)
                    .nationality("Maroc")
                    .build(),
            Member.builder()
                    .firstName("Mohamed")
                    .lastName("Chkir")
                    .accessionDate(java.time.LocalDate.of(2023, 12, 13))
                    .identityNumber("MA129732")
                    .identityDocumentType(IdentityDocumentType.IDENTITY_CARD)
                    .nationality("Maroc")
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
