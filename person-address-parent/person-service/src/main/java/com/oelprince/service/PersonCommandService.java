package com.oelprince.service;

import com.oelprince.entity.Person;
import com.oelprince.events.control.EventProducer;
import com.oelprince.events.entity.CreatePerson;
import com.oelprince.events.entity.PersonAddressInfo;
import com.oelprince.events.entity.RemovePerson;
import com.oelprince.facade.PersonFacadeLocal;
import com.oelprince.service.control.PersonAddressData;
import com.oelprince.service.control.PersonAddressStatus;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author oelprince
 */
@ApplicationScoped
public class PersonCommandService {
    
    @Inject
    private PersonFacadeLocal personFacade;
    
    @Inject
    private EventProducer eventProducer;
    
    @Inject
    private PersonAddressData personAddressData;
    
    public void createPerson(final Person person) {
        eventProducer.publish(new CreatePerson(new PersonAddressInfo(
                UUID.fromString(person.getId()),
                person.getAddressId() == null ? UUID.randomUUID() : UUID.fromString(person.getAddressId()))));

        personFacade.create(person);
    }
    
    public void updatePerson(final Person person) {
        if(person.getAddressId() != null) {
            eventProducer.publish(new CreatePerson(new PersonAddressInfo(UUID.fromString(person.getId()),UUID.fromString(person.getAddressId()))));
        }
        personFacade.edit(person);
    }
    
    

    public void removePerson(final Person person) {
        if(person.getAddressId() != null) {
            eventProducer.publish(new RemovePerson(new PersonAddressInfo(UUID.fromString(person.getId()),UUID.fromString(person.getAddressId()))));
        }
        personFacade.remove(person);
    }

    public void acceptPerson(UUID personId) {
        final PersonAddressStatus personAddressStatus = personAddressData.get(personId);
        if(personAddressStatus != null) {
            personAddressStatus.accept();
        }
    }

    public void acceptPersonRemoval(Person person) {
        if(person != null) {
            person.setAddressId(null);
            personFacade.edit(person);
        }
    }
   
    
}
