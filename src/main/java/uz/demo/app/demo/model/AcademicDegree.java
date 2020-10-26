package uz.demo.app.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "main_academic_degree")
public class AcademicDegree {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "institution_name", nullable = false)
    private String institutionName;

    @Column(name = "discipline", nullable = false)
    private String discipline;

    @Column(name = "begin_year")
    private Integer beginYear;

    @Column(name = "end_year")
    private Integer endYear;

    @Column(name = "degree")
    private String degree;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public AcademicDegree() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public Integer getBeginYear() {
        return beginYear;
    }

    public void setBeginYear(Integer beginYear) {
        this.beginYear = beginYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "AcademicDegree{" +
                "id=" + id +
                ", institutionName='" + institutionName + '\'' +
                ", discipline='" + discipline + '\'' +
                ", beginYear=" + beginYear +
                ", endYear=" + endYear +
                ", degree='" + degree + '\'' +
                ", employee=" + employee.getFullName() +
                '}';
    }
}
