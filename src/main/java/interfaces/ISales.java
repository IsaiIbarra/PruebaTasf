package interfaces;

import dtos.SalesDto;
import java.util.List;

public interface ISales {
    public List<SalesDto> getSales();
    public int validateExistSale(SalesDto sale);
    public int addSale(SalesDto sale);
    public int editSale(SalesDto sale);
    public SalesDto findSale(int id);
}
