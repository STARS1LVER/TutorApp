package star.tutorApp.Student.Dtos;

import java.util.List;

public class SubjectWithTutorsDto {
    private Long id;
    private String name;
    private Integer semester;
    private Integer credits;
    private Integer code;
    private String description;
    private String img_bs64;
    private List<TutorDto> tutors;

    public SubjectWithTutorsDto(Long id, String name, Integer semester, Integer credits, List<TutorDto> tutors, Integer code, String description, String img_bs64) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.credits = credits;
        this.tutors = tutors;
        this.code = code;
        this.description = description;
        this.img_bs64 = img_bs64;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_bs64() {
        return img_bs64;
    }

    public void setImg_bs64(String img_bs64) {
        this.img_bs64 = img_bs64;
    }
}
