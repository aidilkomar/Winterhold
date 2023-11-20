package com.indocyber.dto.author;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AuthorGridDto {

    private Integer id;

    private String fullName;

    private LocalDate birthDate;

    private LocalDate deceasedDate;

    private String education;

    private String summary;

    public AuthorGridDto() {
    }

    public AuthorGridDto(Integer id, String fullName, LocalDate birthDate, LocalDate deceasedDate, String education, String summary) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.deceasedDate = deceasedDate;
        this.education = education;
        this.summary = summary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getDeceasedDate() {
        if(deceasedDate != null){
            return "Deceased";
        }
        return "Alive";
    }

    public void setDeceasedDate(LocalDate deceasedDate) {
        this.deceasedDate = deceasedDate;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getAge() {
        if (this.deceasedDate == null){
            return ChronoUnit.YEARS.between(this.birthDate, LocalDate.now());
        } else {
            return ChronoUnit.YEARS.between(this.birthDate, this.deceasedDate);
        }
    }
}
