package com.paintme.domain.models;

import com.paintme.domain.models.statuses.GameStatuses;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "boards")
public class Board implements Serializable{
    private Integer id;
    private GameTable table;
    private GameStatuses status = GameStatuses.CREATED;
    private String cells;
    private Integer winnerColor = -1;

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
            nullable = false)
    public GameStatuses getStatus() {
        return status;
    }

    public void setStatus(GameStatuses status) {
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
    public Integer getWinnerColor() {
        return winnerColor;
    }

    public void setWinnerColor(Integer winnerColor) {
        this.winnerColor = winnerColor;
    }
}
