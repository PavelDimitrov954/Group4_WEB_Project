package com.example.group4_web_project.exceptions;

public class EntityDuplicateException extends RuntimeException {

    public EntityDuplicateException(String value) {
        super(String.format("User %s is already an admin.", value));
    }

    public EntityDuplicateException(String type, String attribute, String value) {
        super(String.format("%s with %s %s already exists.", type, attribute, value));
    }


}
