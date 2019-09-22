package com.ddv.test.model;

import java.util.Map;

public class Text implements ParagraphElement {
    private String text;
    private String decorateType;
    private Map<String, String> decorateAtttributes;

    public String getDecorateType() {
        return decorateType;
    }

    public void setDecorateType(String decorateType) {
        this.decorateType = decorateType;
    }

    public Map<String, String> getDecorateAtttributes() {
        return decorateAtttributes;
    }

    public void setDecorateAtttributes(Map<String, String> decorateAtttributes) {
        this.decorateAtttributes = decorateAtttributes;
    }

    public Text(String text) {
        setText(text);
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
}
