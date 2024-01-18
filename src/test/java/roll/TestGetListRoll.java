package roll;

import vnscbyfinhay.api.roll.GetListRoll;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestGetListRoll {

    GetListRoll roll= new GetListRoll();
    @Test
    public void TC_O1_Check_Correct_List_Roll() throws Exception {
        JsonPath data = roll.getAPIListRollValid("0001001033");
        List<Object> result = data.getList("result");
        System.out.println("List roll user:\n" +result);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(data.get("message"), "Success");
    }
    @Test
    public void TC_O2_Check_Correct_List_Roll_With_User_Not_Exits_Roll() throws Exception {
        JsonPath data = roll.getAPIListRollValid("0001001033");
        List<Object> result = data.getList("result");
        System.out.println("List roll user:\n" +result);
        Assert.assertTrue(result.isEmpty());
        Assert.assertEquals(data.get("message"), "Success");
        Assert.assertEquals(data.get("error_code"), "0");
    }

    @Test
    public void TC_O3_Check_InCorrect_List_Roll_With_User_Null() throws Exception {
        JsonPath data = roll.getAPIListRollInvalid(null);
        List<Object> result = data.getList("result");
        Assert.assertTrue(result.isEmpty());
        Assert.assertEquals(data.get("message"), "Tiểu khoản truyền vào không hợp lệ");
        Assert.assertEquals(data.get("error_code"), "GET_USER_RIGHT_ERROR");
    }

    @Test
    public void TC_O4_Check_InCorrect_List_Roll_With_User_Invalid() throws Exception {
        JsonPath data = roll.getAPIListRollInvalid("0123456654");
        List<Object> result = data.getList("result");
        Assert.assertTrue(result.isEmpty());
        Assert.assertEquals(data.get("message"), "Tiểu khoản truyền vào không hợp lệ");
        Assert.assertEquals(data.get("error_code"), "GET_USER_RIGHT_ERROR");
    }
}