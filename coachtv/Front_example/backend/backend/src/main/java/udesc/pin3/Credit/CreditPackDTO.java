package udesc.pin3.Credit;

import udesc.pin3.User.UserDTO;

import javax.validation.constraints.NotBlank;

public class CreditPackDTO {

    private UserDTO userDTO;

    @NotBlank(message = "Para efetuar a compra de créditos, o título do pacote deve ser informado!")
    private String title;

    private int amount;

    private float price;

    public CreditPackDTO(UserDTO userDTO, String title) {
        this.userDTO = userDTO;
        this.title = title;
    }

    public CreditPackDTO(CreditPack creditPack){
        this.title = creditPack.getTitle();
        this.amount = creditPack.getAmount();
        this.price = creditPack.getPrice();
    }

    public CreditPackDTO() {
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
