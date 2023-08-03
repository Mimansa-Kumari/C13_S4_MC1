package com.example.MuzixApplication.service;

import com.example.MuzixApplication.domain.Track;
import com.example.MuzixApplication.exception.ArtistNameNotFound;
import com.example.MuzixApplication.exception.InvalidTrackId;
import com.example.MuzixApplication.exception.NoTrackExistWithTheSpecifiedCondition;
import com.example.MuzixApplication.exception.TrackDetailsWithSpecifiedIdAlreadyExist;
import com.example.MuzixApplication.repository.ITrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService implements ITrackService {
    @Autowired
    ITrackRepository iTrackRepository;

    public TrackService(ITrackRepository iTrackRepository) {
        this.iTrackRepository = iTrackRepository;
    }

    @Override
    public Track saveTrackDetails(Track track) throws TrackDetailsWithSpecifiedIdAlreadyExist {
        if(iTrackRepository.findById(track.getTrackId()).isEmpty())
        {
            return iTrackRepository.insert(track);
        }
        else
        {
            throw new TrackDetailsWithSpecifiedIdAlreadyExist();
        }
    }

    @Override
    public boolean deleteTrackDetails(int trackId) throws InvalidTrackId {
        if(iTrackRepository.findById(trackId).isPresent())
        {
            iTrackRepository.deleteById(trackId);
            return true;
        }
        else
        {
           throw new InvalidTrackId();
        }
    }

    @Override
    public List<Track> fetchAllTrackDetails() {
        return iTrackRepository.findAll();
    }

    @Override
    public List<Track> getTrackByTrackRating() throws NoTrackExistWithTheSpecifiedCondition {
        return iTrackRepository.getTrackByTrackRating();
    }

    @Override
    public List<Track> getTrackByArtistName(String artistName) throws ArtistNameNotFound{
        return iTrackRepository.getTrackByArtistName(artistName);
    }
}
