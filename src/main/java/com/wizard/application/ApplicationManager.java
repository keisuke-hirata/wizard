package com.wizard.application;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.EventListener;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.Table;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;
import org.springframework.stereotype.Service;

import com.wizard.application.event.EncountEvent;
import com.wizard.application.event.PartEvent;
import com.wizard.domain.Attack;
import com.wizard.domain.Damage;
import com.wizard.domain.Field;
import com.wizard.domain.Level;
import com.wizard.domain.Magic;
import com.wizard.domain.MagicAttack;
import com.wizard.domain.aggregation.Enemy;
import com.wizard.domain.aggregation.Wizard;

import lombok.Getter;

@Service
@Getter
public class ApplicationManager implements ApplicationEventPublisherAware  {
	
    /** イベントパブリッシャー */
	private ApplicationEventPublisher publisher;
	
	/** ウィザード */
	private Wizard wizard;
	
	/** 敵 */
	private Enemy enemy;

	/**  */
	@Autowired
	private EnemyEncounter enemyEncounter;

	/**  */
	private Part part;
    
	/**  */
	private Timer questTimer;
	
	public ApplicationManager() {
		this.part = Part.INIT;
	}

	/**
	 * 
	 * @param wizardName
	 */
	public void createWizard(String wizardName) {
		this.wizard = new Wizard(wizardName);
		message("Wizard '" + this.wizard.getName() + "' is created.");
		this.moveToAdventurePart();
	}
	
	/**
	 * 
	 * @param field
	 */
	public void quest(Field field) {
	    if(questTimer == null) {
	        questTimer = new Timer();
	        questTimer.schedule(enemyEncounter.createEnemyEncountTask(), 0L, 1000L);
	    }
	}
	
	/**
	 * 
	 * @param event
	 */
    @EventListener
	public void encount(EncountEvent event) {
        	questTimer.cancel();
        	questTimer.purge();
        	questTimer = null;
        	this.enemy = event.getEnemy();
        	this.moveToBattlePart();
	}
	
    /**
     * 
     * @return
     */
	public Table wizardStatus() {
		String[][] wizardData = new String[][] {
			{"Name", wizard.getName()},
			{"Level", wizard.getLevel().getValue().toString()},
			{"Experience", wizard.getExperience().toString()},
			{"HP", wizard.getHitPoint().toString()},
			{"MP", wizard.getMagicPoint().toString()}
		};
		TableModel wizardModel = new ArrayTableModel(wizardData);
		TableBuilder tableBuilder = new TableBuilder(wizardModel);
        return tableBuilder.addFullBorder(BorderStyle.fancy_double).build();
	}

	/**
	 * 
	 * @return
	 */
    public Table enemyStatus() {
        String[][] enemyData = new String[][] {
            {"Name", enemy.getName()},
            {"HP", enemy.getHitPoint().toString()},
            {"Rare", enemy.getRare().name()},
            {"Experience", enemy.getExperience().toString()}
        };
        TableModel enemyModel = new ArrayTableModel(enemyData);
        TableBuilder tableBuilder = new TableBuilder(enemyModel);
        return tableBuilder.addFullBorder(BorderStyle.fancy_double).build();
    }

	
	/**
	 * 
	 * @param magic
	 */
	public void wizardAttack(Magic magic) {
	    message(wizard.getName() + " cast a spell. " + magic.getName());
        Damage damage = wizard.attack(new MagicAttack(magic));
        enemy.damaged(damage);
        message(enemy.getName() + " recieved "+ magic.getAttackPoint() +"points of damage.");
        if(enemy.isDead()) {
            message("enemy is dead.");
            Level level = wizard.getLevel();
            wizard.gainExperience(enemy.getExperience());
            if(level != wizard.getLevel()) {
                message("level up.");
            }
            enemy = null;
            this.moveToAdventurePart();
            return;
        }
        this.enemyAttack();
	}
	
	/**
	 * 
	 */
	private void enemyAttack() {
	    Damage damage = enemy.attack(new Attack(enemy.getAttackPower()));
	    wizard.damaged(damage);
        if(wizard.isDead()) {
            message("you are dead...");
            this.moveToInitPart();
        }
	}
	
    /**
     * 探索パートへ移行
     */
    private void moveToAdventurePart() {
        message("let's go a quest.");
        this.part = Part.SEARCH;
        this.publisher.publishEvent(new PartEvent(this, Part.SEARCH));
    }
    
    /**
     * 戦闘パートへ移行
     */
    private void moveToBattlePart() {
        message("let's attack a enemy");
        this.part = Part.BATTLE;
        this.publisher.publishEvent(new PartEvent(this, Part.BATTLE));
    }

    /**
     * 初期パートへ移行
     */
    private void moveToInitPart() {
        message("reset...");
        this.reset();
        this.part = Part.INIT;
        this.publisher.publishEvent(new PartEvent(this, Part.INIT));
    }
    
    /**
     * 無に帰します
     */
    private void reset() {
        wizard = null;
        enemy = null;
    }

    /**
     * メッセージを表示
     * 
     * @param msgs
     */
    private void message(String ...msgs) {
        System.out.println("");
        for (String msg : msgs) {
            System.out.println(msg);
        }
    }
    
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}
	
}
