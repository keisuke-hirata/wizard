package com.wizard.domain;

import lombok.Getter;

@Getter
public enum Magic {
    
    FIRE("fire", 1, 2, 20),
    FRAME("frame", 2, 10, 80),
    EXPLOSION("explosion", 3, 22, 240);

    private String name;
    private Integer level;
    private Integer cost;
    private Integer attackPoint;
    
    private Magic(String name, Integer level, Integer cost, Integer attackPoint) {
        this.name = name;
        this.level = level;
        this.cost = cost;
        this.attackPoint = attackPoint;
    }

}
