package db;

import model.dao.DaoFactory;
import model.dao.impl.DepartmentDaoJdbc;
import model.entities.Department;

import java.sql.PreparedStatement;

public class Main2 {
    public static void main(String[] args) {
        DepartmentDaoJdbc departmentDaoJdbc = DaoFactory.createDepartmentDao();
        //departmentDaoJdbc.insert(new Department("D2"));
        departmentDaoJdbc.update(new Department(8, "Arts"));
        System.out.println(departmentDaoJdbc.findById(1));
        departmentDaoJdbc.findAll().forEach(System.out::println);

    }
}
