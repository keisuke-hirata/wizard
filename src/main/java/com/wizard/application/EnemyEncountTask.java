package com.wizard.application;

import java.util.Random;
import java.util.TimerTask;

import org.springframework.context.ApplicationEventPublisher;

import com.wizard.application.event.EncountEvent;
import com.wizard.domain.aggregation.Enemy;

public class EnemyEncountTask extends TimerTask {
    
    private ApplicationEventPublisher publisher;
    
    public EnemyEncountTask(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

	@Override
	public void run() {
        System.out.println("");
        System.out.println("search...");
        	if(new Random().nextInt(5) == 0) {
        	    Enemy enemy = new Enemy();
            System.out.println("");
        		System.out.println("enemy is appered. - " + enemy.getName());
    			publisher.publishEvent(new EncountEvent(this, enemy));
        	}
	}
}
