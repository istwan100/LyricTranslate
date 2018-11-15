
/*
 * Copyright (c) 2018. Peter Fecher
 */

package net.cavefire.isti.lyrictranslate.api;


import org.json.simple.JSONArray;

public class TranslationParser {
    private JSONArray jsonArray;

    public TranslationParser(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public String getText() {

        String text = "";

        for (int i = 0; i < this.jsonArray.size(); i++) {
            text = text + ((JSONArray) this.jsonArray.get(i)).get(0);
        }

        return text;
    }
}
