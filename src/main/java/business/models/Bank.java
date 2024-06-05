package business.models;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    private String address;
    private List<Branch> branches;

    public Bank(String name, String address) {
        this.name = name;
        this.address = address;
        this.branches = new ArrayList<>();
    }

    // Getters and Setters

    public void addBranch(Branch branch) {
        branches.add(branch);
    }

    public void removeBranch(Branch branch) {
        branches.remove(branch);
    }

    // Other methods as needed (e.g., findBranchById)
}