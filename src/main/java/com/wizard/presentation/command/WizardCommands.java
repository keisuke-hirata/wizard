package com.wizard.presentation.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.Table;

import com.wizard.application.ApplicationManager;
import com.wizard.application.Part;

@ShellComponent
public class WizardCommands {
	
	@Autowired
	private ApplicationManager manager;
	
	/**
	 * 魔術師を生成します
	 * @param name
	 */
	@ShellMethod("create your wizard.")
	public void born(String name) {
		manager.createWizard(name);
	}

	/**
	 * 魔術師のステータスを表示します
	 * @return
	 */
	@ShellMethod("show wizard status.")
	public Table status() {
		return manager.wizardStatus();
	}
	
    /**
     * born 使用可否
     * @return
     */
    public Availability bornAvailability() {
        return manager.getPart() == Part.INIT
            ? Availability.available()
            : Availability.unavailable("Usable at Wizard Part.");
    }
	
    /**
     * status 使用可否
     * @return
     */
    public Availability statusAvailability() {
        return manager.getPart() != Part.INIT
            ? Availability.available()
            : Availability.unavailable("Usable at Adventure/Battle Part.");
    }
	
	


}
