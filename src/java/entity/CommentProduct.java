package entity;

import java.sql.Date;

public class CommentProduct {

    private String commentID;
    private String productID;
    private String comment;
    private String userID;
    private String commentImg;
    private Date createdAt;

    public CommentProduct(String commentID, String productID, String comment, String userID, String commentImg, Date createdAt) {
        this.commentID = commentID;
        this.productID = productID;
        this.comment = comment;
        this.userID = userID;
        this.commentImg = commentImg;
        this.createdAt = createdAt;
    }

    public CommentProduct() {
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCommentImg() {
        return commentImg;
    }

    public void setCommentImg(String commentImg) {
        this.commentImg = commentImg;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "CommentProduct{" + "commentID=" + commentID + ", productID=" + productID + ", comment=" + comment + ", userID=" + userID + ", commentImg=" + commentImg + ", createdAt=" + createdAt + '}';
    }
}
