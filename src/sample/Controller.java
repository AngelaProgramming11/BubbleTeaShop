package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.*;
import java.util.ArrayList;

public class Controller {
    public Label selectionTitle;
    public ImageView milkTeaBtn;
    public VBox drinkTypeMenu;
    public ImageView fruitTeaBtn;
    public VBox milkTeaMenu;
    public ImageView basicMilkTeaBtn;
    public ImageView brownSugarMilkTeaBtn;
    public ImageView greenTeaMilkTeaBtn;
    public ImageView passionFruitTeaBtn;
    public VBox fruitTeaMenu;
    public ImageView peachTeaBtn;
    public ImageView strawberryMatchaTeaBtn;
    public VBox iceMenu;
    public ImageView wantsIce;
    public VBox tapiocaMenu;
    public ImageView wantsTapioca;
    public VBox toppingsMenu;
    public ImageView herbalJellyBtn;
    public ImageView redBeanBtn;
    public ImageView fruitsBtn;
    public ImageView milkFoamBtn;
    public ImageView poppingBobaBtn;
    public ImageView pinkStrawBtn;
    public VBox strawMenu;
    public ImageView blueStrawBtn;
    public ImageView greenStrawBtn;
    public ImageView yellowStrawBtn;
    public ArrayList<Drinks> drinks = new ArrayList<>();
    //public Menu menu = new Menu();
    public Menu menuButtons = new Menu();
    public Drinks newDrink = new Drinks();
    public ArrayList<Label> drinkNames = new ArrayList<>();
    public ImageView drinkBase;
    public Button selectDrinkTypeBtn;
    public Button selectMilkTeaBtn;
    public Button selectFruitTeaBtn;
    public Button selectToppingsBtn;
    public Button selectIceBtn;
    public Button selectTapiocaBtn;
    public Button selectStrawBtn;
    public ImageView ice;
    public ImageView tapioca;
    public ImageView topping;
    public ImageView straw;
    public Ingredient selectedIngredient = new Ingredient();
    public ArrayList<Ingredient> currentSelection = new ArrayList<>();
    public Button currentBtn = new Button();
    public int imageWidth = 100, imageHeight = 153, borderWidth = 30, imageTextWidth = 20, textHeight = 17, itemsPerRow = 5;
    public Button startBtn;
    public TextField newDrinkName;
    public VBox nameMenu;
    public Button enterNameBtn;
    public int numOfUntitled = 0;
    public AnchorPane displayDrinksPane;

    //load previously created drinks (image + name) + display it
    //create file if not there
    //arraylist of drinks
    //keep track of imageview location + # of drinks
    public void initialize() throws IOException{
        drinkBase.setVisible(true);
        ice.setVisible(false);
        tapioca.setVisible(false);
        topping.setVisible(false);
        straw.setVisible(false);
        //get menu
        getMenu(menuButtons);
        //get previously made drinks
        getDrinks(drinks);
        //display drinks
    }

    //read from menu
    void getMenu(Menu menuButtons) throws IOException{
        //get path of folder containing images needed for the code to run
        //get menu from file
        //format: 'category name':'item1'.'file name','item2'.'file name',et...
        //in case file doesn't exist

        //add 'button' images to a menu to be crossed referenced if the correct imageviews were pressed
        //add drink types to menu
        Ingredient tempIngredient = new Ingredient("MilkTea", milkTeaBtn, true);
        menuButtons.getDrinkBaseTypes().add(tempIngredient);
        tempIngredient = new Ingredient("FruitTea", fruitTeaBtn, true);
        menuButtons.getDrinkBaseTypes().add(tempIngredient);

        //add milk tea bases
        tempIngredient = new Ingredient("BasicMilkTea", basicMilkTeaBtn, true);
        menuButtons.getDrinkBaseList().add(tempIngredient);
        tempIngredient = new Ingredient("BrownSugarMilkTea", brownSugarMilkTeaBtn, true);
        menuButtons.getDrinkBaseList().add(tempIngredient);
        tempIngredient = new Ingredient("GreenTeaMilkTea", greenTeaMilkTeaBtn, true);
        menuButtons.getDrinkBaseList().add(tempIngredient);

        //add fruit tea bases
        tempIngredient = new Ingredient("PassionFruitTea", passionFruitTeaBtn, true);
        menuButtons.getDrinkBaseList().add(tempIngredient);
        tempIngredient = new Ingredient("PeachTea", peachTeaBtn, true);
        menuButtons.getDrinkBaseList().add(tempIngredient);
        tempIngredient = new Ingredient("StrawberryMatchaTea", strawberryMatchaTeaBtn, true);
        menuButtons.getDrinkBaseList().add(tempIngredient);

        //add ice
        tempIngredient = new Ingredient("Ice", wantsIce, true);
        menuButtons.getIceList().add(tempIngredient);

        //add tapioca
        tempIngredient = new Ingredient("Tapioca", wantsTapioca, true);
        menuButtons.getTapiocaList().add(tempIngredient);

        //add toppings
        tempIngredient = new Ingredient("HerbalJelly", herbalJellyBtn, true);
        menuButtons.getToppingsList().add(tempIngredient);
        tempIngredient = new Ingredient("RedBean", redBeanBtn, true);
        menuButtons.getToppingsList().add(tempIngredient);
        tempIngredient = new Ingredient("Fruits", fruitsBtn, true);
        menuButtons.getToppingsList().add(tempIngredient);
        tempIngredient = new Ingredient("PoppingBoba", poppingBobaBtn, true);
        menuButtons.getToppingsList().add(tempIngredient);
        tempIngredient = new Ingredient("MilkFoam", milkFoamBtn, true);
        menuButtons.getToppingsList().add(tempIngredient);

        //add straws
        tempIngredient = new Ingredient("PinkStraw", pinkStrawBtn, true);
        menuButtons.getStrawList().add(tempIngredient);
        tempIngredient = new Ingredient("BlueStraw", blueStrawBtn, true);
        menuButtons.getStrawList().add(tempIngredient);
        tempIngredient = new Ingredient("GreenStraw", greenStrawBtn, true);
        menuButtons.getStrawList().add(tempIngredient);
        tempIngredient = new Ingredient("YellowStraw", yellowStrawBtn, true);
        menuButtons.getStrawList().add(tempIngredient);
    }

    //get previously made drinks
    void getDrinks(ArrayList<Drinks> drinks) throws IOException{
        //in case file doesn't exist
        FileWriter fw = new FileWriter("CreatedDrinks.txt", true);
        fw.close();
        //get previously made drinks from file
        //info format: name:'base','ice','tapioca','toppings','straw'  |||  each line is a new drinks
        FileReader fr = new FileReader("CreatedDrinks.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            //separate drink name from drink components
            String array[] = line.split(":");
            Drinks tempDrink = new Drinks();
            tempDrink.setName(array[0]);
            //count how many drinks are untitled so untitled drinks can be assigned a number, ex: untitled1, untitled2, etc...
            if(array[0].startsWith("untitled")){
                numOfUntitled++;
            }
            //separate drink components
            String drinkComponent[] = array[1].split(",");
            //find image of each component
            //drink base
            for(int i = 0; i < menuButtons.getDrinkBaseList().size(); i++) {
                if(menuButtons.getDrinkBaseList().get(i).getName().equals(drinkComponent[0])) {
                    tempDrink.getBase().setName(drinkComponent[0]);
                    String location = "Images/" + drinkComponent[0] + ".PNG";
                    tempDrink.getBase().setIngredientImage(new ImageView(new Image(this.getClass().getResourceAsStream(location))));
                    break;
                }
            }
            //ice
            if(drinkComponent[1].equals("no ice")){
                tempDrink.getIce().setName("no ice");
            }
            else {
                String location = "Images/" + tempDrink.getBase().getName() + "Ice.PNG";
                tempDrink.getIce().setName(tempDrink.getBase().getName() + "Ice");
                tempDrink.getIce().setIngredientImage(new ImageView(new Image(this.getClass().getResourceAsStream(location))));
            }
            //tapioca
            if(drinkComponent[2].equals("no tapioca")){
                tempDrink.getTapioca().setName("no tapioca");
            }
            else {
                String location = "Images/" + tempDrink.getBase().getName() + "Ice.PNG";
                tempDrink.getTapioca().setName(tempDrink.getBase().getName() + "Ice");
                tempDrink.getTapioca().setIngredientImage(new ImageView(new Image(this.getClass().getResourceAsStream(location))));
            }
            //toppings
            for(int i = 0; i < menuButtons.getToppingsList().size(); i++) {
                if(menuButtons.getToppingsList().get(i).getName().equals(drinkComponent[3])) {
                    tempDrink.getToppings().setName(drinkComponent[3]);
                    String location = "Images/" + drinkComponent[3] + ".PNG";
                    tempDrink.getToppings().setIngredientImage(new ImageView(new Image(this.getClass().getResourceAsStream(location))));
                    break;
                }
            }
            //straws
            for(int i = 0; i < menuButtons.getStrawList().size(); i++) {
                if(menuButtons.getStrawList().get(i).getName().equals(drinkComponent[4])) {
                    tempDrink.getStraw().setName(drinkComponent[4]);
                    String location = "Images/" + drinkComponent[4] + ".PNG";
                    tempDrink.getStraw().setIngredientImage(new ImageView(new Image(this.getClass().getResourceAsStream(location))));
                    break;
                }
            }
            drinks.add(tempDrink);
            //display drink
            int numOfItems = drinks.size();
            displayDrinks(imageWidth, imageHeight, borderWidth, imageTextWidth, textHeight, itemsPerRow, numOfItems, tempDrink);
        }
        br.close();
    }

    //start
    public void start(MouseEvent mouseEvent) throws IOException{
        //reset variables
        //reset cup
        String relativeLocation = "Images/EmptyCup.PNG";
        Image tempImage = new Image(this.getClass().getResourceAsStream(relativeLocation));
        drinkBase.setImage(tempImage);
        ice.setVisible(false);
        tapioca.setVisible(false);
        topping.setVisible(false);
        straw.setVisible(false);
        newDrink = new Drinks();
        selectionTitle.setVisible(false);

        startBtn.setVisible(false);
        drinkTypeMenu.setVisible(true);
        selectedIngredient = null;
        currentBtn = selectDrinkTypeBtn;
        currentSelection = menuButtons.getDrinkBaseTypes();
    }

    //display drinks
    void displayDrinks(int imageWidth, int imageHeight, int borderWidth, int imageTextWidth, int textHeight, int itemPerRow, int numOfItems, Drinks drink){
        //text width = image width, border width = border height
        //find which row new item should be on
        int row = numOfItems/(itemPerRow + 1);
        //find which column
        int column = numOfItems%itemsPerRow - 1;
        double coordinates[] = new double[2];
        coordinates[0] = borderWidth + column*(imageWidth + borderWidth);
        coordinates[1] = borderWidth + row*(imageHeight + imageTextWidth + textHeight + borderWidth);
        //insert images
        drink.getBase().getIngredientImage().setFitWidth(imageWidth);
        drink.getBase().getIngredientImage().setPreserveRatio(true);
        drink.getBase().getIngredientImage().setLayoutX(coordinates[0]);
        drink.getBase().getIngredientImage().setLayoutY(coordinates[1]);

        //cup outline
        String location = "Images/CupOutline.PNG";
        ImageView tempImageView = new ImageView(new Image(this.getClass().getResourceAsStream(location)));
        tempImageView.setFitWidth(imageWidth);
        tempImageView.setPreserveRatio(true);
        tempImageView.setLayoutX(coordinates[0]);
        tempImageView.setLayoutY(coordinates[1]);

        drink.getIce().getIngredientImage().setFitWidth(imageWidth);
        drink.getIce().getIngredientImage().setPreserveRatio(true);
        drink.getIce().getIngredientImage().setLayoutX(coordinates[0]);
        drink.getIce().getIngredientImage().setLayoutY(coordinates[1]);

        drink.getTapioca().getIngredientImage().setFitWidth(imageWidth);
        drink.getTapioca().getIngredientImage().setPreserveRatio(true);
        drink.getTapioca().getIngredientImage().setLayoutX(coordinates[0]);
        drink.getTapioca().getIngredientImage().setLayoutY(coordinates[1]);

        drink.getToppings().getIngredientImage().setFitWidth(imageWidth);
        drink.getToppings().getIngredientImage().setPreserveRatio(true);
        drink.getToppings().getIngredientImage().setLayoutX(coordinates[0]);
        drink.getToppings().getIngredientImage().setLayoutY(coordinates[1]);

        drink.getStraw().getIngredientImage().setFitWidth(imageWidth);
        drink.getStraw().getIngredientImage().setPreserveRatio(true);
        drink.getStraw().getIngredientImage().setLayoutX(coordinates[0]);
        drink.getStraw().getIngredientImage().setLayoutY(coordinates[1]);

        //create text boxes, insert name
        Label drinkName = new Label();
        drinkName.setLayoutX(coordinates[0]);
        drinkName.setLayoutY((coordinates[1] + imageHeight + imageTextWidth));
        drinkName.setPrefWidth(imageWidth);
        drinkName.setPrefHeight(textHeight);
        drinkName.setTextAlignment(TextAlignment.CENTER);
        drinkName.setText(drink.getName());

        displayDrinksPane.getChildren().addAll(drink.getBase().getIngredientImage(), tempImageView, drink.getIce().getIngredientImage(), drink.getTapioca().getIngredientImage(), drink.getToppings().getIngredientImage(), drink.getStraw().getIngredientImage());
        /*
        drinkNames.add(drinkName);
        drinkNames.get(drinkNames.lastIndexOf(drinkName)).setVisible(true);

         */
    }
    //select drink type
    //shows progress (picking drink base, adding ice, etc...)
    //change selection info
    //make visible milk/fruit tea menu
    //enable/disable next button
    public void selectDrinkType(MouseEvent mouseEvent) {
        //if milk tea btn is clicked
        if(selectedIngredient.getIngredientImage().equals(milkTeaBtn)){
            //make visible milk tea menu
            milkTeaMenu.setVisible(true);
            drinkTypeMenu.setVisible(false);
            //change label
            selectionTitle.setText("Choose a milk tea:");
            selectionTitle.setVisible(true);
            currentSelection = menuButtons.getDrinkBaseList();
            currentBtn = selectMilkTeaBtn;
            selectDrinkTypeBtn.setDisable(true);
            //clear selected ingredient in case it is accidently accessed
            selectedIngredient = null;
        }
        //of fruit tea btn is clicked
        else if(selectedIngredient.getIngredientImage().equals(fruitTeaBtn)){
            //make visible fruit tea menu
            fruitTeaMenu.setVisible(true);
            drinkTypeMenu.setVisible(false);
            selectionTitle.setText("Choose a fruit tea:");
            selectionTitle.setVisible(true);
            currentSelection = menuButtons.getDrinkBaseList();
            currentBtn = selectFruitTeaBtn;
            //clear selected ingredient in case it is accidently accessed
            selectedIngredient = null;
        }
    }

    //select base
    //add to cup
    //make visible ice menu
    public void selectDrinkBase(MouseEvent mouseEvent) {
        //add to cup
        String imageLocation = "Images/" + selectedIngredient.getName() + ".PNG";
        drinkBase.setImage(new Image(this.getClass().getResourceAsStream(imageLocation)));
        newDrink.getBase().setName(selectedIngredient.getName());
        newDrink.getBase().setIngredientImage(new ImageView(new Image(this.getClass().getResourceAsStream(imageLocation))));
        milkTeaMenu.setVisible(false);
        fruitTeaMenu.setVisible(false);
        iceMenu.setVisible(true);
        selectionTitle.setText("Choose to add ice:");
        currentSelection = menuButtons.getIceList();
        currentBtn = selectIceBtn;
        milkTeaBtn.setDisable(true);
        selectFruitTeaBtn.setDisable(true);
        selectIceBtn.setDisable(false);
        //clear selected ingredient in case it is accidently accessed
        selectedIngredient = null;
    }

    //select ice
    //make visible tapioca menu
    public void selectIce(MouseEvent mouseEvent) throws IOException{
        //if ice was selected
        if(selectedIngredient != null) {
            //find specific ice (image of ice for each drink base is different b/c of color)
            String relativeLocation = "Images/" + newDrink.getBase().getName() + "Ice.PNG";
            Image tempImage = new Image(this.getClass().getResourceAsStream(relativeLocation));
            newDrink.getIce().setName(selectedIngredient.getName());
            newDrink.getIce().getIngredientImage().setImage(tempImage);
            ice.setImage(tempImage);
            ice.setVisible(true);
        }
        //no ice
        else{
            newDrink.getIce().setName("no ice");
        }
        iceMenu.setVisible(false);
        tapiocaMenu.setVisible(true);
        selectionTitle.setText("Choose to add tapioca:");
        currentSelection = menuButtons.getTapiocaList();
        currentBtn = selectTapiocaBtn;
        selectIceBtn.setDisable(true);
        selectTapiocaBtn.setDisable(false);
        selectedIngredient = null;
    }

    //select tapioca
    //make visible toppings menu
    public void selectTapioca(MouseEvent mouseEvent){
        //if tapioca was selected
        if(selectedIngredient != null) {
            //find specific ice (image of ice for each drink base is different b/c of color)
            String imageLocation = "Images/" + newDrink.getBase().getName() + "Tapioca.PNG";
            Image tempImage = new Image(this.getClass().getResourceAsStream(imageLocation));
            newDrink.getTapioca().setName(selectedIngredient.getName());
            newDrink.getTapioca().getIngredientImage().setImage(tempImage);
            tapioca.setImage(tempImage);
            tapioca.setVisible(true);
        }
        else{
            newDrink.getTapioca().setName("no tapioca");
        }
        tapiocaMenu.setVisible(false);
        toppingsMenu.setVisible(true);
        selectionTitle.setText("Choose a topping:");
        currentSelection = menuButtons.getToppingsList();
        currentBtn = selectToppingsBtn;
        selectTapiocaBtn.setDisable(true);
        selectedIngredient = null;
    }

    //select topping
    //make visible straws menu
    public void selectToppings(MouseEvent mouseEvent) {
        //add to cup
        String imageLocation = "Images/" + selectedIngredient.getName() + ".PNG";
        topping.setImage(new Image(this.getClass().getResourceAsStream(imageLocation)));
        topping.setVisible(true);
        newDrink.getToppings().setName(selectedIngredient.getName());
        newDrink.getToppings().setIngredientImage(selectedIngredient.getIngredientImage());
        toppingsMenu.setVisible(false);
        strawMenu.setVisible(true);
        selectionTitle.setText("Choose a straw:");
        currentSelection = menuButtons.getStrawList();
        currentBtn = selectStrawBtn;
        selectToppingsBtn.setDisable(true);
        //clear selected ingredient in case it is accidently accessed
        selectedIngredient = null;
    }

    //select straw
    //add to arraylist
    //add new drink to display drinks
    //add to file
    //reset drink variable
    public void selectStraw(MouseEvent mouseEvent){
        //add to cup
        String imageLocation = "Images/" + selectedIngredient.getName() + ".PNG";
        straw.setImage(new Image(this.getClass().getResourceAsStream(imageLocation)));
        straw.setVisible(true);
        newDrink.getStraw().setName(selectedIngredient.getName());
        newDrink.getStraw().setIngredientImage(selectedIngredient.getIngredientImage());
        strawMenu.setVisible(false);
        nameMenu.setVisible(true);
        selectionTitle.setText("Give your drink a name:");
        selectStrawBtn.setDisable(true);
        //clear selected ingredient in case it is accidently accessed
        selectedIngredient = null;
    }

    public void finishDrink(MouseEvent mouseEvent) throws IOException{
        if(newDrinkName.getText().equals("") || newDrinkName.getText().startsWith("untitled")){
            newDrink.setName("untitled" + numOfUntitled);
        }
        else{
            newDrink.setName(newDrinkName.getText());
        }
        //reset game
        nameMenu.setVisible(false);
        startBtn.setVisible(true);
        //add drink to list
        drinks.add(newDrink);
        //add drink to display
        int numOfItems = drinks.size();
        displayDrinks(imageWidth, imageHeight, borderWidth, imageTextWidth, textHeight, itemsPerRow, numOfItems, newDrink);
        //add to file
        addToFile(newDrink);
    }

    //record what ingredient was chosen in case any changes are made before pressing 'next'
    public void selectIngredient(MouseEvent mouseEvent) {
        //if whatever is clicked is an imageview than get which imageview was clicked
        if(mouseEvent.getSource() instanceof ImageView && ((ImageView) mouseEvent.getSource()).getImage() != null){
            //check if the image is part of the menu
            for(int i = 0; i < currentSelection.size(); i++){
                if(currentSelection.get(i).getIngredientImage().equals(mouseEvent.getSource())){
                    selectedIngredient = new Ingredient();
                    selectedIngredient.pointToImageView(currentSelection.get(i).getIngredientImage());
                    selectedIngredient.setName(currentSelection.get(i).getName());
                    currentBtn.setDisable(false);
                    break;
                }
            }
        }
    }

    public void unselectIngredient(MouseEvent mouseEvent) {
        selectedIngredient = null;
    }

    public void addToFile(Drinks drink) throws IOException{
        FileWriter fw = new FileWriter("CreatedDrinks.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(drink.getName() + ":" + drink.getBase().getName() + "," + drink.getIce().getName() + "," + drink.getTapioca().getName() + "," + drink.getToppings().getName() + "," + drink.getStraw().getName() + "\n");
        bw.close();
    }

    /*reminders:
    - method to print name of drink
    - add label for name of drink when drink is finished
     */
}
