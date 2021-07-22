package sample;

import javafx.scene.image.ImageView;

public class Drinks {
    //stores info of a drink
    private String name;
    private Ingredient base = new Ingredient();
    private Ingredient ice = new Ingredient();
    private Ingredient tapioca = new Ingredient();
    private Ingredient toppings = new Ingredient();
    private Ingredient straw = new Ingredient();

    //constructors
    Drinks(){

    }
    Drinks(String name, Ingredient base, Ingredient ice, Ingredient tapioca, Ingredient toppings, Ingredient straw){
        this.base = base;
        this.ice = ice;
        this.tapioca = tapioca;
        this.toppings = toppings;
        this.straw = straw;
    }

    //setters and getters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ingredient getBase() {
        return base;
    }

    public void setBase(Ingredient base) {
        this.base = base;
    }

    public Ingredient getIce() {
        return ice;
    }

    public void setIce(Ingredient ice) {
        this.ice = ice;
    }

    public Ingredient getTapioca() {
        return tapioca;
    }

    public void setTapioca(Ingredient tapioca) {
        this.tapioca = tapioca;
    }

    public Ingredient getToppings() {
        return toppings;
    }

    public void setToppings(Ingredient toppings) {
        this.toppings = toppings;
    }

    public Ingredient getStraw() {
        return straw;
    }

    public void setStraw(Ingredient straw) {
        this.straw = straw;
    }
}
