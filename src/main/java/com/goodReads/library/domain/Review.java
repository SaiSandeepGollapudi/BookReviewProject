package com.goodReads.library.domain;

//    @Getter
//    @Setter
//    @AllArgsConstructor
//    @NoArgsConstructor
    public class Review {

        private String id;
        private String comment;
        private String reviewer;
        private Double rating;
        private String bookId;

    public Review() {
    }

    public Review(String id, String comment, String reviewer, Double rating, String bookId) {
        this.id = id;
        this.comment = comment;
        this.reviewer = reviewer;
        this.rating = rating;
        this.bookId = bookId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}

