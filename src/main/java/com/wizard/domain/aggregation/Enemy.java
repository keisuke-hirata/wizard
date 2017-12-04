package com.wizard.domain.aggregation;

import com.wizard.domain.Attack;
import com.wizard.domain.Attackable;
import com.wizard.domain.Damage;
import com.wizard.domain.Damageable;
import com.wizard.domain.Rare;
import com.wizard.domain.VitalStatus;

import lombok.Getter;

@Getter
public class Enemy implements Damageable, Attackable {
    
    private String name;
    private Integer hitPoint;
    private VitalStatus vitalStatus;
    private Rare rare;
    private Integer attackPower = 3;
    private Integer experience;
    
    public Enemy() {
        this.name = "bat";
        this.hitPoint = 100;
        this.vitalStatus = VitalStatus.ALIVE;
        this.rare = Rare.NORMAL;
        this.experience = 200;
    }
    
    @Override
    public void damaged(Damage damage) {
        hitPoint -= damage.getPoint();
        if(hitPoint <= 0) {
            vitalStatus = VitalStatus.DEAD;
        }
    }

    @Override
    public Damage attack(Attack attack) {
        return new Damage(attackPower);
    }
    
    // ここでは無さそう
    public boolean isDead() {
        return vitalStatus == VitalStatus.DEAD;
    }

}
