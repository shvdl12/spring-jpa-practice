package com.practive.jpa;

import com.practive.jpa.entity.Member;
import com.practive.jpa.entity.Team;
import com.practive.jpa.repository.MemberRepository;
import com.practive.jpa.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
//@Rollback(value = false)
public class RepositoryTest {

    Logger logger = LoggerFactory.getLogger(RepositoryTest.class);
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void fetch_member() {
        Team team = teamRepository.save(Team.builder()
                .teamName("맨유")
                .build());

        Member member = memberRepository.save(Member.builder()
                .memberName("박지성")
                .team(team)
                .build());

        Member result = memberRepository.findByMemberName(member.getMemberName());

        assertThat(result.getMemberName()).isEqualTo(member.getMemberName());
        assertThat(result.getTeam().getTeamName()).isEqualTo(team.getTeamName());
    }

    @Test
    public void fetch_team() {
        Team team = teamRepository.save(Team.builder()
                .teamName("맨유")
                .build());

        memberRepository.save(Member.builder()
                .memberName("박지성")
                .team(team)
                .build());

        memberRepository.save(Member.builder()
                .memberName("에브라")
                .team(team)
                .build());

        memberRepository.save(Member.builder()
                .memberName("루니")
                .team(team)
                .build());

        memberRepository.flush();

        Team result = teamRepository.findByTeamName(team.getTeamName());

        assertThat(result.getTeamName()).isEqualTo(team.getTeamName());
        assertThat(result.getMembers()).hasSize(3);

    }
}
