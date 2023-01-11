package fooddelivery.domain;

import fooddelivery.domain.Accepted;
import fooddelivery.domain.CookStarted;
import fooddelivery.domain.Rejected;
import fooddelivery.domain.CookFinished;
import fooddelivery.StoreApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="StoreOrder_table")
@Data

public class StoreOrder  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String orderId;
    
    
    
    
    
    private String storeId;
    
    
    
    
    
    private String menuId;
    
    
    
    
    
    private String qty;
    
    
    
    
    
    private String address;
    
    
    
    
    
    private String status;

    @PostPersist
    public void onPostPersist(){


        Accepted accepted = new Accepted(this);
        accepted.publishAfterCommit();

    }
    @PostUpdate
    public void onPostUpdate(){


        CookStarted cookStarted = new CookStarted(this);
        cookStarted.publishAfterCommit();



        Rejected rejected = new Rejected(this);
        rejected.publishAfterCommit();


        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.


        fooddelivery.external.Delivery delivery = new fooddelivery.external.Delivery();
        // mappings goes here
        StoreApplication.applicationContext.getBean(fooddelivery.external.DeliveryService.class)
            .pick(delivery);


        CookFinished cookFinished = new CookFinished(this);
        cookFinished.publishAfterCommit();

    }
    @PrePersist
    public void onPrePersist(){
    }

    public static StoreOrderRepository repository(){
        StoreOrderRepository storeOrderRepository = StoreApplication.applicationContext.getBean(StoreOrderRepository.class);
        return storeOrderRepository;
    }




    public static void orderInfoTransfer(OrderPlaced orderPlaced){

        /** Example 1:  new item 
        StoreOrder storeOrder = new StoreOrder();
        repository().save(storeOrder);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(storeOrder->{
            
            storeOrder // do something
            repository().save(storeOrder);


         });
        */

        
    }
    public static void cancelCook(OrderCancelled orderCancelled){

        /** Example 1:  new item 
        StoreOrder storeOrder = new StoreOrder();
        repository().save(storeOrder);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCancelled.get???()).ifPresent(storeOrder->{
            
            storeOrder // do something
            repository().save(storeOrder);


         });
        */

        
    }


}
