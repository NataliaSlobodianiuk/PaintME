package com.paintme.domain.models;

import com.paintme.domain.models.statuses.UserStatuses;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable
{
    private Integer id;
    private String login;
    private String passwordHash;
    private byte[] passwordSalt;
    private String email;
    private UserStatuses status = UserStatuses.ONLINE;
    private Integer wins = 0;
    private Integer draws = 0;
    private Integer defeats = 0;
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

    @Column(name = "password_hash", nullable = false)
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Column(name = "password_salt", nullable = false)
    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
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
            nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public UserStatuses getStatus() {
        return status;
    }

    public void setStatus(UserStatuses status) {
        this.status = status;
    }

    @Column(
            name = "wins",
            nullable = false)
    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    @Column(
            name = "draws",
            nullable = false)
    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    @Column(name = "defeats",
            nullable = false)
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
