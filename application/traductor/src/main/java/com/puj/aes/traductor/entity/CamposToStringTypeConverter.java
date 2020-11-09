package com.puj.aes.traductor.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class CamposToStringTypeConverter implements DynamoDBTypeConverter<String, Campo[]> {
    @Override
    public String convert(final Campo[] campos)  {
        String stringCampos = new Gson().toJson(campos);
        return stringCampos;
    }

    @Override
    public Campo[] unconvert(final String string) {
        Type listType = new TypeToken<Campo[]>() {}.getType();
        Campo[] campos = new Gson().fromJson(string, listType);
        return campos;
    }
}
