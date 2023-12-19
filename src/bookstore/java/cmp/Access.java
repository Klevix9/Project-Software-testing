
package bookstore.java.cmp;

public enum Access {

    ADMINISTRATOR("admin"),
    MANAGER("manager"),
    LIBRARIAN("librarian");

    private final String access;

    private Access(String level) {
        this.access = level;
    }

    @Override
    public String toString() {
        return access;
    }

}
