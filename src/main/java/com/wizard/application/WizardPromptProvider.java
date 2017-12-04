package com.wizard.application;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.context.event.EventListener;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

import com.wizard.application.event.PartEvent;

/**
 * Spring Shellのカスタムプロンプト
 * パートに従い、入力
 */
@Component
public class WizardPromptProvider implements PromptProvider {
	
	private Part currentPart;
	
	public WizardPromptProvider() {
		currentPart = Part.INIT;
	}

    @Override
    public AttributedString getPrompt() {
    	
        	switch(this.currentPart) {
        	    	case INIT:
        	    		return new AttributedString("Wizard:>",
        	    				AttributedStyle.DEFAULT.foreground(AttributedStyle.BLACK));
    
        	    	case SEARCH:
        	    		return new AttributedString("Adventure:>",
        	    				AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
        	    	
        	    	case BATTLE:
        	    		return new AttributedString("Battle:>",
        	    				AttributedStyle.DEFAULT.foreground(AttributedStyle.RED));
        		default:
        			throw new AssertionError();
        	}
    }

    @EventListener
    public void handle(PartEvent event) {
        this.currentPart = event.getPart();
    }
}