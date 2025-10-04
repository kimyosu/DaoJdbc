package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJdbc implements DepartmentDao {
    private Connection connection;

    public DepartmentDaoJdbc(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void insert(Department obj) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "insert into department values (default, ?)"
            );
            preparedStatement.setString(1, obj.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "update department " +
                            "set Name =  ? " +
                            "where id = ?"
            );
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(2, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("" +
                    "delete from department " +
                    "where id = ?");
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "select * from department " +
                            "where id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return new Department(resultSet.getInt(id), resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departmentList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(
                    "select * from department"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                departmentList.add(new Department(resultSet.getInt("Id"), resultSet.getString("Name")));
            }
        }catch (SQLException e){
            throw  new DbException(e.getMessage());
        }
        return departmentList;
    }
}
