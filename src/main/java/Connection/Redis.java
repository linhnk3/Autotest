package Connection;

import redis.clients.jedis.Jedis;


public class Redis {
    public static void main(String[] args) {
       // System.out.println(getString(15, "markowitz_optimal_strategy"));
    }
    public static String getString(int dbNumber, String key) {
        Jedis jedis = new Jedis("10.0.0.28", 6379);
        jedis.select(dbNumber);
        return jedis.get(key);
    }
}