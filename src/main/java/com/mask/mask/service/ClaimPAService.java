package com.mask.mask.service;


import com.mask.mask.entity.ClaimPA;
import com.mask.mask.entity.ClaimTravel;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface ClaimPAService {
    public String saveClaimPA(ClaimPA claimPA);
    public String saveClaimPAByHeir(ClaimPA claimPA, String polisId, String identityHeirNumber, String nameOfTheInsured,String emailOfTheInsured);
    public void updateClaimPA(String id, ClaimPA claimPA);
    public ClaimPA getClaimPAById(String id);
    public Page<ClaimPA> getClaimPAPerPage(Pageable pageable);
    public List<ClaimPA> getClaimPA();
    public void deleteClaimPAById(String id);

    public void updateClaimPAAproved(String id, ClaimPA claimPA) throws IOException, MessagingException;
    public void reviewClaimPAApproved(String id, ClaimPA claimPA) throws IOException, MessagingException;
    public void updateClaimPARejected(String id, String description) throws IOException, MessagingException;


}
