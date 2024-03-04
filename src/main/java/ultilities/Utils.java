package ultilities;

import java.math.BigDecimal;

public class Utils {
    public static BigDecimal toBigDecimal(Object o) {
        return new BigDecimal(String.valueOf(o));
    }
}
