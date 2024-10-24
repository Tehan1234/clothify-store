package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item {
    private String itemCode;
    private String itemName;
    private String itemCategory;
    private String itemSize;
    private Double itemUnitPrice;
    private Integer itemQty;
    private String supplierId;
}
