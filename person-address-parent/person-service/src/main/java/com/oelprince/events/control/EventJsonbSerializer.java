package com.oelprince.events.control;

import com.oelprince.events.entity.PersonAddressEvent;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

public class EventJsonbSerializer implements JsonbSerializer<PersonAddressEvent> {

    @Override
    public void serialize(final PersonAddressEvent event, final JsonGenerator generator, final SerializationContext ctx) {
        generator.writeStartObject();
        generator.write("class", event.getClass().getCanonicalName());
        ctx.serialize("data", event, generator);
        generator.writeEnd();
        generator.close();
    }

}
