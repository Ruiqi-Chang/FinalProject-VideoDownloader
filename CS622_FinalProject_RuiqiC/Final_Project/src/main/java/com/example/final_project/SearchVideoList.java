package com.example.final_project;

public class SearchVideoList {

    private String searchKeyWord;
    private String currentSearchID;
    private String status;


    public SearchVideoList(String searchKeyWord, String currentSearchID, String status) {
        this.setSearchKeyWord(searchKeyWord);
        this.setCurrentSearchID(currentSearchID);
        this.setStatus(status);

    }

    public SearchVideoList() {
        this.searchKeyWord = null;
        this.currentSearchID = null;
        this.status = null;
    }

    public String getCurrentSearchID() {
        return currentSearchID;
    }

    public void setCurrentSearchID(String currentSearchID) {
        this.currentSearchID = currentSearchID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSearchKeyWord() {
        return searchKeyWord;
    }

    public void setSearchKeyWord(String searchKeyWord) {
        this.searchKeyWord = searchKeyWord;
    }

    public String toString() {
        String rtn = "Search Keywords: "+ this.searchKeyWord+"        Status:" +this.status;
        return rtn;
    }


}

