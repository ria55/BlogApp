package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
























    // if called with an empty matrix, result will be NaN! -- Y?? because of division by zero?
    public static double atlag(int[][] szamok) {
        double atlag = 0.0;
        double sum = 0.0;
        for (int sorindex = 0; sorindex < szamok.length; sorindex++) {
            int[] sor = szamok[sorindex];
            for (int oszlop = 0; oszlop < sor.length; oszlop++) {
                sum += sor[sorindex];
            }
        }
        atlag = sum / szamok.length;
        return atlag;
    }

}