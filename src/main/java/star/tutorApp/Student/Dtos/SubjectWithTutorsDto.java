package star.tutorApp.Student.Dtos;

import java.util.List;

public class SubjectWithTutorsDto {
    private Long id;
    private String name;
    private Integer semester;
    private Integer credits;
    private List<TutorDto> tutors;

    public SubjectWithTutorsDto(Long id, String name, Integer semester, Integer credits, List<TutorDto> tutors) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.credits = credits;
        this.tutors = tutors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public List<TutorDto> getTutors() {
        return tutors;
    }

    public void setTutors(List<TutorDto> tutors) {
        this.tutors = tutors;
    }
}
