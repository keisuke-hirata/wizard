package com.wizard.presentation.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.Table;

import com.wizard.application.ApplicationManager;
import com.wizard.application.Part;
import com.wizard.domain.Magic;

/**
 * 戦闘コマンド
 * 戦闘パートでのみ入力可能
 */
@ShellComponent
public class BattleCommands {
    
    @Autowired
    private ApplicationManager manager;

    /**
     * アナライズ
     * @return
     */
    @ShellMethod("show enemy status.")
    public Table analyze() {
        return manager.enemyStatus();
    }

	/**
	 * ファイヤー
	 */
	@ShellMethod("fire!")
	public void fire() {
	    manager.wizardAttack(Magic.FIRE);
	}
	
	/**
	 * フレイム
	 */
	@ShellMethod("flame!!")
	public void flame() {
        manager.wizardAttack(Magic.FRAME);
	}
	
	/**
	 * エクスプロージョン
	 */
	@ShellMethod("explosion!!!")
	public void explosion() {
        manager.wizardAttack(Magic.EXPLOSION);
	}
	
    /**
     * アナライズ使用可否
     * @return
     */
    public Availability analyzeAvailability() {
        return manager.getPart() == Part.BATTLE
            ? Availability.available()
            : Availability.unavailable("Usable at Battle Part.");
    }
    
    /**
     * ファイヤー使用可否
     * @return
     */
    public Availability fireAvailability() {
        return manager.getPart() == Part.BATTLE
            ? Availability.available()
            : Availability.unavailable("Usable at Battle Part.");
    }
    
    /**
     * フレイム使用可否
     * @return
     */
    public Availability flameAvailability() {
        return manager.getPart() == Part.BATTLE && manager.getWizard().getLevel().getValue() >= Magic.FRAME.getLevel()
            ? Availability.available()
            : Availability.unavailable("Usable at Battle Part. And Level 2 magic.");
    }
    
    /**
     * エクスプロージョン使用可否
     * @return
     */
    public Availability explosionAvailability() {
        return manager.getPart() == Part.BATTLE && manager.getWizard().getLevel().getValue() >= Magic.EXPLOSION.getLevel()
            ? Availability.available()
            : Availability.unavailable("Usable at Battle Part. And Level 3 magic.");
    }
}
