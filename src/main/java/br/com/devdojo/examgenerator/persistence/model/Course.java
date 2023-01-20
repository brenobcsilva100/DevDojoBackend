package br.com.devdojo.examgenerator.persistence.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Course extends AbstractEntity{

    @NotEmpty(message = "The field name cannot be empty")
    private String name;
    @ManyToOne(optional = false)
    private ProfessorBreno professorBreno;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProfessorBreno getProfessorBreno() {
        return professorBreno;
    }

    public void setProfessorBreno(ProfessorBreno professorBreno) {
        this.professorBreno = professorBreno;
    }
}
