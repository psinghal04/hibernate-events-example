package com.pranay.hibernateeventsexample.controller;

import com.pranay.hibernateeventsexample.exception.ResourceNotFoundException;
import com.pranay.hibernateeventsexample.model.Address;
import com.pranay.hibernateeventsexample.model.Member;
import com.pranay.hibernateeventsexample.repository.AddressRepository;
import com.pranay.hibernateeventsexample.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MemberController {
    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository, AddressRepository addressRepository) {
        this.memberRepository = memberRepository;
        this.addressRepository = addressRepository;
    }

    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }


    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable(value = "id") Long memberId)
            throws ResourceNotFoundException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + memberId));
        return ResponseEntity.ok().body(member);
    }

    @PostMapping("/members")
    public Member createMember(@Valid @RequestBody Member member) {
        Member mem = memberRepository.save(member);
        for (Address a : mem.getAddresses()) {
            a.setMember(mem);
            addressRepository.save(a);
        }

        return mem;
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable(value = "id") Long memberId,
                                               @Valid @RequestBody Member memberDetails) throws ResourceNotFoundException {
        System.out.println("Thread ID of controller: " + Thread.currentThread().getId());
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found for this id :: " + memberId));

        for (Address a : memberDetails.getAddresses()) {
            a.setMember(member);
            addressRepository.save(a);
        }

        member.setEmailId(memberDetails.getEmailId());
        member.setLastName(memberDetails.getLastName());
        member.setFirstName(memberDetails.getFirstName());
        final Member updatedMember = memberRepository.save(member);

        return ResponseEntity.ok(updatedMember);
    }
}
