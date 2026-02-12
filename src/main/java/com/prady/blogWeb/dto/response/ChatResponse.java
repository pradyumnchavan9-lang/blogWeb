package com.prady.blogWeb.dto.response;

import java.util.ArrayList;

public  class ChatResponse {


    public static class Choice{
        public Message message;

        public Message getMessage() {
            return message;
        }
    }

    public static class Message{
        public String role;
        public String content;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public ArrayList<Choice> choices;

    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<Choice> choices) {
        this.choices = choices;
    }
}
