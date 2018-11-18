package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "thing")
public class Thing implements Validator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String magicScript;

    private int manaNeeded;

    public Thing() {
    }

    public Thing(String name, String magicScript, int manaNeeded) {
        this.name = name;
        this.magicScript = magicScript;
        this.manaNeeded = manaNeeded;
    }

    public Thing(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMagicScript() {
        return magicScript;
    }

    public void setMagicScript(String magicScript) {
        this.magicScript = magicScript;
    }

    public int getManaNeeded() {
        return manaNeeded;
    }

    public void setManaNeeded(int manaNeeded) {
        this.manaNeeded = manaNeeded;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Thing.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Thing thing = (Thing) target;
        String thingMagicScript = thing.getMagicScript();
        ValidationUtils.rejectIfEmpty(errors,"magicScript","thingMagicScript.empty");
        if (thingMagicScript.length() < 10 || thingMagicScript.length() > 1000) {
            errors.rejectValue("magicScript", "thingMagicScript.lenght");
        }
        int thingMana = thing.getManaNeeded();
        ValidationUtils.rejectIfEmpty(errors,"manaNeeded","thingMana.empty");
        if (thingMana < 0) {
            errors.rejectValue("manaNeeded", "thingMana.integer");
        }
    }
}
