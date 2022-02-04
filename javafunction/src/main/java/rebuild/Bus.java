package rebuild;

/**
 * @Author wangjiaxing
 * @Date 2022/2/4
 */
public class Bus {
    public static void main(String[] args) {
        Customer c = new Customer("zhangsan");
        Movie m = new Movie("决战",0);
        Movie m2 = new Movie("战狼",1);
        Rental r = new Rental(m,3);
        Rental r2 = new Rental(m2, 4);
        c.addRental(r);
        c.addRental(r2);
        System.out.println(c.statement());
    }
}
