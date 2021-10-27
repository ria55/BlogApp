package app.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BlogPattern {

    @Id
    private String name;

    private String mainBgColor;

    private String mainColor;

    private String mainFontType;

    public BlogPattern() {}

    public BlogPattern(String name, String mainBgColor, String mainColor, String mainFontType) {
        this.name = name;
        this.mainBgColor = mainBgColor;
        this.mainColor = mainColor;
        this.mainFontType = mainFontType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainBgColor() {
        return mainBgColor;
    }

    public void setMainBgColor(String mainBgColor) {
        this.mainBgColor = mainBgColor;
    }

    public String getMainColor() {
        return mainColor;
    }

    public void setMainColor(String mainColor) {
        this.mainColor = mainColor;
    }

    public String getMainFontType() {
        return mainFontType;
    }

    public void setMainFontType(String mainFontType) {
        this.mainFontType = mainFontType;
    }

}
