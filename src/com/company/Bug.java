package com.company;

class Bug {
    private String title;
    private String description;
    private String assignDeveloper;
    private boolean solved;

    public Bug(){}

    public Bug(String title, String description, String assignDeveloper, boolean solved){
        this.title = title;
        this.description = description;
        this.assignDeveloper = assignDeveloper;
        this.solved = solved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignDeveloper() {
        return assignDeveloper;
    }

    public void setAssignDeveloper(String assignDeveloper) {
        this.assignDeveloper = assignDeveloper;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }


}
