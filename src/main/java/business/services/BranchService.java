package business.services;

import business.models.Account;
import business.models.Branch;
import business.models.Employee;
import persistence.repository.BranchRepositoryImpl;

public class BranchService {
    private final BranchRepositoryImpl branchRepository;

    public BranchService(BranchRepositoryImpl branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Branch createBranch(String location) {
        Branch branch = new Branch(generateBranchId(), location);
        return branchRepository.save(branch);
    }

    public void addAccount(Branch branch, Account account) {
        branch.addAccount(account);
        branchRepository.update(branch);
    }

    public void removeAccount(Branch branch, Account account) {
        branch.removeAccount(account);
        branchRepository.update(branch);
    }

    public void addEmployee(Branch branch, Employee employee) {
        branch.addEmployee(employee);
        branchRepository.update(branch);
    }

    public void removeEmployee(Branch branch, Employee employee) {
        branch.removeEmployee(employee);
        branchRepository.update(branch);
    }

    // ... other branch-related methods (findById, etc.)

    private int generateBranchId() {
        // Logic to generate a unique branch ID
        return 0; // Placeholder
    }
}