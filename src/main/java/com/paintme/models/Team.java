package com.paintme.models;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "teams")
public class Team implements Serializable{
    private Integer id;
    private String color;
    private GameTable table;
    private Set<Player> playerSet;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "color", nullable = false)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public GameTable getTable() {
        return table;
    }

    public void setTable(GameTable table) {
        this.table = table;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "team")
    public Set<Player> getPlayerSet() {
        return playerSet;
    }

    public void setPlayerSet(Set<Player> playerSet) {
        this.playerSet = playerSet;
    }
}
