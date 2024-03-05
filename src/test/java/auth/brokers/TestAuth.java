package auth.brokers;

import constants.ConfigPath;
import org.testng.annotations.Test;

import java.util.HashMap;

import static vnscbyfinhay.api.auth.BaseAuth.verifyGet;
import static vnscbyfinhay.api.auth.BaseAuth.verifyPut;

public class TestAuth {
    ConfigPath configPath = new ConfigPath("dev");
    @Test
    public void TC_O1_givenInvalidToken_whenJoinBroker_thenExpectedFailAndStatusCode401() throws Exception {
        int brokerId = 1;
        String tokenNull =null;
        verifyPut(
                configPath.JOIN_GROUP_BROKER + brokerId,
                tokenNull,
                new HashMap<String, Object>() {{
                    put("favorite", "true");
                }},
                401,
                "Thông tin xác thực không hợp lệ",
                "InvalidTokenException");
    }

    @Test
    public void TC_O2_givenExpiredToken_whenJoinBroker_thenExpectedFailAndStatusCode401() throws Exception {
        int brokerId = 1;
        String tokenExpired="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjM3MSwiY3VzdF9pZCI6IjAwMDEwMDA0MDEiLCJzY29wZSI6IkxPR0lOIiwiaWF0IjoxNzA0OTQ1NjExLCJleHAiOjE3MDQ5NDkyMTF9.3vAsQ0USf1HNYU3Yavlzk7FOW97LmqkiYGiznc-CuR0";
        verifyPut(
                configPath.JOIN_GROUP_BROKER + brokerId,
                tokenExpired,
                new HashMap<String, Object>() {{
                    put("favorite", "true");
                }},
                401,
                "Thông tin xác thực không hợp lệ",
                "InvalidTokenException");
    }

    @Test
    public void TC_O3_givenExpiredToken_whenGetListBroker_thenExpectedFailAndStatusCode401() throws Exception {
        String tokenExpired="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjM3MSwiY3VzdF9pZCI6IjAwMDEwMDA0MDEiLCJzY29wZSI6IkxPR0lOIiwiaWF0IjoxNzA0OTQ1NjExLCJleHAiOjE3MDQ5NDkyMTF9.3vAsQ0USf1HNYU3Yavlzk7FOW97LmqkiYGiznc-CuR0";
        verifyGet(
                configPath.GET_LIST_BROKER ,
                tokenExpired,
                401,
                "Thông tin xác thực không hợp lệ",
                "InvalidTokenException");
    }

    @Test
    public void TC_O4_givenInvalidToken_whenGetListBroker_thenExpectedFailAndStatusCode401() throws Exception {
        String tokenNull =null;
        verifyGet(
                configPath.GET_LIST_BROKER ,
                tokenNull,
                401,
                "Thông tin xác thực không hợp lệ",
                "InvalidTokenException");
    }

    @Test
    public void TC_O5_givenInvalidToken_whenGetDetailBroker_thenExpectedFailAndStatusCode401() throws Exception {
        String tokenNull =null;
        int brokerId = 1;
        verifyGet(
                configPath.GET_DETAIL_BROKER + brokerId ,
                tokenNull,
                401,
                "Thông tin xác thực không hợp lệ",
                "InvalidTokenException");
    }

    @Test
    public void TC_O6_givenExpiredToken_whenGetDetailBroker_thenExpectedFailAndStatusCode401() throws Exception {
        String tokenExpired="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjM3MSwiY3VzdF9pZCI6IjAwMDEwMDA0MDEiLCJzY29wZSI6IkxPR0lOIiwiaWF0IjoxNzA0OTQ1NjExLCJleHAiOjE3MDQ5NDkyMTF9.3vAsQ0USf1HNYU3Yavlzk7FOW97LmqkiYGiznc-CuR0";
        int brokerId = 1;
        verifyGet(
                configPath.GET_DETAIL_BROKER + brokerId ,
                tokenExpired,
                401,
                "Thông tin xác thực không hợp lệ",
                "InvalidTokenException");
    }

    @Test
    public void TC_O7_givenExpiredToken_whenGetDataProfitAndLoss_thenExpectedFailAndStatusCode401() throws Exception {
        String tokenExpired="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjM3MSwiY3VzdF9pZCI6IjAwMDEwMDA0MDEiLCJzY29wZSI6IkxPR0lOIiwiaWF0IjoxNzA0OTQ1NjExLCJleHAiOjE3MDQ5NDkyMTF9.3vAsQ0USf1HNYU3Yavlzk7FOW97LmqkiYGiznc-CuR0";
        int brokerId = 1;
        verifyGet(
                configPath.GET_PROFIT_AND_LOSS_BROKER + brokerId  + "/deals" ,
                tokenExpired,
                401,
                "Thông tin xác thực không hợp lệ",
                "InvalidTokenException");
    }

    @Test
    public void TC_O8_givenInvalidToken_whenGetDataProfitAndLoss_thenExpectedFailAndStatusCode401() throws Exception {
        String tokenNull =null;
        int brokerId = 1;
        verifyGet(
                configPath.GET_PROFIT_AND_LOSS_BROKER + brokerId  + "/deals" ,
                tokenNull,
                401,
                "Thông tin xác thực không hợp lệ",
                "InvalidTokenException");
    }

//    class BrokerAPI {
//        public void BrokerAPI(String env) {
//            this.env = env;
//        }
//
//        public buildInvestmentPerformanceURL(brokerId) {
//
//        }
//    }
    @Test
    public void TC_O9_givenInvalidToken_whenGetDataInvestmentPerformance_thenExpectedFailAndStatusCode401() throws Exception {
        String tokenNull =null;
        int brokerId = 1;
        verifyGet(
                configPath.GET_INVESTMENT_PERFORMANCE + brokerId  + "/investment-performance" ,
                tokenNull,
                401,
                "Thông tin xác thực không hợp lệ",
                "InvalidTokenException");
    }

    @Test
    public void TC_10_givenExpiredToken_whenGetDataInvestmentPerformance_thenExpectedFailAndStatusCode401() throws Exception {
        String tokenExpired="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjM3MSwiY3VzdF9pZCI6IjAwMDEwMDA0MDEiLCJzY29wZSI6IkxPR0lOIiwiaWF0IjoxNzA0OTQ1NjExLCJleHAiOjE3MDQ5NDkyMTF9.3vAsQ0USf1HNYU3Yavlzk7FOW97LmqkiYGiznc-CuR0";
        int brokerId = 1;
        verifyGet(
                configPath.GET_INVESTMENT_PERFORMANCE + brokerId  + "/investment-performance" ,
                tokenExpired,
                401,
                "Thông tin xác thực không hợp lệ",
                "InvalidTokenException");
    }

    @Test
    public void TC_11_givenExpiredToken_whenGetDataInvestmentPerformance_thenExpectedFailAndStatusCode401() throws Exception {
        String tokenExpired="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjM3MSwiY3VzdF9pZCI6IjAwMDEwMDA0MDEiLCJzY29wZSI6IkxPR0lOIiwiaWF0IjoxNzA0OTQ1NjExLCJleHAiOjE3MDQ5NDkyMTF9.3vAsQ0USf1HNYU3Yavlzk7FOW97LmqkiYGiznc-CuR0";
        int brokerId = 1;
        verifyGet(
                configPath.GET_INVESTMENT_PERFORMANCE + brokerId  + "/investment-performance" ,
                tokenExpired,
                401,
                "Vui lòng đăng nhập lại để tiếp tục sử dụng",
                "UnauthorizedException");
    }

    @Test
    public void TC_12_givenExpiredToken_whenGetDataInvestEffects_thenExpectedFailAndStatusCode401() throws Exception {
        String tokenExpired="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjM3MSwiY3VzdF9pZCI6IjAwMDEwMDA0MDEiLCJzY29wZSI6IkxPR0lOIiwiaWF0IjoxNzA0OTQ1NjExLCJleHAiOjE3MDQ5NDkyMTF9.3vAsQ0USf1HNYU3Yavlzk7FOW97LmqkiYGiznc-CuR0";
        int brokerId = 1;
        verifyGet(
                configPath.GET_INVEST_EFFECTS + brokerId  + "/backtest" ,
                tokenExpired,
                401,
                "Vui lòng đăng nhập lại để tiếp tục sử dụng",
                "UnauthorizedException");
    }

    @Test
    public void TC_13_givenInvalidToken_whenGetDataInvestEffects_thenExpectedFailAndStatusCode401() throws Exception {
        String tokenNull =null;
        int brokerId = 1;
        verifyGet(
                configPath.GET_INVEST_EFFECTS + brokerId  + "/backtest" ,
                tokenNull,
                401,
                "Thông tin xác thực không hợp lệ",
                "InvalidTokenException");
    }
}

