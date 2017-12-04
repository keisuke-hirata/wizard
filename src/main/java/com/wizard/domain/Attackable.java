package com.wizard.domain;

/**
 * 攻撃可能オブジェクトに実装
 */
public interface Attackable {
	
    /**
     * 攻撃時の挙動
     */
	Damage attack(Attack attack);

}
