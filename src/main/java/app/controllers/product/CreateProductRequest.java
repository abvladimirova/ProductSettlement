package app.controllers.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import app.product.entity.Agreement;
import app.product.entity.PropertyData;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Getter
public class CreateProductRequest {

    Integer instanceId; //Идентификатор экземпляра продукта: Если есть, то создаётся ДопСоглашение к ЭП (договору) Если NULL/пусто, то создаётся новый ЭП

    @NotBlank(message = "Значение обязательного параметра productType не заполнено")
    String productType; //Тип Экземпляра Продукта: Enum (НСО, СМО, ЕЖО, ДБДС, договор)

    @NotBlank(message = "Значение обязательного параметра productCode не заполнено")
    String productCode; // Код продукта в каталоге продуктов

    @NotBlank(message = "Значение обязательного параметра registerType не заполнено")
    String registerType;

    @NotBlank(message = "Значение обязательного параметра mdmCode не заполнено")
    String mdmCode;

    @NotBlank(message = "Значение обязательного параметра contractNumber не заполнено")
    String contractNumber; // Номер договора обслуживания

    //@NotNull(message = "Значение обязательного параметра contractDate не заполнено")
    Timestamp contractDate; //Дата заключения договора обслуживания

    @NotNull(message = "Значение обязательного параметра priority не заполнено")
    int priority; //Числовое значение, определяющее последовательность расчета %%

    float interestRatePenalty; //Штрафная процентная ставка
    BigDecimal minimalBalance;
    BigDecimal thresholdAmount;
    String accountingDetails; //Реквизиты выплаты
    String rateType; //Выбор ставки в зависимости от суммы: enum, дифференцированная 0 /прогрессивная 1
    float taxPercentageRate; //Ставка налогообложения
    BigDecimal technicalOverdraftLimitAmount; //Сумма лимита технического овердрафта
    int contractId; // Ссылка на договор обслуживания счета
    String BranchCode; // Код филиала
    String IsoCurrencyCode; //Код валюты
    String urgencyCode;//Код срочности договора
    int ReferenceCode; //Код точки продаж
    PropertyData additionalProperties; //массив дополнительных признаков для сегмента КИБ(VIP) ключ+значение
    List<Agreement> instanceArrangement;
}

