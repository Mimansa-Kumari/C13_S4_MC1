package com.example.MuzixApplication.repository;

import com.example.MuzixApplication.domain.Artist;
import com.example.MuzixApplication.domain.Track;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest

class ITrackRepositoryTest {
    @Autowired
    ITrackRepository iTrackRepository;
    Track track;
    Artist artist;

    @BeforeEach
    void setUp() {
        artist = new Artist(444,"Shreya");
        track = new Track(44,"Tum hi ho",8,artist);
    }

    @AfterEach
    void tearDown() {
        artist = null;
        track = null;
        iTrackRepository.deleteAll();
    }

    @Test
    @DisplayName("Test case for saving customer object")
    void givenTrackToSaveShouldReturnTrack() {
        iTrackRepository.save(track);
        Track track1= iTrackRepository.findById(track.getTrackId()).get();
        assertNotNull(track1);
        assertEquals(track.getTrackId(), track1.getTrackId());
    }


    @Test
    @DisplayName("Test case for deleting track object")
    public void givenTrackToDeleteShouldDeleteTrack() {
        iTrackRepository.insert(track);
        Track track1 = iTrackRepository.findById(track.getTrackId()).get();
        iTrackRepository.delete(track1);
        assertEquals(Optional.empty(), iTrackRepository.findById(track.getTrackId()));

    }

    @Test
    @DisplayName("Test case for retrieving track details whose rating is greater than 4")
    void getTrackByTrackRating() {
        iTrackRepository.insert(track);
        Artist artist1 = new Artist(222,"Sonu Nigam");
        Track track1 = new Track(22,"Kabhi to",3,artist1);
        iTrackRepository.insert(track1);
        List<Track> list = iTrackRepository.getTrackByTrackRating();
        assertEquals(1,list.size());
    }

    @Test
    @DisplayName("Test case for retrieving specified artist name")
    void getTrackByArtistName() {
        iTrackRepository.insert(track);
        Artist artist1 = new Artist(222,"Sonu Nigam");
        Track track1 = new Track(22,"Kabhi to",3,artist1);
        iTrackRepository.insert(track1);
        List<Track> list = iTrackRepository.getTrackByArtistName("Shreya");
        assertEquals(1,list.size());
    }
}