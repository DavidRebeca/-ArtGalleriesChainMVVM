package model;

import javax.persistence.*;

@Entity
@Table(name = "artWork")
public class ArtWork implements Comparable{
    @Id
    @Column(unique = true, nullable = false)
    private int idArtWork;
    private String name;
    private String artist;
    private int year;
    private String type;
    private String status;
    @ManyToOne
    @JoinColumn(name = "idGallery")
    private ArtGallery artGallery;

    public ArtWork(){}

    public ArtWork(int idArtWork, String name, String artist, int year, String type, ArtGallery artGallery, String status) {
        this.idArtWork = idArtWork;
        this.name = name;
        this.artist = artist;
        this.year = year;
        this.type = type;
        this.artGallery = artGallery;
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArtGallery getArtGallery() {
        return artGallery;
    }

    public void setArtGallery(ArtGallery artGallery) {
        this.artGallery = artGallery;
    }


    public int getIdArtWork() {
        return idArtWork;
    }

    public void setIdArtWork(int idArtWork) {
        this.idArtWork = idArtWork;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(Object artWork) {
        int compareYear=((ArtWork)artWork).getYear();
        return this.getYear()-compareYear;
    }
}
