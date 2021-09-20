
package eczaneotomasyon.model;


public class BaseEntity {
    private int id; //encapsulation

    //getter
    public int getId() { return id; }
    //setter
    public void setId(int id) { this.id = id; }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseEntity other = (BaseEntity) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
