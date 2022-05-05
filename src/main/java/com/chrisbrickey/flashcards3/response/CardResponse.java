package com.chrisbrickey.flashcards3.response;

public class CardResponse {
    private long id;
    private String question;
    private String answer;
    private String category;

    public CardResponse(long id, String question, String answer, String category) {
        setId(id);
        setQuestion(question);
        setAnswer(answer);
        setCategory(category);
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
