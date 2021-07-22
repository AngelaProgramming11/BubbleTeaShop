package sample;
import javafx.scene.image.ImageView;

public class Ingredient {
    //stores name and image of drink base
    private String name;
    private ImageView ingredientImage = new ImageView();

    //constructor
    Ingredient(){
        this.name = "Ingredient Name";
    }

    Ingredient(String name, ImageView ingredientImage){
        this.name = name;
        this.ingredientImage.setImage(ingredientImage.getImage());
    }

    Ingredient(String name, ImageView ingredientImage, boolean pointAtImageView){
        this.name = name;
        this.ingredientImage = ingredientImage;
    }

    //setters + getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageView getIngredientImage() {
        return ingredientImage;
    }

    public void setIngredientImage(ImageView ingredientImage) {
        this.ingredientImage.setImage(ingredientImage.getImage());
    }

    public void pointToImageView(ImageView ingredientImage){
        this.ingredientImage = ingredientImage;
    }
}
