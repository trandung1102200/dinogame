import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author user
 */
public class test {
    public static void main(String[] args){
        long start, end;
        start = System.currentTimeMillis(); // start lấy thời gian theo millisecond
        for (long i=0; i<100000000; i++);    //vòng lặp không thực hiện thêm lệnh nào
        end = System.currentTimeMillis();   // start lấy thời gian theo millisecond
        System.out.println("Time Millis: " + (end - start));
        while()
    }
}
