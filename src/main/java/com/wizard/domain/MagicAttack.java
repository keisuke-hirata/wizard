package com.wizard.domain;

import lombok.Getter;

@Getter
public class MagicAttack extends Attack {
    
    private Integer magicCost;
    
    public MagicAttack(Magic magic) {
        super(magic.getAttackPoint());
        this.magicCost = magic.getCost();
    }

}
