package demo.bank.kata.operation;

public enum EnumTypeOperation {
    DEPOSIT("msg.type.operation.deposit"),
    WITHDRAW("msg.type.operation.withdraw"),

    ;
    private String message;

    EnumTypeOperation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
