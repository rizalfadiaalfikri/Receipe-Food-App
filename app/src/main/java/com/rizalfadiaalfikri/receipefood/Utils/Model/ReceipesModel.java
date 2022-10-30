package com.rizalfadiaalfikri.receipefood.Utils.Model;

public class ReceipesModel {

    public ReceipesModel() {
    }

    private String users_id;
    private String receipes_id;
    private String receipes_name;
    private String receipes_images;
    private String receipes_ingredients;
    private String receipes_steps;

    public ReceipesModel(String users_id, String receipes_id, String receipes_name, String receipes_images, String receipes_ingredients, String receipes_steps) {
        this.users_id = users_id;
        this.receipes_id = receipes_id;
        this.receipes_name = receipes_name;
        this.receipes_images = receipes_images;
        this.receipes_ingredients = receipes_ingredients;
        this.receipes_steps = receipes_steps;
    }

    public String getUsers_id() {
        return users_id;
    }

    public void setUsers_id(String users_id) {
        this.users_id = users_id;
    }

    public String getReceipes_id() {
        return receipes_id;
    }

    public void setReceipes_id(String receipes_id) {
        this.receipes_id = receipes_id;
    }

    public String getReceipes_name() {
        return receipes_name;
    }

    public void setReceipes_name(String receipes_name) {
        this.receipes_name = receipes_name;
    }

    public String getReceipes_images() {
        return receipes_images;
    }

    public void setReceipes_images(String receipes_images) {
        this.receipes_images = receipes_images;
    }

    public String getReceipes_ingredients() {
        return receipes_ingredients;
    }

    public void setReceipes_ingredients(String receipes_ingredients) {
        this.receipes_ingredients = receipes_ingredients;
    }

    public String getReceipes_steps() {
        return receipes_steps;
    }

    public void setReceipes_steps(String receipes_steps) {
        this.receipes_steps = receipes_steps;
    }
}
