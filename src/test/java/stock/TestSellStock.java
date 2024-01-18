package stock;

import finhay.api.stock.BuySellStock;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import ultilities.ReportLogListener;

public class TestSellStock {
    private static Logger ReportLog = Logger.getLogger(ReportLogListener.class.getName());
    BuySellStock stock = new BuySellStock();
     @Test
    public void TC1_Sell_Stock_Success(){
         Assert.assertEquals(stock.getMessageAPISellStockSuccess(1153),"Bạn đã đặt lệnh bán thành công. Hãy thư giãn và chờ đợi kết quả nhé!");
         Assert.assertEquals(stock.getStatusBuyStockUser1153(),"IN_PROGRESS");
         Assert.assertEquals(String.valueOf(stock.getQuantityBuyStockUser1153()),"1000");
     }
    @Test
    public void TC2_Sell_Odd_Stock(){
        Assert.assertEquals(stock.getMessageAPIPostSellOddStock(1153),"Bạn chỉ bán được lô cổ phiếu chẵn.");
    }
    @Test
    public void TC3_Sell_Stock_Over_Amount_Have(){
        Assert.assertEquals(stock.getMessageAPIPostSellOverStock(1153),"Số lượng cổ phiếu cần bán lớn hơn số lượng đang có");
    }
}
