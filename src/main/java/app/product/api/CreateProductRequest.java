package app.product.api;

import app.common.AdditionalProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import app.agreement.Agreement;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CreateProductRequest {

    //Идентификатор экземпляра продукта: Если есть, создаётся ДопСоглашение к ЭП, иначе создаётся новый ЭП
    BigInteger instanceId;

    //Тип Экземпляра Продукта: Enum (НСО, СМО, ЕЖО, ДБДС, договор)
    @NotBlank(message = "Значение обязательного параметра productType не заполнено")
    String productType;

    // Код продукта в каталоге продуктов
    @NotBlank(message = "Значение обязательного параметра productCode не заполнено")
    String productCode;

    @NotBlank(message = "Значение обязательного параметра registerType не заполнено")
    String registerType;

    @NotBlank(message = "Значение обязательного параметра mdmCode не заполнено")
    String mdmCode;

    // Номер договора обслуживания
    @NotBlank(message = "Значение обязательного параметра contractNumber не заполнено")
    String contractNumber;

    //Дата заключения договора обслуживания
    @NotNull(message = "Значение обязательного параметра contractDate не заполнено")
    String contractDate;

    //Числовое значение, определяющее последовательность расчета %%
    @NotNull(message = "Значение обязательного параметра priority не заполнено")
    int priority;

    float       interestRatePenalty;    //Штрафная процентная ставка
    BigDecimal  minimalBalance;
    BigDecimal  thresholdAmount;
    String      accountingDetails;      //Реквизиты выплаты
    String      rateType;               //Выбор ставки в зависимости от суммы: enum, дифференцированная 0 /прогрессивная 1
    float       taxPercentageRate;      //Ставка налогообложения
    BigDecimal  technicalOverdraftLimitAmount; //Сумма лимита технического овердрафта
    int         contractId;             // Ссылка на договор обслуживания счета
    String      branchCode;             // Код филиала
    String      isoCurrencyCode;        //Код валюты
    String      urgencyCode;            //Код срочности договора
    int         referenceCode;          //Код точки продаж

    List<AdditionalProperty>    additionalProperties; //массив дополнительных признаков для сегмента КИБ(VIP) ключ+значение
    List<Agreement>             instanceArrangement; // массив доп соглашений

}

