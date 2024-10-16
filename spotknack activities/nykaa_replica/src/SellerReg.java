import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Seller {
    private String sId;
    private String username;
    private String companyname;
    private String email;
    private String password;
    private String contactNumber;
    private String address;

    public Seller(String sId,String username,String companyname, String email, String password, String contactNumber,String address) {
        this.sId = sId;
        this.username = username;
        this.companyname= companyname;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        this.address= address;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

class SellerService {
    private List<Seller> sellers = new ArrayList<>();

    public void addSeller(Seller seller) {
        sellers.add(seller);
        System.out.println("Seller added: " + seller);
    }

    public List<Seller> getAllSellers() {
        return sellers;
    }

    public Seller getSellerById(String sId) {
        for (Seller seller : sellers) {
            if (seller.getsId().equals(sId)) {
                return seller;
            }
        }
        return null;
    }

    public boolean updateSeller(String sId,String username,String companyname, String email, String password, String contactNumber,String address) {
        Seller seller = getSellerById(sId);
        if (seller != null) {
            seller.setUsername(username);
            seller.setCompanyname(companyname);
            seller.setEmail(email);
            seller.setPassword(password);
            seller.setContactNumber(contactNumber);
            seller.setAddress(address);
            System.out.println("Seller updated: " + seller);
            return true;
        }
        return false;
    }

    public boolean removeSeller(String sId) {
        Seller seller = getSellerById(sId);
        if (seller != null) {
            sellers.remove(seller);
            System.out.println("Seller removed: " + seller);
            return true;
        }
        return false;
    }
}
public class SellerReg {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SellerService sellerService = new SellerService();

        while (true) {
            System.out.println("\n---- Seller Management ----");
            System.out.println("1. Add Seller");
            System.out.println("2. View All Sellers");
            System.out.println("3. View Seller by ID");
            System.out.println("4. Update Seller");
            System.out.println("5. Remove Seller");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Seller ID: ");
                    String sId = scanner.nextLine();
                    System.out.print("Enter Seller Name: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter Company Name: ");
                    String companyname = scanner.nextLine();
                    System.out.print("Enter Seller Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter Contact Number: ");
                    String contactNumber = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    sellerService.addSeller(new Seller(sId, username, companyname, email, password, contactNumber, address));
                    break;

                case 2:
                    System.out.println("All Sellers:");
                    for (Seller seller : sellerService.getAllSellers()) {
                        System.out.println(seller);
                    }
                    break;

                case 3:
                    System.out.print("Enter Seller ID: ");
                    sId = scanner.nextLine();
                    Seller seller = sellerService.getSellerById(sId);
                    if (seller != null) {
                        System.out.println(seller);
                    } else {
                        System.out.println("Seller not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Seller ID: ");
                    sId = scanner.nextLine();
                    System.out.print("Enter new Name: ");
                    username = scanner.nextLine();
                    System.out.print("Enter new Company Name: ");
                    companyname = scanner.nextLine();
                    System.out.print("Enter new Email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter new Password: ");
                    password = scanner.nextLine();
                    System.out.print("Enter new Contact Number: ");
                    contactNumber = scanner.nextLine();
                    System.out.print("Enter new Address: ");
                    address = scanner.nextLine();
                    if (sellerService.updateSeller(sId, username, companyname, email, password, contactNumber, address)) {
                        System.out.println("Seller updated successfully.");
                    } else {
                        System.out.println("Seller not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter Seller ID to remove: ");
                    sId = scanner.nextLine();
                    if (sellerService.removeSeller(sId)) {
                        System.out.println("Seller removed successfully.");
                    } else {
                        System.out.println("Seller not found.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}