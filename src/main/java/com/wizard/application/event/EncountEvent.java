package com.wizard.application.event;

import org.springframework.context.ApplicationEvent;

import com.wizard.domain.aggregation.Enemy;

import lombok.Getter;

@Getter
public class EncountEvent extends ApplicationEvent {
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Enemy enemy;

	public EncountEvent(Object source, Enemy enemy) {
		super(source);
		this.enemy = enemy;
	}

}
