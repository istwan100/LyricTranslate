package net.cavefire.isti.lyrictranslate;

import net.cavefire.isti.lyrictranslate.api.Translator;

public class Main {

    public Translator translator;

    public Main(){
        this.translator = new Translator();
        LyricTranslator lyricTranslator = new LyricTranslator(this);
        lyricTranslator.translateFile();

        //String translated = translator.translate("Hallo", "de", "en");
        //System.out.println(translated);
    }

    public static void main(String[] args) {
        new Main();
    }
}
