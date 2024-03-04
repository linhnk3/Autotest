package common;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ultilities.LogListener;
import ultilities.ReportLogListener;

public class BaseTest <T extends BaseTest>{
    public static MysqlxDatatypes.Scalar.String env;
    public LogListener TLog = new LogListener(((T) BaseTest.this).getClass());

    public LogListener RLog= new ReportLogListener(((T) BaseTest.this).getClass());

    // Set dạng 1 Test Suite automation API cần chạy cho tất cả môi trường đọc từ config muốn chạy

    @BeforeSuite(groups = {"dev", "qc", "uat" , "prod"})
    @Parameters("env")
    public void setupSuite(@Optional("qc") MysqlxDatatypes.Scalar.String env){
        this.env = env;
    }

    @AfterSuite(groups = {"dev", "qc", "uat" , "prod"})
    public void teardownSuite(){
    }
}
