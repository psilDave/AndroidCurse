package com.example.agenda.database.converter;

import androidx.room.TypeConverter;

import com.example.agenda.model.TipoTelefone;

public class ConversorTipoTelefone {

    @TypeConverter
    public String paraString(TipoTelefone valor){
        if(valor != null){
            return valor.name();
        }
        return TipoTelefone.FIXO.name();
    }
    @TypeConverter
    public TipoTelefone paraTipoTelefone(String valor){
        if (valor != null){
            return TipoTelefone.valueOf(valor);
        }
        return TipoTelefone.FIXO;
    }
}
