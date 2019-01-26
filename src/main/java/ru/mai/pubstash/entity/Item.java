package ru.mai.pubstash.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Cущность "Товар"
 */
@Entity
@Table(name = "ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "COST", nullable = false)
    private double cost;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="PARTY_ID")
    private Party party;

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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                Double.compare(item.cost, cost) == 0 &&
                Objects.equals(name, item.name) &&
                Objects.equals(party, item.party);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cost, party);
    }
}
