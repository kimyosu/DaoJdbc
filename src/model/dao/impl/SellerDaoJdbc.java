package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJdbc implements SellerDao {
    private Connection connection;

    public SellerDaoJdbc() {
    }

    public SellerDaoJdbc(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement( //criando o preparedStatement(
                    // responsavel por executar o comando SQL)
                    "select seller.*,department.Name as DepName " + "from seller inner join department " + "on seller.DepartmentId = department.id " + "where seller.Id = ?");/* Explicando o codigo: Seleciona todos os campos da tabela seller e o campo Name da tabela
             department, renomeando-o para DepName. Faz um inner join entre as tabelas seller e department
             com base na correspondência entre seller.DepartmentId e department.id. Filtra os resultados para
             incluir apenas o registro onde seller.Id corresponde ao valor fornecido (representado por ?).
             */

            preparedStatement.setInt(1, id); //Setando o valor do parametro ? com o id recebido no metodo
            resultSet = preparedStatement.executeQuery(); //Executando o comando SQL
            //e armazenando o resultado no resultSet
            if (resultSet.next()) { //acessando o primeiro registro do resultSet
                //caso haja um registro no resultSet (ou seja, se o id existir no banco de dados)
                Department department = instantiateDepartment(resultSet);
                Seller seller = instantiateSeller(resultSet, department);
                return seller;

            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            // Removido DB.closeConnection(); para não fechar a conexão prematuramente
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
        Seller seller = new Seller();
        seller.setId(resultSet.getInt("Id"));
        seller.setName(resultSet.getString("Name"));
        seller.setEmail(resultSet.getString("Email"));
        seller.setBasesalary(resultSet.getDouble("BaseSalary"));
        seller.setBirthdate(resultSet.getDate("BirthDate").toLocalDate());
        seller.setDepartment(department);
        return seller;
        /*
                Explicando o código acima:
                1. Verifica se há um próximo registro no ResultSet usando resultSet.next().
                2. Cria uma nova instância de Department.
                3. Define o ID do departamento usando o valor da coluna "DepartmentId" do ResultSet.
                4. Define o nome do departamento usando o valor da coluna "DepName" do ResultSet.
                5. Cria uma nova instância de Seller.
                6. Define o ID do vendedor usando o valor da coluna "Id" do ResultSet.
                7. Define o nome do vendedor usando o valor da coluna "Name" do ResultSet.
                8. Define o email do vendedor usando o valor da coluna "Email" do ResultSet.
                9. Define o salário base do vendedor usando o valor da coluna "BaseSalary" do ResultSet.
                10. Define a data de nascimento do vendedor convertendo o valor da coluna
                    "BirthDate" do ResultSet para LocalDate.
                11. Associa o departamento criado anteriormente ao vendedor.
                12. Retorna o objeto Seller populado.

                 */
    }

    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("Id"));
        department.setName(resultSet.getString("DepName"));
        return department;
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }

    @Override
    public List<Seller> findByDepartmentId(Department department) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "select seller.*, department.Name as DepName " +
                            "from seller inner join department " +
                            "on seller.DepartmentId  = department.id " +
                            "where DepartmentId = ? " +
                            "order by Name");
            preparedStatement.setInt(1, department.getId());
            resultSet = preparedStatement.executeQuery();
            List<Seller> sellerList = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            while (resultSet.next()) {
                Department dep = map.get(resultSet.getInt("DepartmentId"));
                if (dep == null) {
                    dep = instantiateDepartment(resultSet);
                    map.put(resultSet.getInt("DepartmentId"), dep);
                }
                Seller seller = instantiateSeller(resultSet, department);
                sellerList.add(seller);
            }
            return sellerList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            // Removido DB.closeConnection(); para não fechar a conexão prematuramente
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }
}
