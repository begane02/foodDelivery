package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class Notified extends AbstractEvent {

    private Long id;

    public Notified(Notification aggregate){
        super(aggregate);
    }
    public Notified(){
        super();
    }
}
