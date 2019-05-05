package com.oelprince.events.control;

import java.util.UUID;
import javax.json.bind.adapter.JsonbAdapter;

public class UUIDAdapter implements JsonbAdapter<UUID, String> {

    @Override
    public String adaptToJson(UUID uuid) {
        return uuid.toString();
    }

    @Override
    public UUID adaptFromJson(String string) {
        return UUID.fromString(string);
    }

}