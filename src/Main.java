import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(1990, 5, 21);
        Department obj = new Department(1, "Books");
        Seller seller = new Seller(21, "Bob Brown", "bob@gmail.com", localDate,
                3000.0, obj);
        System.out.println(obj);
        System.out.println(seller);
    }
}