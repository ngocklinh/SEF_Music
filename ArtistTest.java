import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.*;

public class ArtistTest {

    public static void main(String[] args) {
        ArtistTest test = new ArtistTest();
        // test.testAddArtist1(); 
        // test.invalidIDTest();  
        // test.invalidBioTest();
        // test.updateTest();
        // test.before2000Test();
        test.awardsTest();


    }

    @Test 
    public void testAddArtist1() {
        ArrayList <String> occupations1 = new ArrayList<String>();
        occupations1.add("singer");
        occupations1.add("songwriter");

        ArrayList <String> genres1 = new ArrayList<String>();
        genres1.add("RNB");
        genres1.add("Pop");

        ArrayList <String> awards1 = new ArrayList<String>();
        awards1.add("2018, New Artist of the Year");

        Artist artist1 = new Artist("567ABCDE!@", "Peter", "Melb|VIC|Aus", "01-01-1990", "Talented artist passionate about music and creativity. With a unique style, bring joyful melodies and heartfelt lyrics to life.", occupations1, genres1, awards1);
        
        ArrayList <String> occupations2 = new ArrayList<String>();
        occupations2.add("singer");
        occupations2.add("rapper");

        ArrayList <String> genres2 = new ArrayList<String>();
        genres2.add("rap");
        genres2.add("pop");

        ArrayList <String> awards2 = new ArrayList<String>();
        awards2.add("2020, New Artist of the Year");
        awards2.add("2021, Collaboration of the Year");

        Artist artist2 = new Artist("789BCDEF!@", "Doja Cat", "Tarzana|Los Angeles|USA", "21-10-1995", "Amalaratna Zandile Dlamini, known by her stage name Doja Cat, is an American singer and rapper. Born and raised in LA, she began writing, uploading songs on SoundCloud in teenager.", occupations2, genres2, awards2);

        assertAll("",()->Assert.assertTrue(artist1.addArtist()),
                    ()->Assert.assertTrue (artist2.addArtist()));
    }


    @Test 
    public void invalidIDTest() {
        // Test data 1
        // (“499AsGDA./", “Harvey”, “05-08-2001”, “Brisbane|Queensland|Australia”, “Pop musician passionate with experimenting and blending jazz into pop music ”, [“musician”, “songwriter”], [“Jazz”, “Pop”, “Fusion”], [“2022, Best Indie Artist”])

        ArrayList <String> occupations3 = new ArrayList<String>();
        occupations3.add("musician");
        occupations3.add("songwriter");

        ArrayList <String> genres3 = new ArrayList<String>();
        genres3.add("jazz");
        genres3.add("pop");
        genres3.add("fusion");

        ArrayList <String> awards3 = new ArrayList<String>();
        awards3.add("2022, Best Indie Artist");

        Artist artist3 = new Artist("499AsGDA./", "Harvey", "05-08-2001", "Brisbane|Queensland|Australia", "Pop musician passionate with experimenting and blending jazz into pop music ", occupations3, genres3, awards3);

        // test data2 
        // (“52PAJSDH?\", “Dereck”, “07-10-1998”, “Miami|Florida|US”, “Dereck is an independent rock artist who is heavily influenced by the 1960’s British rock scene, [“musician”, “singer”], [“Rock”, “Blues”])
        ArrayList <String> occupations4 = new ArrayList<String>();
        occupations4.add("musician");
        occupations4.add("singer");

        ArrayList <String> genres4 = new ArrayList<String>();
        genres4.add("rock");
        genres4.add("blues");

        ArrayList <String> awards4 = new ArrayList<String>();
        awards4.add("2021, Best Indie Artist");

        Artist artist4 = new Artist("52PAJSDH?\\", "Dereck", "07-10-1998", "Miami|Florida|USA", "Dereck is an independent rock artist who is heavily influenced by the 1960’s British rock scene", occupations4, genres4, awards4);

        assertAll("",()->Assert.assertFalse(artist3.addArtist()),
                    ()->Assert.assertFalse(artist4.addArtist()));

    }

    @Test 
    public void invalidBioTest() {
        //(“799QIUQW/.", “Peter”, “11-12-1994”, “Chicago|Illinois|US”, “Up and coming rapper”, [“musician”, “rapper”, “songwriter”], [“Rap”, “Pop”], [“2021, Potential New Rapper”])
        ArrayList <String> occupations = new ArrayList<String>();
        occupations.add("musician");
        occupations.add("rapper");
        occupations.add("songwriter");

        ArrayList <String> genres = new ArrayList<String>();
        genres.add("rap");
        genres.add("pop");

        ArrayList <String> awards = new ArrayList<String>();
        awards.add("2021, Potential New Rapper");

        Artist artist1 = new Artist("799QIUQW/.", "Peter", "Chicago|Illinois|US", "11-12-1994", "Up and coming rapper", occupations, genres, awards);
        // (“959EHIWF;.", “Selena”, “26-08-1995”, “Sydney|NSW|Australia”, “Pursued classical music at a young age”, [“musician”, “pianist”], [“Classical”, “Instrumental”])
        ArrayList <String> occupations2 = new ArrayList<String>();
        occupations2.add("musician");
        occupations2.add("pianist");

        ArrayList <String> genres2 = new ArrayList<String>();
        genres2.add("instrumental");
        genres2.add("classical");

        ArrayList <String> awards2 = new ArrayList<String>();
        awards2.add("2017, NOminated Young Potential Pianist");

        Artist artist2 = new Artist("959EHIWF;.", "Selena", "Sydney|NSW|Australia", "26-08-1995", "Pursued classical music at a young age", occupations2, genres2, awards2);

        assertAll("",()->Assert.assertFalse(artist1.addArtist()),
                    ()->Assert.assertFalse(artist2.addArtist()));

        // Assert.assertFalse(artist.validBio());
        // Assert.assertFalse(artist.updateArtist());
    }


    @Test 
    public void updateTest() {

        // test data 1
        // original artist 
        ArrayList <String> occupations1 = new ArrayList<String>();
        occupations1.add("songwriter");
        occupations1.add("singer");

        ArrayList <String> genres1 = new ArrayList<String>();
        genres1.add("RNB");
        genres1.add("Pop");

        ArrayList <String> awards1 = new ArrayList<String>();
        awards1.add("2018, New Artist of the Year");

        Artist original1 = new Artist("567ABCDE!@", "Liam", "Melb|VIC|Aus", "01-01-1990", "Talented artist passionate about music and creativity. With a unique style, bring joyful melodies and heartfelt lyrics to life.", occupations1, genres1, awards1);
        
        original1.addArtist();

//      updated artist
        ArrayList <String> occupations = new ArrayList<String>();
        occupations.add("songwriter");
        occupations.add("singer");

        ArrayList <String> genres = new ArrayList<String>();
        genres.add("hiphop");
        genres.add("pop");

        ArrayList <String> awards = new ArrayList<String>();
        // 2018, New Artist of the Year”, “2022, Nominated Artist of the Year”
        awards.add("2018, New Artist of the Year");
        awards.add("2022, Nominated Artist of the Year");

        Artist updated1 = new Artist("567ABCDE!@", "Liam", "Melb|VIC|Aus", "01-01-1990", "Talented artist passionate about music and creativity. With a unique style, bringing joyful melodies and heartfelt lyrics to life.", occupations, genres, awards);

        // updated1.updateArtist(original1);

        // test data 2
        ArrayList <String> occupations2 = new ArrayList<String>();
        occupations2.add("singer");
        occupations2.add("rapper");

        ArrayList <String> genres2 = new ArrayList<String>();
        genres2.add("rap");
        genres2.add("pop");

        ArrayList <String> awards2 = new ArrayList<String>();
        awards2.add("2020, New Artist of the Year");
        awards2.add("2021, Collaboration of the Year");

        Artist original2 = new Artist("789BCDEF!@", "Doja", "Tarzana|Los Angeles|USA", "21-10-1995", "Amalaratna Zandile Dlamini, known by her stage name Doja Cat, is an American singer and rapper. Born and raised in LA, she began writing, uploading songs on SoundCloud in teenager.", occupations2, genres2, awards2);

        original2.addArtist();

        ArrayList <String> occupations3 = new ArrayList<String>();
        occupations3.add("singer");
        occupations3.add("rapper");

        ArrayList <String> genres3 = new ArrayList<String>();
        genres3.add("rap");
        genres3.add("pop");

        ArrayList <String> awards3 = new ArrayList<String>();
        awards3.add("2019, New Artist of the Year");
        awards3.add("2021, Collaboration of the Year");

        // (“789BCDEF!@", “Doja Cat”, “01-03-1995”, “Tarzana|Los Angeles|USA”, ”Amalaratna, known by her stage name Doja Cat, is an American singer and rapper. Born and raised in LA, she began writing, uploading songs on SoundCloud in her teenager.”, [“singer”, “rapper”], [“rap”, “pop”], [“2020, New Artist of the Year”, “2021, Collaboration of the Year”])
        Artist updated2 = new Artist("789BCDEF!@", "Doja ", "Tarzana|Los Angeles|USA", "01-03-1995", "Amalaratna, known by her stage name Doja Cat, is an American singer and rapper. Born and raised in LA, she began writing, uploading songs on SoundCloud in her teenager.", occupations3, genres3, awards3);
        
        // updated2.updateArtist(original2);
        
        // Assert.assertTrue(updated2.updateArtist(original2));
        assertAll("",()->Assert.assertTrue(updated1.updateArtist(original1)),
                    ()->Assert.assertTrue (updated2.updateArtist(original2)));
    }
    
    
    @Test 
    public void before2000Test() {
        // test data 1
        ArrayList <String> occupations1 = new ArrayList<String>();
        occupations1.add("musician");
        occupations1.add("songwriter");

        ArrayList <String> genres1 = new ArrayList<String>();
        genres1.add("country");
        genres1.add("bluegrass");
        genres1.add("pop");

        ArrayList <String> awards1 = new ArrayList<String>();
        awards1.add("2020, Best Young Country Artist");

        Artist original1 = new Artist("555AJIDI;/", "Kent", "Austin|Texas|US", "25-01-1989", "Born and raised in Texas, Kent puts his country and bluegrass roots into his pop songs", occupations1, genres1, awards1); 

        ArrayList<String> occupations_ = new ArrayList<>();
        occupations_.add("singer");
        Artist updated1 = new Artist(original1.getID(), original1.getName(), original1.getAddress(), original1.getBirthdate(), original1.getBio(), occupations_, genres1, awards1);

        // test data 2
        // (“798ABCDE!@", “Jenny”, “16-02-1994”, “New York|New York|US”, “An independent artist who aims to bring positivity to audience through her music”, [“singer”, “songwriter”, ”musician”], [“Pop”, “RNB”])
        ArrayList <String> occupations2 = new ArrayList<String>();
        occupations2.add("singer");
        occupations2.add("songwriter");
        occupations2.add("musician");

        ArrayList <String> genres2 = new ArrayList<String>();
        genres2.add("RNB");
        genres2.add("pop");

        ArrayList <String> awards2 = new ArrayList<String>();
        awards2.add("2021, Collaboration of the Year");

        Artist original2 = new Artist("798ABCDE!@", "Jenny", "New York|New York|USA", "16-02-1994", "An independent artist who aims to bring positivity to audience through her music.", occupations2, genres2, awards2);

        original2.addArtist();

        ArrayList <String> occupationsUpdated = new ArrayList<String>();
        occupationsUpdated.add("rapper");
        occupationsUpdated.add("songwriter");
        occupationsUpdated.add("musician");
        // System.out.println(occupations);
        Artist updated2 = new Artist(original2.getID(), original2.getName(), original2.getAddress(), original2.getBirthdate(), original2.getBio(), occupationsUpdated, original2.getGenres(), original2.getAwards());

        assertAll("",()->Assert.assertFalse(updated1.updateArtist(original1)),
                    ()->Assert.assertFalse(updated2.updateArtist(original2)));
    }

    @Test 
    public void awardsTest() {
        // test data 1
        // (“598AIHWA_.", “Tom”, “15-07-1970”, “New Orleans|Louisiana|US”, “Tom is a classical pianist with impeccable taste and exquisite interpretations of famous classical pieces”, [“musician”, “pianist”], [“classical”, “instrumental”], [“1998, Best Classical Musician”])
        ArrayList <String> occupations1 = new ArrayList<String>();
        occupations1.add("musician");
        occupations1.add("pianist");

        ArrayList <String> genres1 = new ArrayList<String>();
        genres1.add("classical");
        genres1.add("instrumental");

        ArrayList <String> awards1 = new ArrayList<String>();
        awards1.add("1998, Best Classical Musician");

        Artist original1 = new Artist("598AIHWA_.", "Tom", "New Orleans|Louisiana|US", "15-07-1970", "Tom is a classical pianist with impeccable taste and exquisite interpretations of famous classical pieces", occupations1, genres1, awards1);

        original1.addArtist();

        ArrayList <String> awardsUpdated = new ArrayList<String>();
        awardsUpdated.add("2002, Best Classical Musician");
        // System.out.println(occupations);
        Artist updated1 = new Artist(original1.getID(), original1.getName(), original1.getAddress(), original1.getBirthdate(), original1.getBio(), original1.getOccupations(), original1.getGenres(), awardsUpdated);

        // test data 2
        // (“968AKWOL[.", “Tune”, ”16-09-1981”, “New York|New York|US”, “Tune is a disco band formed in 1981, and has been operating till this day”, [“musician”, “songwriter”,”band”], [“pop”, “disco”], [“1989, Best New Band”])
        ArrayList <String> occupations2 = new ArrayList<String>();
        occupations2.add("musician");
        occupations2.add("songwriter");
        occupations2.add("band");

        ArrayList <String> genres2 = new ArrayList<String>();
        genres2.add("pop");
        genres2.add("disco");

        ArrayList <String> awards2 = new ArrayList<String>();
        awards2.add("1989, Best New Band");

        Artist original2 = new Artist("968AKWOL[.", "Tune", "New York|New York|US", "16-09-1981", "Tune is a disco band formed in 1981, and has been operating till this day.", occupations2, genres2, awards2);

        original2.addArtist();

        ArrayList <String> awardsUpdated2 = new ArrayList<String>();
        awardsUpdated.add("2002, Best Classical Band");
        // System.out.println(occupations);
        Artist updated2 = new Artist(original2.getID(), original2.getName(), original2.getAddress(), original2.getBirthdate(), original2.getBio(), original2.getOccupations(), original2.getGenres(), awardsUpdated2);
        
        assertAll("",()->Assert.assertFalse(updated1.updateArtist(original1)),
                    ()->Assert.assertFalse(updated2.updateArtist(original2)));
    }
}



    
