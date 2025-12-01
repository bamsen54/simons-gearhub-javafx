package com.simonsgearhubjavafx.database;

import java.util.HashMap;
import java.util.Map;

import com.simonsgearhubjavafx.Level;
import com.simonsgearhubjavafx.member.Member;

public class MemberRegistry {

    private final Map<Integer, Member> members = new HashMap<>();

    public MemberRegistry() {
    }

    public void  loadFromDatabase() {
        this.addMember( new Member( 0,  "simon",    Level.STUDENT ) );
        this.addMember( new Member( 1,  "simona",   Level.STUDENT ) );
        this.addMember( new Member( 2,  "korina",   Level.STANDARD ) );
        this.addMember( new Member( 3,  "sara",     Level.STANDARD ) );
        this.addMember( new Member( 4,  "dennis",   Level.STANDARD ) );
        this.addMember( new Member( 5,  "billy",    Level.STANDARD ) );
        this.addMember( new Member( 6,  "kristina", Level.STANDARD ) );
        this.addMember( new Member( 7,  "kristian", Level.STANDARD ) );
        this.addMember( new Member( 8,  "anders",   Level.PREMIUM ) );
        this.addMember( new Member( 9,  "lovisa",   Level.PREMIUM ) );
        this.addMember( new Member( 10, "august",   Level.PREMIUM ) );
    }

    public boolean hasMemberId( int memberId ) {
        return this.members.containsKey(memberId);
    }

    public void addMember(Member member) {
        this.members.put( member.getId(), member );
    }

    public void removeMember(Member member) {
        this.members.remove( member.getId() );
    }

    public Member getMember(int id) {
        return this.members.get( id );
    }

    public Map<Integer, Member> getMembers() {
        return this.members;
    }
}
