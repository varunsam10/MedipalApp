package iss.medipal.model;

import java.util.Date;

/**
 * Created by sreekumar on 3/19/2017.
 */

public class Temperature extends Measurement{

    private int temperature;

    public Temperature(int id,int imageId,int temperature,Date measuredOn){

        super(id,imageId,measuredOn);
        this.temperature=temperature;

    }
    public Temperature(){

    }
    public Temperature(int id,int temperature,Date measuredOn){

        super(id,measuredOn);
        this.temperature=temperature;

    }

    public Temperature(int temperature,Date measuredOn){
        super(measuredOn);
       this.temperature=temperature;

    }
    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

}
