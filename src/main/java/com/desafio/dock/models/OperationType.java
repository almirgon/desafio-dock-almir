package com.desafio.dock.models;

public enum OperationType {
    DEPOSIT("DEPOSIT"),
    WITHDRAW("WITHDRAW");

    private String operation;

    OperationType(String operation){
        this.operation = operation;
    }

    public String getOperation(){
        return operation;
    }
}
