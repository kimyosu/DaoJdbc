package model.entities;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Seller implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nme;
    private String email;
    private LocalDate birthdate;
    private Double basesalary;
    private Department department;

    public Seller(Integer id, String nme, String email, LocalDate birthdate, Double basesalary, Department department) {
        this.id = id;
        this.nme = nme;
        this.email = email;
        this.birthdate = birthdate;
        this.basesalary = basesalary;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNme() {
        return nme;
    }

    public void setNme(String nme) {
        this.nme = nme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Double getBasesalary() {
        return basesalary;
    }

    public void setBasesalary(Double basesalary) {
        this.basesalary = basesalary;
    }

    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(id, seller.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
