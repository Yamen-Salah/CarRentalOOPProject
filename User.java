class User {
    private int id;
    private String name;
    private String phone;

    User(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    // Getters and setters will come later
    int getId() {
        return id;
    }
    void setId(int id) {
        this.id = id;
    }
    String getName() {
        return name;
    }
    void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }
    String getPhone() {
        return phone;
    }
    void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }
        this.phone = phone;
    }
}
