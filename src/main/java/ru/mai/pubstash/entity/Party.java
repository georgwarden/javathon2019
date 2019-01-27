package ru.mai.pubstash.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Сущность "Группа"
 */
@Entity
@Table(name = "PARTY")
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME", length=100,  nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", length=1000)
    private String description;

    @Column(name = "TOTAL" , nullable = false)
    private double total;

    @OneToMany(mappedBy="party")
    private List<Member> members;

    @OneToMany(mappedBy="party")
    @OrderBy("id ASC")
    private List<Item> items;

    @OneToMany(mappedBy="party")
    private List<Operation> operations;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="PAYER_ID")
    private Member payMember;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public Member getPayMember() {
        return payMember;
    }

    public void setPayMember(Member payMember) {
        this.payMember = payMember;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Party party = (Party) o;
        return id == party.id &&
                Double.compare(party.total, total) == 0 &&
                Objects.equals(name, party.name) &&
                Objects.equals(description, party.description) &&
                Objects.equals(members, party.members) &&
                Objects.equals(items, party.items) &&
                Objects.equals(operations, party.operations) &&
                Objects.equals(payMember, party.payMember);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, total, members, items, operations, payMember);
    }
}
