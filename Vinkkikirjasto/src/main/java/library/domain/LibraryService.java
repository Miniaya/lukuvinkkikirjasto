package library.domain;

import java.util.*;
import library.dao.LibraryDao;

public class LibraryService {

    // Tänne sovelluslogiikkaan liittyvät metodit, esim tietokannan käyttö
    private LibraryDao libraryDao;

    public LibraryService(LibraryDao dao) {
        this.libraryDao = dao;
    }

    public boolean add(String suggestionType, String[] detailTypes, String[] detailValues) {
        boolean success = false;
        if (suggestionType.equals("book")) {
            Suggestion book = new Book();
            detailValues[3] = trimTags(3, detailValues);
            book.addDetails(detailTypes, detailValues);

            success = libraryDao.add(book);
        } else if (suggestionType.equals("article")) {
            Suggestion article = new Article();
            detailValues[2] = trimTags(2, detailValues);
            article.addDetails(detailTypes, detailValues);

            success = libraryDao.add(article);
        }

        return success;
    }
    
    public boolean updateTags(String suggestionType, String name, String tag) {
        boolean success = false;
        if (suggestionType.equals("book")) {
            List<Suggestion> suggestions = listAll();
            for (Suggestion suggestion: suggestions) {
                if (suggestion.getDetail("nimi").equals(name)) {
                    String tags = suggestion.getDetail("tagit");
                    if (tags.equals("") ) {
                        String updatedTags = tag;
                        success = libraryDao.updateBookTag(name, updatedTags);
                    } else if (tags.contains(tag)){
                        success = true;
                    } else {
                        String updatedTags = tags + " " + tag;
                        success = libraryDao.updateBookTag(name, updatedTags);
                    }
                }
            }
        } else if (suggestionType.equals("article")) {
            List<Suggestion> suggestions = listAll();
            for (Suggestion suggestion: suggestions) {
                if (suggestion.getDetail("nimi").equals(name)) {
                    String tags = suggestion.getDetail("tagit");
                    if (tags.equals("")) {
                        String updatedTags = tag;
                        success = libraryDao.updateArticleTag(name, updatedTags);
                    } else if (tags.contains(tag)) {
                        success = true;
                    } else {
                        String updatedTags = tags + " " + tag;
                        success = libraryDao.updateArticleTag(name, updatedTags);
                    }
                }
            }
        }
        return success;
    }
    
    public boolean deleteTags(String suggestionType, String name, String tag) {
        boolean success = false;
        if (suggestionType.equals("book")) {
            List<Suggestion> suggestions = listAll();
            for (Suggestion suggestion: suggestions) {
                if (suggestion.getDetail("nimi").equals(name)) {
                    String tags = suggestion.getDetail("tagit");
                    String updatedTags = deleteChosenTag(tags, tag);
                    success = libraryDao.updateBookTag(name, updatedTags);
                }
            }
        } else if (suggestionType.equals("article")) {
            List<Suggestion> suggestions = listAll();
            for (Suggestion suggestion: suggestions) {
                if (suggestion.getDetail("nimi").equals(name)) {
                    String tags = suggestion.getDetail("tagit");
                    String updatedTags = deleteChosenTag(tags, tag);
                    success = libraryDao.updateArticleTag(name, updatedTags);
                }
            }
        }
        return success;
    }
    
    private String deleteChosenTag(String tags, String tagToDelete) {
        String[] tagsToArray = tags.split("\\s+");
        String updatedTag = "";
        for (int i = 0; i < tagsToArray.length; i++) {
            if (!tagsToArray[i].equals(tagToDelete)) {
                updatedTag = updatedTag + " " + tagsToArray[i];
            }
        }
        return updatedTag;
    }

    private String trimTags(int index, String[] detailValues) {
        String tagInput = detailValues[index].toLowerCase();
        Set<String> tags = new HashSet<String>(Arrays.asList(tagInput.trim().split("\\s*,\\s*")));
        StringBuilder tagString = new StringBuilder();

        Iterator iterator = tags.iterator();
        while(iterator.hasNext()) {
            tagString.append(iterator.next());
            tagString.append(" ");
        }

        return tagString.toString().trim();
    }

    public boolean remove(String name, String type) {
        return libraryDao.remove(name, type);
    }

    public boolean update(String type, String name, String pages) {
        return libraryDao.update(type, name, pages);
    }
    
    public List<Suggestion> listAll() {
        return libraryDao.getAll();
    }
    
    /*
    tag annetaan lowercasena
    */
    public List<Suggestion> searchByTag(String tag) {
        List<Suggestion> sugs = libraryDao.getSuggestionsByTag(tag);
        if (sugs == null) {
            return new ArrayList<Suggestion>();
        }
        return sugs;
    }
}
