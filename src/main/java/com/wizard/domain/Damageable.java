package com.wizard.domain;

/**
 * 損傷可能オブジェクトに実装
 */
public interface Damageable {
	
    /**
     * 損傷時の挙動
     */
	void damaged(Damage damage);

}
