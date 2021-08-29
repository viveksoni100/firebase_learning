package com.example.firebasedemo.auth.demo.model;

public class AhmedabadRegionModel {

    private String image;
    private String notes_en;
    private String notes_gu;
    private String title_en;
    private String title_gu;

    public AhmedabadRegionModel() {
    }

    public AhmedabadRegionModel(String image, String notes_en, String notes_gu, String title_en, String title_gu) {
        this.image = image;
        this.notes_en = notes_en;
        this.notes_gu = notes_gu;
        this.title_en = title_en;
        this.title_gu = title_gu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNotes_en() {
        return notes_en;
    }

    public void setNotes_en(String notes_en) {
        this.notes_en = notes_en;
    }

    public String getNotes_gu() {
        return notes_gu;
    }

    public void setNotes_gu(String notes_gu) {
        this.notes_gu = notes_gu;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public String getTitle_gu() {
        return title_gu;
    }

    public void setTitle_gu(String title_gu) {
        this.title_gu = title_gu;
    }
}
