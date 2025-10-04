import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(3);

        System.out.println("=== TEST 1: seller findById ===");
        System.out.println(seller);

        System.out.println("\n=== TEST 2: seller findByDepartment  ===");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartmentId(department);

        list.forEach(System.out::println);

        DB.closeConnection();


    }
}