package com.pranay.hibernateeventsexample.repository;

import com.pranay.hibernateeventsexample.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
