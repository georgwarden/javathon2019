package ru.mai.pubstash.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "OPERATION")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "COST", nullable = false)
    private double cost;


    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="MEMBER_ID")
    private Member member;


    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="PARTY_ID")
    private Party party;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return id == operation.id &&
                Double.compare(operation.cost, cost) == 0 &&
                Objects.equals(member, operation.member) &&
                Objects.equals(party, operation.party);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, member, party);
    }
}
