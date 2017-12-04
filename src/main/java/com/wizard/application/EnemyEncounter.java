package com.wizard.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Service
public class EnemyEncounter implements ApplicationEventPublisherAware {

    @Autowired
	private ApplicationEventPublisher publisher;
    
    public EnemyEncountTask createEnemyEncountTask() {
        return new EnemyEncountTask(publisher);
    }

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

}
