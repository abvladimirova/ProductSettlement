package app.productregister.entity;

public enum TppProductRegisterStates {
    OPEN ("Открыт"),
    RESERVED("Зарезервирован"),
    CLOSED("Закрыт"),
    DELETED("Удалён");

    public final String stateName;

    TppProductRegisterStates(String state) {
        stateName = state;
    }
}
