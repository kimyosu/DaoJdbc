import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
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

        System.out.println("\n\n=== TEST 3: seller findAll  ===");
        list = sellerDao.findAll();
        list.forEach(System.out::println);

        System.out.println("\n=== TEST 4: seller insert  ===");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com",
                LocalDate.of(2007, 12, 3),  4000.0, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());

        DB.closeConnection();


    }
}