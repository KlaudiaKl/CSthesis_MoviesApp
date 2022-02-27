package com.example.application.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchModel {
    private int page;
    @SerializedName(value = "total_results")
    private int totalResults;
    @SerializedName(value = "total_pages")
    private int totalPages;

    private List<SearchModelItem> results;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getPage() {
        return page;
    }

    public List<SearchModelItem> getResults() {
        return results;
    }

    public void setResults(List<SearchModelItem> results) {
        this.results = results;
    }
}
