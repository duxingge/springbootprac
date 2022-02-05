package rebuild;

/**
 * @Author wangjiaxing
 * @Date 2022/2/4
 */
public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String _title;
    private Price price;

    public Movie(String title, int priceCode) {
        _title = title;
        setPrice(priceCode);
    }



    public String getTitle() {
        return _title;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(int priceCode) {
        switch (priceCode) {
            case Movie.REGULAR:
                price = new RegularPrice();
                break;
            case Movie.CHILDRENS:
                price = new ChildrenPrice();
                break;
            case Movie.NEW_RELEASE:
                price = new NewReleasePrice();
                break;
            default:
                throw new IllegalArgumentException("Illegal argument of priceCode");
        }
    }

    public double getCharge(int dayRented) {
        return getPrice().getCharge(dayRented);
    }

    public int getFrequentRenterPoints(int dayRented) {
        return getPrice().getFrequentRenterPoints(dayRented);
    }
}

