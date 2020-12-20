package com.mask.mask.service;

import com.mask.mask.entity.ClaimTravel;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface ClaimTravelService {
    public String addClaimTravel(ClaimTravel claimTravel);
    public Page<ClaimTravel> getClaimTravelInPage(Pageable pageable);
    public List<ClaimTravel> getAllClaimTravel();
    public ClaimTravel getClaimTravelById (String id);
    public void editClaimTravel(String id, ClaimTravel claimTravel);
    public void deleteClaimTravelById (String id);
    public void updateClaimTravelApproved(String id, ClaimTravel claimTravel) throws IOException, MessagingException;
    public void updateClaimTravelRejected(String id) throws IOException, MessagingException;
}
