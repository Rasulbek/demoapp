package uz.demo.app.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "main_work_place")
public class Workplace {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "employer_name", nullable = false)
    private String employerName;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "begin_year")
    private Integer beginYear;

    @Column(name = "end_year")
    private Integer endYear;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Workplace() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Workplace{" +
                "id=" + id +
                ", employerName='" + employerName + '\'' +
                ", position='" + position + '\'' +
                ", beginYear=" + beginYear +
                ", endYear=" + endYear +
                ", employee=" + employee +
                '}';
    }
}
