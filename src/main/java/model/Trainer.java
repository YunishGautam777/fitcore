package model;

/**
 * Represents a gym trainer with personal details and specialization.
 * This class encapsulates the data for a trainer in the gym management system.
 */
public class Trainer {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String specialization;

    /**
     * Default constructor for Trainer.
     */
    public Trainer() {}

    /**
     * Constructor for Trainer with all fields.
     * @param id The unique identifier for the trainer
     * @param name The name of the trainer
     * @param email The email address of the trainer
     * @param phone The phone number of the trainer
     * @param specialization The specialization of the trainer
     */
    public Trainer(int id, String name, String email, String phone, String specialization) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.specialization = specialization;
    }

    /**
     * Gets the trainer's ID.
     * @return the trainer's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the trainer's ID.
     * @param id the trainer's ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the trainer's name.
     * @return the trainer's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the trainer's name.
     * @param name the trainer's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the trainer's email.
     * @return the trainer's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the trainer's email.
     * @param email the trainer's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the trainer's phone number.
     * @return the trainer's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the trainer's phone number.
     * @param phone the trainer's phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the trainer's specialization.
     * @return the trainer's specialization
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Sets the trainer's specialization.
     * @param specialization the trainer's specialization
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
