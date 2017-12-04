package com.wizard.domain.aggregation;

import com.wizard.domain.Attack;
import com.wizard.domain.Attackable;
import com.wizard.domain.Damage;
import com.wizard.domain.Damageable;
import com.wizard.domain.Level;
import com.wizard.domain.MagicAttack;
import com.wizard.domain.VitalStatus;

import lombok.Getter;

/**
 * 魔術師
 */
@Getter
public class Wizard implements Damageable, Attackable {

    /** 名前 */
    private String name;

    /** レベル */
    private Level level;
    
    /** 経験値 */
    private Integer experience;
    
    /** HP */
    private Integer hitPoint;
    
    /** MP */
    private Integer magicPoint;

    /** 生存状態 */
	private VitalStatus vitalStatus;
    
    public Wizard(String name) {
        this.name = name;
        this.level = Level.ONE;
        this.experience = 0;
        this.hitPoint = 100;
        this.magicPoint = 100;
		this.vitalStatus = VitalStatus.ALIVE;
    }

    /**
     * 経験値を得る
     * @param experience
     */
    public void gainExperience(Integer experience) {
        this.experience += experience;
        this.level = Level.valueOf(this.experience);
    }

    /**
     * ダメージを受ける
     */
	@Override
	public void damaged(Damage damege) {
		this.hitPoint -= damege.getPoint();
        if(hitPoint <= 0) {
            vitalStatus = VitalStatus.DEAD;
        }
	}

	/**
	 * 攻撃する
	 */
    @Override
    public Damage attack(Attack attack) {
        MagicAttack magicAttack = (MagicAttack)attack;
        this.magicPoint -= magicAttack.getMagicCost();
        return new Damage(magicAttack.getPoint());
    }
    
    // ここではなさそう
    public boolean isDead() {
        return vitalStatus == VitalStatus.DEAD;
    }
}