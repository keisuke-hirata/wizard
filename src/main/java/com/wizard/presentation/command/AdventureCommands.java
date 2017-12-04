package com.wizard.presentation.command;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.Table;

import com.wizard.application.ApplicationManager;
import com.wizard.application.Part;
import com.wizard.domain.Field;

/**
 * 冒険コマンド
 * 冒険パートでのみ入力可能
 */
@ShellComponent
public class AdventureCommands {
	
	@Autowired
	private ApplicationManager manager;
	
	/**
	 * クエストに出ます。
	 * 行き先は森（forest）か洞窟（cave）になります
	 * @param fieldName
	 */
	@ShellMethod("go to quest field.")
	public void quest(@Pattern(regexp="(forest|cave)", message="forest or cave") String fieldName) {
		System.out.println(fieldName);
		manager.quest(Field.lowerValueOf(fieldName).get());
	}

	/**
     * quest使用可否
     * @return
     */
    public Availability questAvailability() {
        return manager.getPart() == Part.SEARCH
            ? Availability.available()
            : Availability.unavailable("Usable at Adventure Part.");
    }


}
