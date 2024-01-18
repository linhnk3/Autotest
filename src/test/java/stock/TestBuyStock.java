package stock;

import finhay.api.stock.BuySellStock;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import ultilities.ReportLogListener;

public class TestBuyStock {
    private static Logger ReportLog = Logger.getLogger(ReportLogListener.class.getName());
    BuySellStock stock = new BuySellStock();
   //  @Test
    public void TC1_Buy_Stock_Success(){
         Assert.assertEquals(stock.getMessageAPIPostBuyStockSuccess(1153),"Bạn đã đặt lệnh mua thành công. Hãy thư giãn và chờ đợi kết quả nhé!");
         Assert.assertEquals(stock.getStatusBuyStockUser1153(),"IN_PROGRESS");
         Assert.assertEquals(String.valueOf(stock.getQuantityBuyStockUser1153()),"1000");
     }
   // @Test
    public void TC2_Buy_Odd_Stock(){
        Assert.assertEquals(stock.getMessageAPIPostBuyOddStock(1153),"Bạn chỉ mua được lô cổ phiếu chẵn.");
    }

    @Test
    public void TC3_Buy_Odd_Stock(){
       stock.getMessageAPIPostBuyOddStockManyTime(1153);
    }
}
