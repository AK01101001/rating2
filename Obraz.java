package dev.isnow.obrazy;

import java.util.ArrayList;

public class Obraz {
    private int source;
    private boolean isAI;
    private ArrayList<Float> ratings;
    private String desc;

    public Obraz(int source, String desc) {
        this.source = source;
        this.isAI = false;
        this.ratings = new ArrayList<>();
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void AddRating(float rate) {
        this.ratings.add(rate);
    }
    public String getAverge()
    {
        if (ratings.size()==0)
        {
            return "brak ocen";
        }
        float suma =0;
        for (float rating :
                ratings) {
            suma += rating;
        }
        return String.valueOf (suma/ratings.size());
    }

    public boolean isAI() {
        return isAI;
    }

    public void setAI(boolean AI) {
        isAI = AI;
    }

    public int getSource() {
        return source;
    }
}
