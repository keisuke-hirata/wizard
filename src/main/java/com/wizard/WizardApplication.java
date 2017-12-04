package com.wizard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class WizardApplication {

	public static void main(String[] args) {
	    String[] disabledCommands = {
            "--spring.shell.command.script.enabled=false",
            "--spring.shell.command.stacktrace.enabled=false"
	    }; 
        String[] fullArgs = StringUtils.concatenateStringArrays(args, disabledCommands);	    
		SpringApplication.run(WizardApplication.class, fullArgs);
	}
}
