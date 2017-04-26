package FxHomeWork2.Controller;

/*в MVC-концепции это наша модель(Model)
Про инкапсуляцию не забыл, просто у нас всё package-private, поэтому без модификатора доступа, если что - допилю*/

public class InitialParameters {

    private double coordinateX;
    private double coordinateY;
    private double velocityX;
    private double velocityY;
    private double frequency;

    public InitialParameters(String coordinateX, String coordinateY, String velocityX, String velocityY, String frequency) {
        if (Validate.isValid(frequency) && Validate.isMoreZero(frequency) && !Validate.islessZero(coordinateY) && Validate.isValid(coordinateX) && Validate.isValid(coordinateY) && Validate.isValid(velocityX)
                && Validate.isValid(velocityY)) {
            this.coordinateX = Double.parseDouble(coordinateX);
            this.coordinateY = Double.parseDouble(coordinateY);
            this.velocityX = Double.parseDouble(velocityX);
            this.velocityY = Double.parseDouble(velocityY);
            this.frequency = Double.parseDouble(frequency);
        }else if(!Validate.isMoreZero(frequency)){
            throw new IllegalArgumentException("DotsInSecond must be more than zero");
        } else if(Validate.islessZero(coordinateY)){
            throw new IllegalArgumentException("CoordinateY less than zero");
        } else if(coordinateX.isEmpty() || coordinateY.isEmpty() || velocityX.isEmpty() || velocityY.isEmpty()){
            throw new IllegalArgumentException("Empty Argument");
        }else{
            throw new IllegalArgumentException("Letter in the field");
        }
    }
    double getFrequency() {
        return frequency;
    }
    double getCoordinateX() {
        return coordinateX;
    }

    double getCooridnateY() {
        return coordinateY;
    }

    double getVelocityX() {
        return velocityX;
    }

    double getVelocityY() {
        return velocityY;
    }

}
