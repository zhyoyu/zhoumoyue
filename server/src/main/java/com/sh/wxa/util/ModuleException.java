package com.sh.wxa.util;

public class ModuleException extends RuntimeException {

    private String openId;

    private ErrorType type;

    private String prompt;

    private Object[] promptArgs;

    public ModuleException(String openId, ErrorType type, String prompt, String[] promptArgs) {
        this.openId = openId;
        this.type = type;
        this.prompt = prompt;
        this.promptArgs = promptArgs;
    }

    public String getOpenId() {
        return openId;
    }

    public ErrorType getType() {
        return type;
    }

    public String getPrompt() {
        return prompt;
    }

    public Object[] getPromptArgs() {
        return promptArgs;
    }
}
