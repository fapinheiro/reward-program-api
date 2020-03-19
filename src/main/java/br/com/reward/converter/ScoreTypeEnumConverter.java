package br.com.reward.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;

import br.com.reward.enums.ScoreTypeEnum;

// @Converter(autoApply = true)
public class ScoreTypeEnumConverter implements AttributeConverter<ScoreTypeEnum, String> {
  
    @Override
    public String convertToDatabaseColumn(ScoreTypeEnum typeEnum) {
        if (typeEnum == null) {
            return null;
        }
        return typeEnum.toString();
    }
 
    @Override
    public ScoreTypeEnum convertToEntityAttribute(String typeStr) {
        if (typeStr == null) {
            return null;
        }
        
        return Stream.of(ScoreTypeEnum.values())
          .filter( typeEnum -> typeEnum.toString().equals(typeStr))
          .findFirst()
          .orElseThrow( () -> new IllegalArgumentException(String.format("Nao encontrado score type id {%s}", typeStr)));
    }
}