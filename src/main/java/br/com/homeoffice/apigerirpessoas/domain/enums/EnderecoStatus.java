package br.com.homeoffice.apigerirpessoas.domain.enums;

public enum EnderecoStatus {
    PRINCIPAL(1),
    SECUNDARIO(2);

    private int code;

    EnderecoStatus(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
    public static EnderecoStatus valueOf(int code) {
        for (EnderecoStatus value : EnderecoStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Codigo do EndereçoStatus Invalido");
    }

}
