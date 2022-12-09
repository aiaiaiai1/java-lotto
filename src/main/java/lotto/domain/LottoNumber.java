package lotto.domain;

public class LottoNumber {
    private static final int LOWER_INCLUSIVE = 1;
    public static final int UPPER_INCLUSIVE = 45;
    private final int number;

    public LottoNumber(int number){
        validate(number);
        this.number = number;
    }

    private void validate(int number){
        if(number < LOWER_INCLUSIVE || number > UPPER_INCLUSIVE){
            throw new IllegalArgumentException();
        }
    }

    public int getNumber(){
        return number;
    }
}
