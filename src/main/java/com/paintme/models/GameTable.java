package com.paintme.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "tables")
public class GameTable implements Serializable{
    private Integer id;
    private Short status;
    private Set<Board> boardSet;
    private Set<Team> teamSet;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "table")
    public Set<Board> getBoardSet() {
        return boardSet;
    }

    public void setBoardSet(Set<Board> boardSet) {
        this.boardSet = boardSet;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "table")
    public Set<Team> getTeamSet() {
        return teamSet;
    }

    public void setTeamSet(Set<Team> teamSet) {
        this.teamSet = teamSet;
    }
}
