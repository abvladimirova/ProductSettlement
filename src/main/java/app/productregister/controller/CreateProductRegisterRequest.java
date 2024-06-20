package app.productregister.controller;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.HashMap;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateProductRegisterRequest {

    @NotNull(message = "Значение обязательного параметра instanceId не заполнено")
    BigInteger instanceId;      //Идентификатор ЭП, к которому привязывается продуктовый регистр
    String registryTypeCode;    //Тип создаваемого продуктового регистра
    String accountType;         //Тип счета: Клиентский или внутрибанковский
    String currencyCode;        //3-х значный код валюты
    String branchCode;          //Код филиала
    String priorityCode;        //Код срочности: Всегда «00» для ПП РО ЮЛ
    String mdmCode;             //МДМ код клиента (КЮЛ)
    String clientCode;          //Код клиента: Только для ВИП (РЖД, ФПК). Обсуждается с клиентом (есть выбор).
    String trainRegion;         //Регион принадлежности железной дороги: Только для ВИП (РЖД, ФПК)
    String counter;             //Счетчик: Только для ВИП (РЖД, ФПК)
    String salesCode;           //Код точки продаж
    HashMap<String,String> data; //additionalProperties массив дополнительных признаков для сегмента КИБ(VIP), добавлять по мере необходимости? на сегодня могут быть переданы 2 пары ключ/значение
}

