package com.paintme.models;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable
{
    private Integer id;
    private String login;
    private String password;
    private String email;
    private Short status;
    private Integer wins;
    private Integer draws;
    private Integer defeats;
    private Set<Player> playerSet;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "login", nullable = false, unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(
            name = "status",
            nullable = false,
            columnDefinition = "smallint default 0")
    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Column(
            name = "wins",
            nullable = false,
            columnDefinition = "int default 0")
    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    @Column(
            name = "draws",
            nullable = false,
            columnDefinition = "int default 0")
    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    @Column(name = "defeats",
            nullable = false,
            columnDefinition = "int default 0")
    public Integer getDefeats() {
        return defeats;
    }

    public void setDefeats(Integer defeats) {
        this.defeats = defeats;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Player> getPlayerSet() {
        return playerSet;
    }

    public void setPlayerSet(Set<Player> playerSet) {
        this.playerSet = playerSet;
    }
}
