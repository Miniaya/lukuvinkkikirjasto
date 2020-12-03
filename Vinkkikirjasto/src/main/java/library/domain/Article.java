/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.domain;

import java.util.HashMap;

/**
 *
 * @author saasini
 */
public class Article implements Suggestion {
    
    private String[] detailTypes;
    private HashMap<String, String> details;
    
    public Article(String name, String url, String tags) {
        this.detailTypes = new String[]{"nimi", "url", "tagit"};
        this.details = new HashMap<>();
        details.put("nimi", name);
        details.put("url", url);
        details.put("tagit", tags);
    }
    
    public Article() {
        this.details = new HashMap<String, String>();
    }

     public void setName(String name) {
        details.put("nimi", name);
    }

    public void setUrl(String url) {
        details.put("url", url);
    }

    public String getName() {
        return details.get("nimi");
    }

    public String getUrl() {
        return details.get("url");
    }
    
    @Override
    public String toString() {
        return "Tyyppi: Artikkeli" 
                + "\nNimi: " + details.get("nimi") 
                + "\nUrl: " + details.get("url")
                + "\nTagit: " + details.get("tagit");
    }
    
    @Override
    public void addDetail(String detailType, String value) {
        details.put(detailType, value);
    }

    @Override
    public void addDetails(String[] detailTypes, String[] values) {
        this.detailTypes = detailTypes;
        for (int i = 0; i < detailTypes.length; i++) {
                this.details.put(detailTypes[i], values[i]);
            }
    }

    @Override
    public String[] getDetailTypes() {
        return detailTypes;
    }
    
    @Override
    public String getDetail(String detailType) {
        return details.getOrDefault(detailType, "-1");
    }

    @Override
    public String getType() {
        return "Article";
    }
}
