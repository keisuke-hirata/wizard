package com.wizard.application.event;

import org.springframework.context.ApplicationEvent;

import com.wizard.application.Part;

import lombok.Getter;

@Getter
public class PartEvent extends ApplicationEvent {
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Part part;

	public PartEvent(Object source, Part nextPart) {
		super(source);
		this.part = nextPart;
	}

}
