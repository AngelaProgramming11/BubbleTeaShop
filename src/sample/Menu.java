package sample;

import java.util.ArrayList;

public class Menu {
    //stores all ingredient images for reference
    private ArrayList<Ingredient> drinkBaseTypes = new ArrayList<>();
    private ArrayList<Ingredient> drinkBaseList = new ArrayList<>();
    private ArrayList<Ingredient> iceList = new ArrayList<>();
    private ArrayList<Ingredient> tapiocaList = new ArrayList<>();
    private ArrayList<Ingredient> toppingsList = new ArrayList<>();
    private ArrayList<Ingredient> strawList = new ArrayList<>();

    //initialize object fields
    Menu(){

    }

    public ArrayList<Ingredient> getDrinkBaseTypes() {
        return drinkBaseTypes;
    }

    public void setDrinkBaseTypes(ArrayList<Ingredient> drinkBaseTypes) {
        this.drinkBaseTypes = drinkBaseTypes;
    }

    //setters + getters
    public ArrayList<Ingredient> getDrinkBaseList() {
        return drinkBaseList;
    }

    public void setDrinkBaseList(ArrayList<Ingredient> drinkBaseList) {
        this.drinkBaseList = drinkBaseList;
    }

    public ArrayList<Ingredient> getIceList() {
        return iceList;
    }

    public void setIceList(ArrayList<Ingredient> iceList) {
        this.iceList = iceList;
    }

    public ArrayList<Ingredient> getTapiocaList() {
        return tapiocaList;
    }

    public void setTapiocaList(ArrayList<Ingredient> tapiocaList) {
        this.tapiocaList = tapiocaList;
    }

    public ArrayList<Ingredient> getToppingsList() {
        return toppingsList;
    }

    public void setToppingsList(ArrayList<Ingredient> toppingsList) {
        this.toppingsList = toppingsList;
    }

    public ArrayList<Ingredient> getStrawList() {
        return strawList;
    }

    public void setStrawList(ArrayList<Ingredient> strawList) {
        this.strawList = strawList;
    }
}
