package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class OrderFinished extends AbstractEvent {

    private Long id;
    private String orderId;
    private String storeId;
    private String menuId;
    private String riderId;
    private String status;

    public OrderFinished(Delivery aggregate){
        super(aggregate);
    }
    public OrderFinished(){
        super();
    }
}
