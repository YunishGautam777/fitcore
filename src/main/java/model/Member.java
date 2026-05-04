package model;

/**
 * Represents a gym member with personal details and membership plan.
 * This class encapsulates the data for a member in the gym management system.
 */
public class Member {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String plan;

    /**
     * Default constructor for Member.
     */
    public Member() {}

    /**
     * Constructor for Member with all fields.
     * @param id The unique identifier for the member
     * @param name The name of the member
     * @param email The email address of the member
     * @param phone The phone number of the member
     * @param plan The membership plan of the member
     */
    public Member(int id, String name, String email, String phone, String plan) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.plan = plan;
    }

    /**
     * Gets the member's ID.
     * @return the member's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the member's ID.
     * @param id the member's ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the member's name.
     * @return the member's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the member's name.
     * @param name the member's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the member's email.
     * @return the member's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the member's email.
     * @param email the member's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the member's phone number.
     * @return the member's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the member's phone number.
     * @param phone the member's phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the member's plan.
     * @return the member's plan
     */
    public String getPlan() {
        return plan;
    }

    /**
     * Sets the member's plan.
     * @param plan the member's plan
     */
    public void setPlan(String plan) {
        this.plan = plan;
    }
}
