package com.practive.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TB_MEMBER")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;
    private String memberName;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}
