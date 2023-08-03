package com.example.MuzixApplication.service;

import com.example.MuzixApplication.domain.Artist;
import com.example.MuzixApplication.domain.Track;
import com.example.MuzixApplication.exception.InvalidTrackId;
import com.example.MuzixApplication.exception.TrackDetailsWithSpecifiedIdAlreadyExist;
import com.example.MuzixApplication.repository.ITrackRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrackServiceTest {
    @Mock
    ITrackRepository iTrackRepository;
    @InjectMocks
    TrackService trackService;

    private Track track1, track2;
    private Artist artist1, artist2;
    List<Track> list;

    @BeforeEach
    void setUp() {
        artist1 = new Artist(888,"Palak");
        track1 = new Track(88,"Life is long",7,artist1);
        artist2 = new Artist(999,"Sanam");
        track2 = new Track(99,"Ek pyar ka Nagma hai",2,artist2);
        list = Arrays.asList(track1,track2);
    }

    @AfterEach
    void tearDown() {
        track1 = null;
        track2 = null;
    }

    @Test
    void saveTrackDetails() throws TrackDetailsWithSpecifiedIdAlreadyExist {
        when(iTrackRepository.findById(track1.getTrackId())).thenReturn(Optional.ofNullable(null));
        when(iTrackRepository.insert(track1)).thenReturn(track1);
        assertEquals(track1,trackService.saveTrackDetails(track1));
        verify(iTrackRepository,times(1)).insert(track1);
        verify(iTrackRepository,times(1)).findById(any());
    }

    @Test
    public void givenTrackToSaveReturnTrackFailure(){
        when(iTrackRepository.findById(track1.getTrackId())).thenReturn(Optional.ofNullable(track1));
        assertThrows(TrackDetailsWithSpecifiedIdAlreadyExist.class,()->trackService.saveTrackDetails(track1));
        verify(iTrackRepository,times(0)).save(any());
        verify(iTrackRepository,times(1)).findById(any());
    }

    @Test
    public void givenTrackToDeleteShouldDeleteSuccess() throws InvalidTrackId {
        when(iTrackRepository.findById(track1.getTrackId())).thenReturn(Optional.ofNullable(track1));
        boolean flag = trackService.deleteTrackDetails(track1.getTrackId());
        assertEquals(true,flag);
        verify(iTrackRepository,times(1)).deleteById(any());
        verify(iTrackRepository,times(1)).findById(any());
    }

    @Test
    public void givenTrackToDeleteShouldDeleteFailure() throws InvalidTrackId {
        when(iTrackRepository.findById(track1.getTrackId())).thenReturn(Optional.ofNullable(null));
        assertThrows(InvalidTrackId.class,()->trackService.deleteTrackDetails(track1.getTrackId()));
        verify(iTrackRepository,times(0)).deleteById(any());
        verify(iTrackRepository,times(1)).findById(any());
    }
}