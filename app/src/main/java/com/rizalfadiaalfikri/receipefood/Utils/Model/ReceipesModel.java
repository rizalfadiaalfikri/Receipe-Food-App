package com.rizalfadiaalfikri.receipefood.Utils.Model;

public class ReceipesModel {

    public ReceipesModel() {
    }

    private String receipe_id;
    private String users_id;
    private String receipe_name;
    private String receipe_images;
    private String receipe_ingredients;
    private String receipe_steps;

    public ReceipesModel(String receipe_id, String users_id, String receipe_name, String receipe_images, String receipe_ingredients, String receipe_steps) {
        this.receipe_id = receipe_id;
        this.users_id = users_id;
        this.receipe_name = receipe_name;
        this.receipe_images = receipe_images;
        this.receipe_ingredients = receipe_ingredients;
        this.receipe_steps = receipe_steps;
    }

    public String getReceipe_id() {
        return receipe_id;
    }

    public void setReceipe_id(String receipe_id) {
        this.receipe_id = receipe_id;
    }

    public String getUsers_id() {
        return users_id;
    }

    public void setUsers_id(String users_id) {
        this.users_id = users_id;
    }

    public String getReceipe_name() {
        return receipe_name;
    }

    public void setReceipe_name(String receipe_name) {
        this.receipe_name = receipe_name;
    }

    public String getReceipe_images() {
        return receipe_images;
    }

    public void setReceipe_images(String receipe_images) {
        this.receipe_images = receipe_images;
    }

    public String getReceipe_ingredients() {
        return receipe_ingredients;
    }

    public void setReceipe_ingredients(String receipe_ingredients) {
        this.receipe_ingredients = receipe_ingredients;
    }

    public String getReceipe_steps() {
        return receipe_steps;
    }

    public void setReceipe_steps(String receipe_steps) {
        this.receipe_steps = receipe_steps;
    }
}
