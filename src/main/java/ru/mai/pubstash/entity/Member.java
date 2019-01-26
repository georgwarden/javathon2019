package ru.mai.pubstash.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность "Участник группы"
 */
@Entity
@Table(name = "MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "COST", nullable = false)
    private double cost;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="USER_ID")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        Member member = (Member) o;
        return id == member.id &&
                Double.compare(member.cost, cost) == 0 &&
                Objects.equals(user, member.user) &&
                Objects.equals(party, member.party);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cost, user, party);
    }
}
