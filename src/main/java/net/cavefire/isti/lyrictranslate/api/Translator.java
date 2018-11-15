package net.cavefire.isti.lyrictranslate.api;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translator {

    JSONArray jsonResponse;
    TranslationParser translationParser;

    public String translate(String inputText, String inputLang, String targetLang) {
        String url = null;
        try {
            url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=" + inputLang + "&tl=" + targetLang + "&dt=t&q=" + URLEncoder.encode(inputText, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported Encoding!");
            return null;
        }

        try {
            jsonRequest(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        //String rt = (String) ((JSONArray)((JSONArray) result.get(0)).get(0)).get(0);
        String rt = translationParser.getText();

        return rt;
    }

    private JSONArray jsonRequest(String url) throws IOException, ParseException {
        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();

        //DEBUG
        System.out.println("Sending GET request to URL: " + url);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONParser jsonParser = new JSONParser();
        this.jsonResponse = (JSONArray) jsonParser.parse(response.toString());
        this.translationParser = new TranslationParser((JSONArray) this.jsonResponse.get(0));

        System.out.println(jsonResponse.toJSONString());

        return this.jsonResponse;
    }

    public String getStartingLanguage() {
        return (String) this.jsonResponse.get(2);
    }
}
