package net.cavefire.isti.lyrictranslate;

import net.cavefire.isti.lyrictranslate.api.Translator;

public class Main {

    public Translator translator;

    public Main(String[] args){
        this.translator = new Translator();
        LyricTranslator lyricTranslator = new LyricTranslator(this, args);
        lyricTranslator.translateFile();

        //String translated = translator.translate("Hallo", "de", "en");
        //System.out.println(translated);
    }

    public static void main(String[] args) {
        String[] languages = new String[]{};
        for (String arg : args) {
            arg = arg.replaceAll(" ", "");
            if (arg.startsWith("--langs=") || arg.startsWith("-l=")) {
                String language = arg.substring(arg.split("=")[0].length() + 1);
                languages = language.split(",");
            }
        }
        new Main(languages);
    }
}
