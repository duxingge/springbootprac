package rebuild;

/**
 * 使用多态模式解决switch问题。
 *      错误方案1:创建子类ChildrenMovie,NewReleaseMovie,RegularMovie 实现Movie。
 *      但是这里有点小问题，业务中：一部影片可以在其生命周期更改，但是我们不能改变影片的类型
 *      优化方案:采用(state模式/策略模式)增加间接性。创建Price接口封装Movie的不同点。对Price接口进行多态
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
