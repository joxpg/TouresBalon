package com.puj.aes.traductor.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CamposToStringTypeConverter implements DynamoDBTypeConverter<String, List<Campo>> {
    @Override
    public String convert(final List<Campo> campos) {
        return campos.toString();
    }

    @Override
    public List<Campo> unconvert(final String string) {
        //List<Campo> campos = new ArrayList<>();
        /*String regexpStr = "(\\[([0-9]+),\\s*([0-9a-zA-Z]+),\\])";
        //String inputData = "[11, john,][23, Adam,][88, Angie,]";

        Pattern regexp = Pattern.compile(regexpStr);
        Matcher matcher = regexp.matcher(string);
        while (matcher.find()) {
            MatchResult result = matcher.toMatchResult();

            String nombre = result.group(2);
            String valorDominio = result.group(3);
            String valorProveedor = result.group(4);

            Campo campo = new Campo(nombre, valorDominio, valorProveedor);
            campos.add(campo);
        }*/
        //return campos.toArray(Campo[]::new);
        return null;
    }
}
