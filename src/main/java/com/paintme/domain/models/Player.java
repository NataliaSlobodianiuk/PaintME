package com.paintme.domain.models;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player implements Serializable{
    private Integer id;
    private User user;
    private Team team;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
