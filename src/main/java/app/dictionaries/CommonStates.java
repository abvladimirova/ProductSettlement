package app.dictionaries;

public enum CommonStates {
    OPEN ("Открыт"),
    RESERVED("Зарезервирован"),
    CLOSED("Закрыт"),
    DELETED("Удалён");

    public final String stateName;

    CommonStates(String state) {
        stateName = state;
    }
}
