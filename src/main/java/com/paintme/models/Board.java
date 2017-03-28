package com.paintme.models;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "boards")
public class Board implements Serializable{
    private Integer id;
    private GameTable table;
    private Short status;
    private String cells;
    private String winnerColor;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public GameTable getTable() {
        return table;
    }

    public void setTable(GameTable table) {
        this.table = table;
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

    @Column(name = "cells", nullable = false)
    public String getCells() {
        return cells;
    }

    public void setCells(String cells) {
        this.cells = cells;
    }

    @Column(name = "winner_color", nullable = false)
    public String getWinnerColor() {
        return winnerColor;
    }

    public void setWinnerColor(String winnerColor) {
        this.winnerColor = winnerColor;
    }
}
