package by.training.interpol.entity;

import java.util.Objects;

public class Assessment extends Entity{
    private int numberOfMessages;
    private String assessmentText;

    public Assessment() {
    }

    public Assessment(long assessmentId, int numberOfMessages, String assessmentText) {
        super(assessmentId);
        this.numberOfMessages = numberOfMessages;
        this.assessmentText = assessmentText;
    }

    public Assessment(int numberOfMessages, String assessmentText) {
        this.numberOfMessages = numberOfMessages;
        this.assessmentText = assessmentText;
    }

    public int getNumberOfMessages() {
        return numberOfMessages;
    }

    public void setNumberOfMessages(int numberOfMessages) {
        this.numberOfMessages = numberOfMessages;
    }

    public String getAssessmentText() {
        return assessmentText;
    }

    public void setAssessmentText(String assessmentText) {
        this.assessmentText = assessmentText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assessment that = (Assessment) o;
        return  getId() == that.getId() &&
                numberOfMessages == that.numberOfMessages &&
                Objects.equals(assessmentText, that.assessmentText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), numberOfMessages, assessmentText);
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "numberOfMessages=" + numberOfMessages +
                ", assessmentText='" + assessmentText + '\'' +
                ", id=" + getId() +
                '}';
    }
}
