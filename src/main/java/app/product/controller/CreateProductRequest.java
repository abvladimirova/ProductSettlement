package app.product.controller;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import app.product.entity.Agreement;
import app.product.entity.PropertyData;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class CreateProductRequest {

    final Integer instanceId; //Идентификатор экземпляра продукта: Если есть, то создаётся ДопСоглашение к ЭП (договору) Если NULL/пусто, то создаётся новый ЭП

    @NotNull(message = "Значение обязательного параметра productType не заполнено")
    final String productType; //Тип Экземпляра Продукта: Enum (НСО, СМО, ЕЖО, ДБДС, договор)

    @NotNull(message = "Значение обязательного параметра productCode не заполнено")
    final String productCode; // Код продукта в каталоге продуктов

    @NotNull(message = "Значение обязательного параметра registerType не заполнено")
    final String registerType;

    @NotNull(message = "Значение обязательного параметра mdmCode не заполнено")
    final String mdmCode;

    @NotNull(message = "Значение обязательного параметра contractNumber не заполнено")
    final String contractNumber; // Номер договора обслуживания

    @NotNull(message = "Значение обязательного параметра contractDate не заполнено")
    final Timestamp contractDate; //Дата заключения договора обслуживания

    @NotNull(message = "Значение обязательного параметра priority не заполнено")
    final int priority; //Числовое значение, определяющее последовательность расчета %%

    final float interestRatePenalty; //Штрафная процентная ставка
    final BigDecimal minimalBalance;
    final BigDecimal thresholdAmount;
    final String accountingDetails; //Реквизиты выплаты
    final String rateType; //Выбор ставки в зависимости от суммы: enum, дифференцированная 0 /прогрессивная 1
    final float taxPercentageRate; //Ставка налогообложения
    final BigDecimal technicalOverdraftLimitAmount; //Сумма лимита технического овердрафта
    final int contractId; // Ссылка на договор обслуживания счета
    final String BranchCode; // Код филиала
    final String IsoCurrencyCode; //Код валюты
    final String urgencyCode;//Код срочности договора
    final int ReferenceCode; //Код точки продаж
    final PropertyData additionalProperties; //массив дополнительных признаков для сегмента КИБ(VIP) ключ+значение
    final List<Agreement> instanceArrangement;
}

