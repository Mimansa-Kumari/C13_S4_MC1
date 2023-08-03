package com.example.MuzixApplication.service;

import com.example.MuzixApplication.domain.Track;
import com.example.MuzixApplication.exception.ArtistNameNotFound;
import com.example.MuzixApplication.exception.InvalidTrackId;
import com.example.MuzixApplication.exception.NoTrackExistWithTheSpecifiedCondition;
import com.example.MuzixApplication.exception.TrackDetailsWithSpecifiedIdAlreadyExist;

import java.util.List;

public interface ITrackService {

    Track saveTrackDetails(Track track) throws TrackDetailsWithSpecifiedIdAlreadyExist;
    boolean deleteTrackDetails(int trackId) throws InvalidTrackId;
    List<Track> fetchAllTrackDetails();
    List<Track> getTrackByTrackRating() throws NoTrackExistWithTheSpecifiedCondition;
    List<Track> getTrackByArtistName(String artistName) throws ArtistNameNotFound ;
}
