package com.simonsgearhubjavafx.service;

import com.simonsgearhubjavafx.database.Inventory;
import com.simonsgearhubjavafx.database.MemberRegistry;
import com.simonsgearhubjavafx.member.Member;
import com.simonsgearhubjavafx.service.IncomeService;

public class MembershipService {

    MemberRegistry memberRegistry = new MemberRegistry();

    public MembershipService(Inventory inventory) {
        memberRegistry.loadFromDatabase();
    }

    public MemberRegistry getMemberRegistry() {
        return this.memberRegistry;
    }

    public void setMemberRegistry(MemberRegistry memberRegistry) {
        this.memberRegistry = memberRegistry;
    }

    public boolean hasMemberWithID( int id ) {
        return this.memberRegistry.hasMemberId( id );
    }

    public Member getMemberWithID( int id ) {
        return this.memberRegistry.getMember( id );
    }

    public void addNewMember(Member member, IncomeService incomeService) {

        this.memberRegistry.addMember( member );
        incomeService.handleEntryFeePaymen( member, null );
    }
}
