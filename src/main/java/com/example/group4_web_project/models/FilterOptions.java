package com.example.group4_web_project.models;

import java.util.Optional;

public class FilterOptions {

    private Optional<String> title;
    private Optional<String> createdBy;
    private Optional<String> sortBy;
    private Optional<String> sortOrder;

    public FilterOptions(String title, String createdBy, String sortBy, String sortOrder) {
        this.title = Optional.ofNullable(title);
        this.createdBy = Optional.ofNullable(createdBy);
        this.sortBy = Optional.ofNullable(sortBy);
        this.sortOrder = Optional.ofNullable(sortOrder);
    }

    public Optional<String> getTitle() {
        return title;
    }

    public void setTitle(Optional<String> title) {
        this.title = title;
    }

    public Optional<String> getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Optional<String> createdBy) {
        this.createdBy = createdBy;
    }

    public Optional<String> getSortBy() {
        return sortBy;
    }

    public void setSortBy(Optional<String> sortBy) {
        this.sortBy = sortBy;
    }

    public Optional<String> getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Optional<String> sortOrder) {
        this.sortOrder = sortOrder;
    }
}
