package com.bozturk.idle.dto;

import com.bozturk.idle.model.UserListing;

public class UserListingDto extends UserListing {

    String content;

    public UserListingDto() {
        // TODO Auto-generated constructor stub
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
