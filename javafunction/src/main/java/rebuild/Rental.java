package rebuild;

/**
 * @Author wangjiaxing
 * @Date 2022/2/4
 */
public class Rental {
    // 影片
    private Movie _movie;
    // 租期
    private int _daysRented;

    public Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    public double getCharge() {
        return getMovie().getCharge(getDaysRented());
    }

    public int getFrequentRenterPoints() {
       return getMovie().getFrequentRenterPoints(getDaysRented());
    }
}

