package co.weirddoeats.attributes;

import co.weirddoeats.gfx.simplegfx.GameGrid;
import co.weirddoeats.gfx.simplegfx.SimpleGfxGridPosition;

public class FoodFactory {

    private static final String [] name = {"sandwiches", "prawns à guilho", "steak, rice, chips and egg", "sushi", "chef dish", "astronaut food"};
    private static final String [] pictures = {"doughnut.png", "gambas.png", "pizza.png", "sushi.png", "author.png", "astronautfood.png"};

    public static Food generateFood(int level, Building building, GameGrid grid){

        SimpleGfxGridPosition foodPos = building.getPosition();
        //int col = (foodPos.getCol() >= (grid.getCols() / 2) ? foodPos.getCol() - 1 : foodPos.getCol() + 1);
        foodPos.setPos(foodPos.getCol() + 1, foodPos.getRow());

        Food food = new Food(name[level], pictures[level], foodPos, grid);

        return food;
    }


}
