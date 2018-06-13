package com.sh.wxa.util;

import com.sh.wxa.JsonMessage;

public class SCPrompt extends JsonMessage {

    private String prompt;

    private Object[] args;

    public SCPrompt(String prompt, Object[] args) {
        this.prompt = prompt;
        this.args = args;
    }

    public static SCPrompt newError(String prompt, Object... args) {
        return new SCPrompt(prompt, args);
    }

}
