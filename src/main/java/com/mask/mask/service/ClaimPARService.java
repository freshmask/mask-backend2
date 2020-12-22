package com.mask.mask.service;

import com.mask.mask.dto.ClaimPARSearchDTO;
import com.mask.mask.entity.ClaimPAR;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface ClaimPARService {
    public String addClaimPAR(ClaimPAR claimPAR);
    public Page<ClaimPAR> getClaimPARInPage(Pageable pageable, ClaimPARSearchDTO claimPARSearchDTO);
    public List<ClaimPAR> getAllClaimPAR();
    public ClaimPAR getClaimPARById (String claimparId);
    public void editClaimPAR(String claimparId, ClaimPAR claimPAR);
    public void deleteClaimPARById (String claimparId);
    public void updateClaimPARApproved(String id, ClaimPAR claimPAR) throws IOException, MessagingException;
    public void updateClaimPARRejected(String id, String description) throws IOException, MessagingException;
}
