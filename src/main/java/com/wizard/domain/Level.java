package com.wizard.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

import lombok.Getter;

@Getter
public enum Level implements Comparable<Level> {
    ONE(1, 0),
    TWO(2, 300),
    THREE(3, 600);

    private Integer value;
    private Integer experience;

    Level(Integer value, Integer experience) {
        this.value = value;
        this.experience = experience;
    }
    
    public static Level valueOf(Integer experience) {
        	Level[] levels = values();
        	Optional<Level> optLevel =  Arrays.stream(levels).sorted(new Comparator<Level>() {
        		@Override
        		public int compare(Level l1, Level l2) {
        			return l2.getExperience() - l1.getExperience();
        		}
    		}).filter(level -> level.getExperience() < experience).findFirst();
        	return optLevel.get();
    }
}