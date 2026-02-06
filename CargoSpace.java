public class CargoSpace {
    private final boolean tiltable; //if the cargo space door is tiltable
    private final boolean liftable; //if the cargo space has a lowerable platform
    private double truckBedAngle = 0; //angle at which the door might be
    private boolean platformLowered = false; //platform position, false is up, true if down
    private double maxAngle; //maximum angle of a tiltable door
    //kan utökas med mer funktionalitet kring faktisk lastning, typ kapacitet

    //constructor for cargo spaces with a tiltable door, can also be liftable
    public CargoSpace(boolean tiltable, boolean liftable, double maxAngle) {
        this.tiltable = tiltable;
        this.liftable = liftable;
        this.maxAngle = maxAngle;
    }

    //constructor for cargo spaces with only a liftable platform and no tilting abilities
    public CargoSpace(boolean tiltable, boolean liftable) {
        if (!tiltable) {
            throw new IllegalArgumentException("must enter maximum angle if tiltable");
        }
        this.tiltable = true;
        this.liftable = liftable;
    }

    /**
     * Checks if the vehicle can be moved
     * @return true or false
     */
    public boolean movingOK() {
        boolean tiltOK = !tiltable || truckBedAngle == maxAngle;
        boolean liftOK = !liftable || !platformLowered;
        return tiltOK && liftOK;
    }

    /**
     * Sets the angle at which the truck bed door is tilted
     * @param angle the angle
     * @throws IllegalArgumentException if angle is out of the set bound
     */
    public void setTruckBedAngle(double angle) {
        if (angle < 0 || angle > maxAngle) {
            throw new IllegalArgumentException("illegal angle");
        }
        this.truckBedAngle = angle;
    }

    /**
     * Sets platform height
     * @param lowered true if platform is lowered
     * @throws IllegalArgumentException if the cargo space has no liftable platform
     */
    public void setPlatformHeight(boolean lowered) {
        if (!liftable) { throw new IllegalArgumentException("this has no liftable platform!"); }
        this.platformLowered = lowered;
    }
}