import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Artist {

    private String ID;
    private String Name;
    private String Address;
    private String Birthdate;
    private String Bio;
    private ArrayList<String> Occupations; // like singer, songwriter, composer
    private ArrayList<String> Genres;// like rock, jazz, blues, pop, classical
    private ArrayList<String> Awards;

    public static void main(String[] args) {
        
    }

    

    public boolean addArtist() {
        /* TODO: Add the information of a new artist to a TXT file */
        // If the artist information meets the defined conditions
        // the information should be added to the TXT file and the function should
        // return true.
        // If the artist information does not meet the conditions,
        // the information should not be added to the TXT file and the function should
        // return false.
        if (!validate()) {
            return false;
        }
        appendUsingBufferedWriter("artists.txt", this.toString(), 1);
        return true;
    }

    public boolean updateArtist(Artist artist) {
        // Read the file and store artists in a list
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("artists.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error: An IO exception occurred while reading the file.");
            e.printStackTrace();
            return false;
        }

        // Update artist's information
        boolean artistFound = false;
        String [] artist_data;
        int index;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith(artist.getID())) {
                artist_data = line.split(", ");
                index = i;
                // System.out.println(line);
                // lines.set(i, this.getID() +", " +newInfo);
                artistFound = true;                

                if (!this.validate()) {
                    System.out.println("Invalid updated information for artist.");
                    return false;
                }

                if (artist.checkBOD()) {
                    if (!this.getOccupations().equals(artist.getOccupations())) {
                    System.out.println("Cannot update occupations for artist born before 2000!");
                    return false;
                    }                    
                }

                if (artist.awardsBefore2000().size() != 0) {
                    ArrayList<String> list = artist.awardsBefore2000();
                    if (!this.getAwards().containsAll(list)) {
                        System.out.println("Cannot update the Awards before 2000.");
                        return false;
                    }
                    
                }
                lines.set(index, this.toString());
                break;
            }
        }

        if (!artistFound) {
            System.out.println("Error: Artist with ID " + this.getID() + " not found.");
            return false;
        }
        
        // Write the updated content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("artists.txt"))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Artist information updated successfully.");
            return true;
        } catch (IOException e) {
            System.out.println("Error: An IO exception occurred while writing to the file.");
            e.printStackTrace();
            return false;
        }
    }
    

    public ArrayList<String> awardsBefore2000() {
        ArrayList <String> result = new ArrayList<String>();
        for (int i =0; i< this.getAwards().size(); i++) {
            Pattern pattern = Pattern.compile("\\b\\d{4}\\b");
            Matcher matcher = pattern.matcher(this.getAwards().get(i));

            // Find the matching number
            if (matcher.find()) {
                String extractedYear = matcher.group();
                // System.out.println("Extracted Year: " + extractedYear);
                if (Integer.parseInt(extractedYear) <= 2000) {
                    result.add(this.getAwards().get(i));
                }
            } else {
                System.out.println("Year not found in the input string.");
            }
        }
        return result;        
    }

    public boolean checkBOD() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date birthdate = dateFormat.parse(this.getBirthdate());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(birthdate);
            if (calendar.get(Calendar.YEAR) < 2000) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format");
            return false;
        }
    }

    // public boolean checkAwards() {
    //     for (int i =0; i < this.getAwards().size(); i++) {
    //         Pattern pattern = Pattern.compile("\\b\\d{4}\\b");
    //         Matcher matcher = pattern.matcher(this.getAwards().get(i));

    //         // Find the matching number
    //         if (matcher.find()) {
    //             String extractedYear = matcher.group();
    //             System.out.println("Extracted Year: " + extractedYear);
    //         } else {
    //             System.out.println("Year not found in the input string.");
    //         }
    //     }
    // }

    public Artist () {
    }

    // constructor for Artist class
    public Artist(String id, String name, String address, String birthdate, String bio, ArrayList<String> occupations, ArrayList<String> genres, ArrayList<String> awards) {
        ID = id;
        Name = name;
        Address = address;
        Birthdate = birthdate;
        Bio = bio;
        Occupations = occupations;
        Genres = genres;
        Awards = awards;
    }

    public boolean validID() {
        String regex = "[5-9]{3}[A-Z]{5}[!@#$%^&*()-_=+{};:',.<>?/]{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.getID());
        if (!matcher.matches()) {
            System.out.println("Wrong ID format");
            return false;
        } else {
            return true;
        }
    }

    // validate Birthdate
    public boolean validBOD() {
        String regex = "(^0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.getBirthdate());
        if (!matcher.matches()) {
            System.out.println("Birthdate should be in format DD-MM-YYYY");
        }
        return matcher.matches();
    }

    // validate address
    public boolean validAddress() {
        String regex = "([A-Za-z\\s]+)\\|([A-Za-z\\s]+)\\|([A-Za-z\\s]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.getAddress());
        if (!matcher.matches()) {
            System.out.println("Address should be in format City|State|Country");
        }
        return matcher.matches();
    }

    // validate bio
    public boolean validBio() {
        String bio = this.getBio();
        String [] str = bio.split("\\s");
        if (str.length < 10 | str.length > 30) {
            System.out.println("Bio's length should be between 10 and 30 words.");
        }
        return (str.length >= 10 && str.length <= 30);
    }

    // validate occupations
    public boolean validOccupations() {
        ArrayList<String> occupations = this.getOccupations();
        if (occupations.size() < 1 | occupations.size() > 5) {
            System.out.println(
                    "The artist should have at least 1 and a maximum of 5 occupations. (Example: Singer, Songwriter.)");
        }
        return (occupations.size() >= 1 && occupations.size() <= 5);
    }

    // validate awards
    // An artist can have zero to a maximum of three awards. Each award should
    // follow the following format: Year, Title. The Title should be between 4 to 10
    // words. Example: 2022, Best Song Written For Visual Media
    public boolean validAwards() {
        boolean validator = true;
        if (this.getAwards().size() > 3) {
            System.out.println("The artist can only have at most 3 awards.");
            validator = false;
        } else {
            for (int i = 0; i < this.getAwards().size(); i++) {
                String regex = "(\\d{4}),\\s([A-Za-z\\s])+";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(this.getAwards().get(i));
                validator = matcher.matches();
                if (!validator) {
                    System.out.println(
                            "Award should be in format 'Year, Title' with Title being between 4 and 10 words.");
                }
            }
        }
        return validator;
    }

    // validate genres
    public boolean validGenres() {
        ArrayList<String> genres = this.getGenres();
        boolean validator = true;
        if (this.getGenres().size() < 2 || this.getGenres().size() > 5) {
            System.out.println("The artist should be active in at least 2 genres and maximum 5 genres.");
            validator = false;
        } else {
            if ((this.getGenres().contains("pop") && genres.contains("rock"))) {
                System.out.println("The artist cannot be active in both pop and rock ");
                validator = false;
            }
        }
        return validator;
    }

    // validate Artist's information
    public boolean validate() {
        return (validID() && validAddress() && validBOD() && validBio() && validOccupations() && validAwards()
                && validGenres() && validAwards());
    }

    public ArrayList<String> fromArraytoArrayList(String [] strings) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i =0; i < strings.length; i++) {
            result.add(strings[i]);
        }
        return result;
    }

    private static void appendUsingBufferedWriter(String filePath, String text, int noOfLines) {
        File file = new File(filePath);
        FileWriter fr = null;
        BufferedWriter br = null;
        try {
            // to append to file, you need to initialize FileWriter using below constructor
            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);
            for (int i = 0; i < noOfLines; i++) {
                br.newLine();
                // you can use write or append method
                br.write(text);
            }
            System.out.println("Successfully added to file.");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return String return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * @return String return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return String return the Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     * @return String return the Birthdate
     */
    public String getBirthdate() {
        return Birthdate;
    }

    /**
     * @param Birthdate the Birthdate to set
     */
    public void setBirthdate(String Birthdate) {
        this.Birthdate = Birthdate;
    }

    /**
     * @return String return the Bio
     */
    public String getBio() {
        return Bio;
    }

    /**
     * @param Bio the Bio to set
     */
    public void setBio(String Bio) {
        this.Bio = Bio;
    }

    /**
     * @return ArrayList <String> return the Occupations
     */
    public ArrayList<String> getOccupations() {
        return Occupations;
    }

    /**
     * @param Awards the Awards to set
     */
    public void setOccupations(ArrayList<String> Occupations) {
        this.Occupations = Occupations;
    }

    /**
     * @return ArrayList <String> return the Genres
     */
    public ArrayList<String> getGenres() {
        return Genres;
    }

    /**
     * @param Awards the Genres to set
     */
    public void setGenres(ArrayList<String> Genres) {
        this.Genres = Genres;
    }

    /**
     * @return ArrayList <String> return the Awards
     */
    public ArrayList<String> getAwards() {
        return Awards;
    }

    /**
     * @param Awards the Awards to set
     */
    public void setAwards(ArrayList<String> Awards) {
        this.Awards = Awards;
    }

    public String arrayToString(ArrayList <String> list) {
        String result = "";
        for (int i =0; i < list.size(); i++) {
            result += list.get(i) + "/";
        }
        return result;
    }

    public String toString() {
        return this.getID() + ", " + 
                this.getName() + ", " + 
                this.getAddress() + ", " +
                this.getBirthdate() + ", " +
                this.getBio() +", " + 
                arrayToString(this.getOccupations()) + ", " +
                arrayToString(this.getGenres() )+ ", " +
                arrayToString(this.getAwards());
    }
}